package javaswingdev.loginUI.component;

import javaswingdev.loginUI.swing.Button;
import javaswingdev.loginUI.swing.MyPasswordField;
import javaswingdev.loginUI.swing.MyTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static javaswingdev.login.Login.currentUserId;
import javaswingdev.login.SessionManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    private MyTextField txtUserRegister;
    private MyTextField txtEmailRegister;
    private MyPasswordField txtPassRegister;
    private JCheckBox chkShowPassRegister;

    private MyTextField txtEmailLogin;
    private MyPasswordField txtPassLogin;
    private JCheckBox chkShowPassLogin;

    public PanelLoginAndRegister() {
        initComponents();
        initRegister();
        initLogin();
        login.setVisible(false);
        register.setVisible(true);
    }

    private void initRegister() {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        register.add(label);

        txtUserRegister = new MyTextField();
        txtUserRegister.setPrefixIcon(new ImageIcon(getClass().getResource("/javaswingdev/loginUI/icon/user.png")));
        txtUserRegister.setHint("Name");
        register.add(txtUserRegister, "w 60%");

        txtEmailRegister = new MyTextField();
        txtEmailRegister.setPrefixIcon(new ImageIcon(getClass().getResource("/javaswingdev/loginUI/icon/mail.png")));
        txtEmailRegister.setHint("Email");
        register.add(txtEmailRegister, "w 60%");

        txtPassRegister = new MyPasswordField();
        txtPassRegister.setPrefixIcon(new ImageIcon(getClass().getResource("/javaswingdev/loginUI/icon/pass.png")));
        txtPassRegister.setHint("Password");
        register.add(txtPassRegister, "w 60%");

        // Tambahkan checkbox "Show Password"
        chkShowPassRegister = new JCheckBox("Show Password");
        chkShowPassRegister.setForeground(new Color(100, 100, 100));
        register.add(chkShowPassRegister, "w 60%");

        Button cmd = new Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN UP");
        register.add(cmd, "w 40%, h 40");

        // Event listener untuk tombol register
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        // Event listener untuk checkbox show password
        chkShowPassRegister.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                txtPassRegister.setEchoChar(chkShowPassRegister.isSelected() ? (char) 0 : '•');
            }
        });
    }

    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(7, 164, 121));
        login.add(label);

        txtEmailLogin = new MyTextField();
        txtEmailLogin.setPrefixIcon(new ImageIcon(getClass().getResource("/javaswingdev/loginUI/icon/mail.png")));
        txtEmailLogin.setHint("Email");
        login.add(txtEmailLogin, "w 60%");

        txtPassLogin = new MyPasswordField();
        txtPassLogin.setPrefixIcon(new ImageIcon(getClass().getResource("/javaswingdev/loginUI/icon/pass.png")));
        txtPassLogin.setHint("Password");
        login.add(txtPassLogin, "w 60%");

        // Tambahkan checkbox "Show Password"
        chkShowPassLogin = new JCheckBox("Show Password");
        chkShowPassLogin.setForeground(new Color(100, 100, 100));
        login.add(chkShowPassLogin, "w 60%");

        JButton cmdForget = new JButton("Forgot your password ?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);

        Button cmd = new Button();
        cmd.setBackground(new Color(7, 164, 121));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN IN");
        login.add(cmd, "w 40%, h 40");

        // Event listener untuk tombol login
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        chkShowPassLogin.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                txtPassLogin.setEchoChar(chkShowPassLogin.isSelected() ? (char) 0 : '•');
            }
        });
    }

    private void registerUser() {
        String username = txtUserRegister.getText().trim();
        String email = txtEmailRegister.getText().trim();
        String password = new String(txtPassRegister.getPassword());

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Simpan data user (bisa ke database)
        JOptionPane.showMessageDialog(this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
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

    private void loginUser() {
        String Email, Password, query, fname = null, passDb = null, userType = null, userId = null;
        String SUrl, SUser, SPass;
        SUrl = "jdbc:MySQL://localhost:3306/studicase_pupuk";
        SUser = "root";
        SPass = "";
        int notFound = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
            System.out.println("Password: " + txtPassLogin.getText());

            if ("".equals(txtEmailLogin.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Email Address is required", "Error", JOptionPane.ERROR_MESSAGE);
            } else if ("".equals(txtPassLogin.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Password is required", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Email = txtEmailLogin.getText();
                Password = hashPassword(txtPassLogin.getText());  // Hash input password sebelum pengecekan

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
                    sessionPst.setString(1, userId);
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

                    // Tutup halaman login setelah login berhasil
                    javax.swing.SwingUtilities.getWindowAncestor(this).dispose();

                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Incorrect email or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
