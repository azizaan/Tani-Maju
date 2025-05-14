package javaswingdev.login;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import static javax.swing.JOptionPane.showMessageDialog;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JTextField;
//import javaswingdev.login.SessionManager;
//import loginandsignup.user.Home;
//import java.util.Base64;

public class Login extends javax.swing.JFrame {

    private JTextField rfidInput;

    public Login() {
        initComponents();
        setupRFIDListener();
        rfidInput.requestFocusInWindow(); // Ensure focus on startup
        btnlogin.setText("Login");
        btnlogin.setColor(new Color(0, 102, 102));              // Base (Teal Gelap)
        btnlogin.setColorOver(new Color(0, 153, 153));          // Hover (Teal Lebih Terang)
        btnlogin.setColorClick(new Color(0, 77, 77));           // Click (Teal Lebih Gelap)
        btnlogin.setBorderColor(new Color(0, 89, 89));          // Border (Teal Tua, Serasi dan Elegan)
        btnlogin.setForeground(Color.WHITE);                   // Teks Putih
        btnlogin.setRadius(30);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Right = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Left = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        showpassword = new javax.swing.JCheckBox();
        btnlogin = new button.MyButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(null);

        Right.setBackground(new java.awt.Color(0, 102, 102));
        Right.setPreferredSize(new java.awt.Dimension(400, 500));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/LOGO putih_1.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("copyright © Tani Maju Sejahtera All rights reserved");

        javax.swing.GroupLayout RightLayout = new javax.swing.GroupLayout(Right);
        Right.setLayout(RightLayout);
        RightLayout.setHorizontalGroup(
            RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightLayout.createSequentialGroup()
                .addGap(0, 50, Short.MAX_VALUE)
                .addGroup(RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(28, 28, 28)))
                .addGap(40, 40, 40))
        );
        RightLayout.setVerticalGroup(
            RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightLayout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(27, 27, 27)
                .addComponent(jLabel7)
                .addGap(78, 78, 78))
        );

        jPanel1.add(Right);
        Right.setBounds(0, 0, 400, 500);

        Left.setBackground(new java.awt.Color(255, 255, 255));
        Left.setMinimumSize(new java.awt.Dimension(400, 500));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("LOGIN");

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Email");

        email.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        email.setForeground(new java.awt.Color(102, 102, 102));

        jLabel3.setBackground(new java.awt.Color(102, 102, 102));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Password");

        jLabel4.setText("I don't have an account");

        jLabel8.setForeground(new java.awt.Color(0, 0, 255));
        jLabel8.setText("REGISTER");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signup(evt);
            }
        });

        showpassword.setText("Show Password");
        showpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showpassword(evt);
            }
        });

        btnlogin.setText("Login");
        btnlogin.setBorderPainted(false);
        btnlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnloginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LeftLayout = new javax.swing.GroupLayout(Left);
        Left.setLayout(LeftLayout);
        LeftLayout.setHorizontalGroup(
            LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftLayout.createSequentialGroup()
                .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LeftLayout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jLabel1))
                    .addGroup(LeftLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2)
                                .addComponent(email)
                                .addComponent(jLabel3)
                                .addComponent(password, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE))
                            .addGroup(LeftLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8))
                            .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnlogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(showpassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        LeftLayout.setVerticalGroup(
            LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(showpassword)
                .addGap(18, 18, 18)
                .addComponent(btnlogin, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        jPanel1.add(Left);
        Left.setBounds(400, 0, 400, 504);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static String currentUserId = null;
//    public static String currentUserIDKasir = null;
//    public static String currentName = null;

    private void setupRFIDListener() {

        rfidInput = new JTextField();

        rfidInput.addKeyListener(new KeyAdapter() {

            @Override

            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    String rfidTag = rfidInput.getText().trim();

                    if (!rfidTag.isEmpty()) {

                        loginWithRFID(rfidTag);

                    }

                    rfidInput.setText("");

                }

            }

        });

        this.add(rfidInput);

    }

    private void loginWithRFID(String rfidTag) {
        String query = "SELECT * FROM user WHERE rfid = ?";
        String SUrl = "jdbc:MySQL://localhost:3306/studicase_pupuk";
        String SUser = "root";
        String SPass = "";

        try (Connection con = DriverManager.getConnection(SUrl, SUser, SPass); PreparedStatement pst = con.prepareStatement(query)) {

            Class.forName("com.mysql.cj.jdbc.Driver");

            pst.setString(1, rfidTag);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String userId = rs.getString("user_id");
                String fname = rs.getString("full_name");
                String userType = rs.getString("type");
                String email = rs.getString("email");

                SessionManager.currentName = fname;
                SessionManager.currentUserIDKasir = userId;

                // Save session data
                String sessionQuery = "INSERT INTO session (user_id, full_name, email, status, login_time) VALUES (?, ?, ?, ?, NOW())";
                try (PreparedStatement sessionPst = con.prepareStatement(sessionQuery)) {
                    sessionPst.setString(1, userId);
                    sessionPst.setString(2, fname);
                    sessionPst.setString(3, email);
                    sessionPst.setString(4, userType.equals("user") ? "Active" : "Admin Active");
                    sessionPst.executeUpdate();
                }

                // Redirect user based on type
                if ("user".equals(userType)) {
                    javaswingdev.main.Main userDashboard = new javaswingdev.main.Main();
                    userDashboard.setUserType("user"); // Set userType ke "user"
                    userDashboard.setVisible(true);
                    userDashboard.pack();
                    userDashboard.setLocationRelativeTo(null);
                } else if ("admin".equals(userType)) {
                    javaswingdev.main.Main adminDashboard = new javaswingdev.main.Main();
                    adminDashboard.setUserType("admin"); // Set userType ke "admin"
                    adminDashboard.setVisible(true);
                    adminDashboard.pack();
                    adminDashboard.setLocationRelativeTo(null);
                }
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "RFID tidak terdaftar!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    private void signup(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signup
        SignUp SignUpFrame = new SignUp();
        SignUpFrame.setVisible(true);
        SignUpFrame.pack();
        SignUpFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_signup

    private void showpassword(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showpassword
        showpasswordActionPerformed(evt);
    }//GEN-LAST:event_showpassword

    private void btnloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnloginActionPerformed
        String Email, Password, query, fname = null, passDb = null, userType = null, userId = null;
        String SUrl, SUser, SPass;
        SUrl = "jdbc:MySQL://localhost:3306/studicase_pupuk";
        SUser = "root";
        SPass = "";
        int notFound = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);

            if ("".equals(email.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Email Address is required", "Error", JOptionPane.ERROR_MESSAGE);
            } else if ("".equals(password.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Password is required", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Email = email.getText();
                Password = hashPassword(password.getText());  // Hash input password sebelum pengecekan

                query = "SELECT * FROM user WHERE email = ?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, Email);

                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    userId = rs.getString("user_id"); // Ambil user_id sebagai String
                    passDb = rs.getString("password");
                    fname = rs.getString("full_name");
                    userType = rs.getString("type");
                    notFound = 1;
                }

                if (notFound == 1 && Password.equals(passDb)) {

                    currentUserId = userId;
                    System.out.println("User ID yang sedang login: " + userId);
                    SessionManager.currentName = fname;
                    SessionManager.currentUserIDKasir = userId;
                    System.out.println("Nama yang sedang login: " + fname);
                    System.out.println("Id yang sedang login: " + currentUserId);

                    // Simpan ke tabel session
                    String sessionQuery = "INSERT INTO session (user_id, full_name, email, status, login_time) VALUES (?, ?, ?, ?, NOW())";
                    PreparedStatement sessionPst = con.prepareStatement(sessionQuery);
                    sessionPst.setString(1, userId); // Simpan user_id sebagai String
                    sessionPst.setString(2, fname);
                    sessionPst.setString(3, Email);
                    sessionPst.setString(4, userType.equals("user") ? "Active" : "Admin Active");
                    sessionPst.executeUpdate();

                    // Redirect sesuai user type
                    if ("user".equals(userType)) {
                        javaswingdev.main.Main userDashboard = new javaswingdev.main.Main();
                        userDashboard.setUserType("user"); // Set userType ke "user"
                        userDashboard.setVisible(true);
                        userDashboard.pack();
                        userDashboard.setLocationRelativeTo(null);
                    } else if ("admin".equals(userType)) {
                        javaswingdev.main.Main adminDashboard = new javaswingdev.main.Main();
                        adminDashboard.setUserType("admin"); // Set userType ke "admin"
                        adminDashboard.setVisible(true);
                        adminDashboard.pack();
                        adminDashboard.setLocationRelativeTo(null);
                    }

                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Incorrect email or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
                password.setText(""); // Kosongkan password setelah login
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnloginActionPerformed

    private void showpasswordActionPerformed(java.awt.event.ActionEvent evt) {
        if (showpassword.isSelected()) {
            password.setEchoChar((char) 0); // Tampilkan password
        } else {
            password.setEchoChar('\u2022'); // Sembunyikan password dengan simbol peluru
        }
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Left;
    private javax.swing.JPanel Right;
    private button.MyButton btnlogin;
    private javax.swing.JTextField email;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField password;
    private javax.swing.JCheckBox showpassword;
    // End of variables declaration//GEN-END:variables
}
