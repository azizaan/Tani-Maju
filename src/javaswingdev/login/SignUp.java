package javaswingdev.login;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JTextField;
import java.sql.PreparedStatement; // Import PreparedStatement

public class SignUp extends javax.swing.JFrame {

    private JTextField rfidInput;
    
    public SignUp() {
        initComponents();
        setupRFIDListener();
        rfidInput.requestFocusInWindow();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        emailAddress = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        SignUpBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sign Up");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/LOGO putih_1.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("copyright © Tani Maju Sejahtera All rights reserved");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(67, 67, 67))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel3)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addContainerGap(165, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 400, 550);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(0, 102, 102));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText("SIGN UP");

        jLabel5.setBackground(new java.awt.Color(102, 102, 102));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Full name");

        fname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fname.setForeground(new java.awt.Color(102, 102, 102));

        jLabel6.setBackground(new java.awt.Color(102, 102, 102));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Email");

        emailAddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        emailAddress.setForeground(new java.awt.Color(102, 102, 102));

        jLabel7.setBackground(new java.awt.Color(102, 102, 102));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Password");

        pass.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pass.setForeground(new java.awt.Color(102, 102, 102));

        jLabel8.setText("I've an account");

        SignUpBtn.setBackground(new java.awt.Color(0, 102, 102));
        SignUpBtn.setForeground(new java.awt.Color(255, 255, 255));
        SignUpBtn.setText("Sign Up");
        SignUpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpBtnActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("LOGIN");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Login(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jLabel4))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5)
                                .addComponent(fname)
                                .addComponent(jLabel6)
                                .addComponent(emailAddress)
                                .addComponent(jLabel7)
                                .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(SignUpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(SignUpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel2))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(400, 0, 400, 500);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setupRFIDListener() {
        rfidInput = new JTextField();
        rfidInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String rfidTag = rfidInput.getText().trim();
                    if (!rfidTag.isEmpty()) {
                        registerRFID(rfidTag);
                    }
                    rfidInput.setText("");
                }
            }
        });
        this.add(rfidInput);
    }

    private void registerRFID(String rfidTag) {
        String queryCheck = "SELECT * FROM user WHERE rfid = ?";
        String queryInsert = "INSERT INTO user (rfid, full_name, email, password, type) VALUES (?, ?, ?, ?, ?)";
        String SUrl = "jdbc:mysql://localhost:3306/studicase_pupuk";
        String SUser = "root";
        String SPass = "";

        try (Connection con = DriverManager.getConnection(SUrl, SUser, SPass); PreparedStatement checkPst = con.prepareStatement(queryCheck); PreparedStatement pstmtInsert = con.prepareStatement(queryInsert, Statement.RETURN_GENERATED_KEYS)) {

            checkPst.setString(1, rfidTag);
            ResultSet rs = checkPst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "RFID sudah terdaftar!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String fullName = JOptionPane.showInputDialog(this, "Masukkan Nama Lengkap:");
                String email = JOptionPane.showInputDialog(this, "Masukkan Email:");
                String password = JOptionPane.showInputDialog(this, "Masukkan Password:");
                String[] userTypes = {"user", "admin"};
                String userType = (String) JOptionPane.showInputDialog(this, "Pilih Tipe Pengguna:",
                        "Tipe Pengguna", JOptionPane.QUESTION_MESSAGE, null, userTypes, userTypes[0]);

                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(this, "Email tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                password = PasswordUtil.hashPassword(password);

                try (PreparedStatement insertPst = con.prepareStatement(queryInsert)) {
                    insertPst.setString(1, rfidTag);
                    insertPst.setString(2, fullName);
                    insertPst.setString(3, email);
                    insertPst.setString(4, password);
                    insertPst.setString(5, userType);
                    insertPst.executeUpdate();
                }

                JOptionPane.showMessageDialog(this, "Pendaftaran berhasil!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void kembaliKeLogin() {
        this.dispose(); // Menutup form registrasi
        Login loginForm = new Login(); // Membuka form login
        loginForm.setVisible(true);
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static class PasswordUtil {

        public static String hashPassword(String password) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hashedBytes = md.digest(password.getBytes());
                StringBuilder sb = new StringBuilder();
                for (byte b : hashedBytes) {
                    sb.append(String.format("%02x", b));
                }
                return sb.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Error hashing password", e);
            }
        }
    }


    private void SignUpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUpBtnActionPerformed
        // System.out.println("Sign up btn clicked");
        String fullName, email, Password, query;
        String userId = null; // Tambahkan variabel untuk user_id
        String SUrl, SUser, SPass;
        SUrl = "jdbc:MySQL://localhost:3306/studicase_pupuk";
        SUser = "root";
        SPass = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
            Statement st = con.createStatement();

            // Periksa apakah inputan kosong
            if ("".equals(fname.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Full Name is required", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!fname.getText().matches("[a-zA-Z ]+")) {
                // Validasi hanya huruf dan spasi
                JOptionPane.showMessageDialog(new JFrame(), "Full Name must only contain letters and spaces", "Error", JOptionPane.ERROR_MESSAGE);
            } else if ("".equals(emailAddress.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Email Address is required", "Error", JOptionPane.ERROR_MESSAGE);
            } else if ("".equals(pass.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Password is required", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Cari user_id terakhir
                String getLastIdQuery = "SELECT user_id FROM user ORDER BY user_id DESC LIMIT 1";
                ResultSet rs = st.executeQuery(getLastIdQuery);

                if (rs.next()) {
                    String lastId = rs.getString("user_id");
                    if (lastId != null && lastId.length() >= 4) {
                        int num = Integer.parseInt(lastId.substring(4)); // Ambil angka dari userXXX
                        userId = String.format("user%03d", num + 1); // Format ke userXXX
                    } else {
                        // Jika ID tidak valid, mulai dari user001
                        userId = "user001";
                    }
                } else {
                    // Jika tabel kosong, mulai dari user001
                    userId = "user001";
                }

                // Ambil input pengguna
                fullName = fname.getText();
                email = emailAddress.getText();

                // Hash password
                Password = PasswordUtil.hashPassword(pass.getText());

                // Query insert data baru
                query = "INSERT INTO user (user_id, full_name, email, password, type) "
                        + "VALUES ('" + userId + "', '" + fullName + "', '" + email + "', '" + Password + "', 'user')";

                st.execute(query);

                // Reset input form
                fname.setText("");
                emailAddress.setText("");
                pass.setText("");
                JOptionPane.showMessageDialog(null, "New account has been created successfully!");
            }
        } catch (Exception e) {
            System.out.println("Error!" + e.getMessage());
        }

    }//GEN-LAST:event_SignUpBtnActionPerformed

    private void Login(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Login
        Login LoginFrame = new Login();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_Login

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SignUpBtn;
    private javax.swing.JTextField emailAddress;
    private javax.swing.JTextField fname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField pass;
    // End of variables declaration//GEN-END:variables
}
