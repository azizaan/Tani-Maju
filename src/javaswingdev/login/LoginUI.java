/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaswingdev.login;

/**
 *
 * @author ACER
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel labelTitle = new JLabel("LOGIN");
        labelTitle.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitle.setForeground(new Color(102, 0, 204));
        labelTitle.setBounds(160, 20, 100, 30);
        panel.add(labelTitle);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(50, 70, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(150, 70, 180, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 110, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(150, 110, 180, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 160, 180, 30);
        loginButton.setBackground(new Color(102, 0, 204));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        panel.add(loginButton);

        JLabel forgotPassword = new JLabel("Lupa Password?");
        forgotPassword.setBounds(200, 200, 150, 20);
        forgotPassword.setForeground(new Color(102, 0, 204));
        panel.add(forgotPassword);

        frame.add(panel);
        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                if (username.equals("admin") && password.equals("admin")) {
                    JOptionPane.showMessageDialog(frame, "Login Berhasil!");
                    frame.dispose(); // Tutup form login
                } else {
                    JOptionPane.showMessageDialog(frame, "Username atau Password salah", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
