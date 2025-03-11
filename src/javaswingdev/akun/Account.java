package javaswingdev.akun;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import javaswingdev.swing.titlebar.ComponentResizer;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;

public class Account extends JComponent {

    private JFrame fram;
    private ComponentResizer resizer;
    private JPanel panel;
    private boolean register = true;
    private int x;
    private int y;
    private JPopupMenu dropdownMenu; // Dropdown menu untuk Profile dan Logout

    public Account() {
        init();
    }

    public void initJFram(JFrame fram) {
        this.fram = fram;
        resizer = new ComponentResizer();
        resizer.setSnapSize(new Dimension(10, 10));
        resizer.setMinimumSize(new Dimension(800, 600));
        resizer.registerComponent(fram);
        fram.addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if (e.getNewState() == JFrame.MAXIMIZED_BOTH) {
                    resizer.deregisterComponent(fram);
                    register = false;
                } else if (e.getNewState() == JFrame.NORMAL) {
                    if (register == false) {
                        resizer.registerComponent(fram);
                        register = true;
                    }
                }
            }
        });
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (fram.getExtendedState() == JFrame.NORMAL && SwingUtilities.isLeftMouseButton(e)) {
                    x = e.getX() + 3;
                    y = e.getY() + 3;
                }
            }
        });
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (fram.getExtendedState() == JFrame.NORMAL) {
                        fram.setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
                    }
                }
            }
        });
    }

    private void init() {
        setLayout(new MigLayout("inset 3, fill", "[fill]", "[fill]"));
        panel = new JPanel();
        panel.setOpaque(false);
        add(panel);
        panel.setLayout(new MigLayout("inset 3"));

        // Membuat tombol dengan teks dan warna background sesuai gambar
        Item adminButton = new Item("admin", new Color(239, 224, 192));
        panel.add(adminButton);

        // Membuat dropdown menu yang menyatu dan warnanya konsisten
        dropdownMenu = new JPopupMenu() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Warna background yang sama dengan tombol
                g2.setColor(new Color(239, 224, 192));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

                g2.dispose();
            }
        };

        // Menghilangkan border bawaan JPopupMenu
        dropdownMenu.setBorderPainted(false);

        // Membuat item Profile dan Logout dengan warna konsisten
        JMenuItem profileItem = new JMenuItem("Profile") {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(239, 224, 192));
                setForeground(new Color(90, 45, 15));
            }
        };
        JMenuItem logoutItem = new JMenuItem("Logout") {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(239, 224, 192));
                setForeground(new Color(90, 45, 15));
            }
        };

        // Menghilangkan border pada item
        profileItem.setBorderPainted(false);
        logoutItem.setBorderPainted(false);

        // Event untuk Profile
        profileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Profile Clicked");
            }
        });

        // Event untuk Logout
        logoutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Logout Clicked");
            }
        });

        // Menambah item ke dropdown
        dropdownMenu.add(profileItem);
        dropdownMenu.add(logoutItem);

        // Menampilkan dropdown saat tombol di-klik
        adminButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dropdownMenu.show(adminButton, 0, adminButton.getHeight() - 5); // Dropdown menyatu ke atasnya
            }
        });
    }

    private class Item extends JButton {

        public Item(String text, Color backgroundColor) {
            setText(text);
            setBackground(backgroundColor);
            init();
        }

        private void init() {
            setContentAreaFilled(false);
            setBorder(null);
            setPreferredSize(new Dimension(80, 30)); // Ukuran tombol
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setFocusPainted(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();

            // Warna background dengan efek hover
            if (getModel().isRollover()) {
                g2.setColor(getBackground().darker());
            } else {
                g2.setColor(getBackground());
            }

            // Membuat tombol dengan sudut melengkung
            g2.fillRoundRect(0, 0, width, height, 20, 20);

            // Menambahkan ikon orang di sebelah kiri teks
            g2.setColor(new Color(90, 45, 15)); // Warna ikon
            int iconSize = 14;
            int iconX = 10;
            int iconY = (height - iconSize) / 2;
            g2.fillOval(iconX, iconY, iconSize, iconSize);

            // Menampilkan teks
            g2.setColor(new Color(90, 45, 15)); // Warna teks agar terlihat
            g2.setFont(getFont());
            int textX = iconX + iconSize + 5;
            int textY = height / 2 + g2.getFontMetrics().getAscent() / 2 - 2;
            g2.drawString(getText(), textX, textY);

            g2.dispose();
        }
    }
}
