/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaswingdev.form.admin;

/**
 *
 * @author ACER
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class BouncingBallGame extends JFrame {

    private static final int BALL_RADIUS = 20;
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_HEIGHT = 20;
    private int ballX = 100;
    private int ballY = 100;
    private int ballSpeedX = 3;
    private int ballSpeedY = 3;
    private int paddleX;
    private int score = 0;
    private int lives = 3;
    private Timer gameTimer;

    // Game components
    private JPanel gamePanel;
    private JLabel scoreLabel;
    private JLabel livesLabel;
    private JLabel gameOverLabel;
    private JButton playAgainButton;

    public BouncingBallGame() {
        setTitle("Bouncing Ball Game with Paddle");
        setUndecorated(true);  // Hide window borders for fullscreen effect
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to fullscreen

        // Initialize paddle position
        paddleX = (getWidth() - PADDLE_WIDTH) / 2;

        // Game panel for drawing
        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    drawBall(g);
                    drawPaddle(g);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        };

        // Score and lives labels
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.BLACK);

        livesLabel = new JLabel("Lives: " + lives);
        livesLabel.setFont(new Font("Arial", Font.BOLD, 20));
        livesLabel.setForeground(Color.BLACK);

        gameOverLabel = new JLabel("", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gameOverLabel.setForeground(Color.RED);

        // Play Again button
        playAgainButton = new JButton("Play Again");
        playAgainButton.setFont(new Font("Arial", Font.BOLD, 20));
        playAgainButton.setVisible(false);  // Initially hidden
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame(); // Reset game when clicked
            }
        });

        // KeyListener for paddle movement
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    movePaddle(e);
                } catch (Exception ex) {
                    scoreLabel.setText("Error: " + ex.getMessage());
                }
            }
        });
        setFocusable(true);

        // Game timer for ball movement
        gameTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateBallPosition();
                    gamePanel.repaint();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error occurred during game update: " + ex.getMessage());
                    System.exit(1);
                }
            }
        });
        gameTimer.start();

        // Set up the layout
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));
        topPanel.add(scoreLabel);
        topPanel.add(livesLabel);

        add(topPanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);
        add(gameOverLabel, BorderLayout.SOUTH);
        add(playAgainButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Draw the ball on the panel
    private void drawBall(Graphics g) throws Exception {
        g.setColor(Color.RED);
        g.fillOval(ballX - BALL_RADIUS, ballY - BALL_RADIUS, 2 * BALL_RADIUS, 2 * BALL_RADIUS);
    }

    // Draw the paddle
    private void drawPaddle(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(paddleX, getHeight() - PADDLE_HEIGHT - 10, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    // Update the ball's position and handle bouncing logic
    private void updateBallPosition() throws Exception {
        // Update ball position
        ballX += ballSpeedX;
        ballY += ballSpeedY;

        // Handle ball bouncing off walls
        if (ballX - BALL_RADIUS <= 0 || ballX + BALL_RADIUS >= getWidth()) {
            ballSpeedX = -ballSpeedX; // Reverse the horizontal speed
        }

        if (ballY - BALL_RADIUS <= 0) {
            ballSpeedY = -ballSpeedY; // Reverse the vertical speed
        }

        // Check if ball hits the paddle
        if (ballY + BALL_RADIUS >= getHeight() - PADDLE_HEIGHT - 10
                && ballX >= paddleX && ballX <= paddleX + PADDLE_WIDTH) {
            ballSpeedY = -ballSpeedY; // Bounce the ball off the paddle
            score++;  // Increase score if ball hits the paddle
        }

        // Check if ball falls below the window without hitting the paddle
        if (ballY + BALL_RADIUS >= getHeight()) {
            score--;  // Decrease score if ball falls down
            if (score < 0) {
                score = 0;  // Prevent negative score
            }
            lives--;  // Decrease lives
            if (lives <= 0) {
                gameOver();
            } else {
                resetBallAndPaddle();
            }
        }
    }

    // Method to move the paddle
    private void movePaddle(KeyEvent e) throws Exception {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                if (paddleX > 0) {
                    paddleX -= 20;
                } else {
                    throw new Exception("Paddle cannot move further left!");
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (paddleX < getWidth() - PADDLE_WIDTH) {
                    paddleX += 20;
                } else {
                    throw new Exception("Paddle cannot move further right!");
                }
                break;
            default:
                throw new Exception("Invalid key pressed!");
        }
    }

    // Method to reset the ball and paddle
    private void resetBallAndPaddle() {
        ballX = (getWidth() - BALL_RADIUS) / 2;
        ballY = (getHeight() - BALL_RADIUS) / 2;
        ballSpeedX = new Random().nextInt(5) + 2; // Random speed for horizontal movement
        ballSpeedY = 3; // Reset the ball speed
        paddleX = (getWidth() - PADDLE_WIDTH) / 2; // Reset paddle position
        scoreLabel.setText("Score: " + score);  // Update score label
        livesLabel.setText("Lives: " + lives);  // Update lives label
    }

    // Method to reset the game
    private void resetGame() {
        score = 0;
        lives = 3;
        gameOverLabel.setText("");
        playAgainButton.setVisible(false); // Hide the Play Again button
        scoreLabel.setText("Score: " + score);  // Reset score label
        livesLabel.setText("Lives: " + lives);  // Reset lives label
        gameTimer.start();
        resetBallAndPaddle();
    }

    // Game Over handling
    private void gameOver() {
        gameOverLabel.setText("GAME OVER!");
        playAgainButton.setVisible(true); // Show the Play Again button
        gameTimer.stop(); // Stop the game timer
        JOptionPane.showMessageDialog(this, "Game Over! Final Score: " + score);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BouncingBallGame();
            }
        });
    }
}
