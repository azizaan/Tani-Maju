package javaswingdev.form.admin;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javaswingdev.form.admin.PasswordUtil;
import javaswingdev.form.Koneksi;
import javax.swing.table.DefaultTableModel;
import java.sql.DriverManager;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Form_User extends javax.swing.JPanel {

    public Form_User(String name) {
        initComponents();
        lb.setText("Form " + name);
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Mencegah edit langsung di tabel
            }
        };

        table.setModel(model);

        // Menghapus kolom password dari tabel
        model.addColumn("User ID");
        model.addColumn("Full name");
        model.addColumn("Email");
        model.addColumn("Type");

        loadData();

        btn_tambah.setText("+ Add");
        btn_tambah.setColor(new Color(47, 128, 237)); // #2F80ED
        btn_tambah.setColorOver(new Color(26, 106, 211)); // Lebih gelap saat hover
        btn_tambah.setColorClick(new Color(15, 90, 190)); // Saat klik
        btn_tambah.setBorderColor(new Color(47, 128, 237));
        btn_tambah.setForeground(Color.WHITE);
        btn_tambah.setRadius(20);

// Tombol EDIT
        btn_edit.setText("✎ Edit"); // Bisa pakai unicode pensil atau icon nanti
        btn_edit.setColor(Color.WHITE);
        btn_edit.setColorOver(new Color(245, 245, 245)); // hover abu muda
        btn_edit.setColorClick(new Color(230, 230, 230));
        btn_edit.setBorderColor(new Color(189, 189, 189)); // #BDBDBD
        btn_edit.setForeground(new Color(79, 79, 79)); // #4F4F4F
        btn_edit.setRadius(20);

// Tombol DELETE
        btn_hapus.setText("🗑 Delete"); // Bisa pakai unicode icon, atau icon image nanti
        btn_hapus.setColor(Color.WHITE);
        btn_hapus.setColorOver(new Color(255, 230, 230)); // hover merah muda
        btn_hapus.setColorClick(new Color(255, 200, 200));
        btn_hapus.setBorderColor(new Color(234, 84, 85)); // Mirip merahnya
        btn_hapus.setForeground(new Color(235, 87, 87)); // #EB5757
        btn_hapus.setRadius(20);
//        table.setEnabled(false);
    }

    private DefaultTableModel model;

    public void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection c = Koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM user";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                Object[] o = new Object[4]; // Sesuaikan ukuran array menjadi 4 (tanpa password)
                o[0] = r.getString("user_id");
                o[1] = r.getString("full_name");
                o[2] = r.getString("email");
                o[3] = r.getString("type");

                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javaswingdev.swing.table.Table();
        txtcari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_tambah = new button.MyButton();
        btn_edit = new button.MyButton();
        btn_hapus = new button.MyButton();

        setOpaque(false);

        lb.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        lb.setForeground(new java.awt.Color(125, 125, 125));
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Data User");

        table.setForeground(new java.awt.Color(0, 0, 255));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Name", "Email", "Position", "Date Join"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Form_User.this.mouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        txtcari.setBackground(new java.awt.Color(255, 255, 255));
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cari(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Cari Data");

        btn_tambah.setText("tambah");
        btn_tambah.setBorderPainted(false);
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_edit.setText("edit");
        btn_edit.setBorderPainted(false);
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_hapus.setText("edit");
        btn_hapus.setBorderPainted(false);
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cari(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cari
        String keyword = txtcari.getText().trim();
        searchUser(keyword);
    }//GEN-LAST:event_cari
    private String selectedUserId;
    private String selectedFullName;
    private String selectedEmail;
    private String selectedUserType;


    private void mouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseClicked
        int row = table.getSelectedRow();

        // Cek apakah ada baris yang dipilih
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row from the table.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
//        if (table.getColumnCount() > 4) {
//            selectedUserType = table.getValueAt(row, 3).toString();
//        } else {
//            JOptionPane.showMessageDialog(null, "User type column is not available.", "Error", JOptionPane.ERROR_MESSAGE);
//        }

        // Jika baris valid, ambil data
        selectedUserId = table.getValueAt(row, 0).toString();
        selectedFullName = table.getValueAt(row, 1).toString();
        selectedEmail = table.getValueAt(row, 2).toString();
        selectedUserType = table.getValueAt(row, 3).toString();
    }//GEN-LAST:event_mouseClicked

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        String fullName, email, Password, query, userType;
        String userId = null;
        String SUrl, SUser, SPass;
        SUrl = "jdbc:MySQL://localhost:3306/studicase_pupuk";
        SUser = "root";
        SPass = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
            Statement st = con.createStatement();

            // Membuat panel untuk input data
            JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
            JTextField tfFullName = new JTextField(15);
            JTextField tfEmail = new JTextField(15);
            JPasswordField pfPassword = new JPasswordField(15);
            JCheckBox showPassword = new JCheckBox("Show Password");

            // ComboBox untuk memilih Type
            String[] userTypes = {"user", "admin"};
            JComboBox<String> cbUserType = new JComboBox<>(userTypes);

            // Menambahkan komponen ke panel
            panel.add(new JLabel("Full Name:"));
            panel.add(tfFullName);
            panel.add(new JLabel("Email Address:"));
            panel.add(tfEmail);
            panel.add(new JLabel("Password:"));
            panel.add(pfPassword);
            panel.add(new JLabel("")); // Kosong untuk posisi
            panel.add(showPassword);
            panel.add(new JLabel("Type:"));
            panel.add(cbUserType);

            // Event Listener untuk Show Password
            showPassword.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (showPassword.isSelected()) {
                        pfPassword.setEchoChar((char) 0); // Tampilkan password
                    } else {
                        pfPassword.setEchoChar('\u2022'); // Sembunyikan dengan simbol peluru
                    }
                }
            });

            int result = JOptionPane.showConfirmDialog(null, panel,
                    "Enter New User Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                fullName = tfFullName.getText().trim();
                email = tfEmail.getText().trim();
                Password = new String(pfPassword.getPassword()).trim();
                userType = cbUserType.getSelectedItem().toString();

                // Validasi Full Name
                if (fullName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Full Name is required", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (!fullName.matches("[a-zA-Z ]+")) {
                    JOptionPane.showMessageDialog(null, "Full Name must only contain letters and spaces", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validasi Email
                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Email Address is required", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validasi Password
                if (Password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Password is required", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Hash password
                Password = PasswordUtil.hashPassword(Password);

                // Cari user_id terakhir
                String getLastIdQuery = "SELECT user_id FROM user ORDER BY user_id DESC LIMIT 1";
                ResultSet rs = st.executeQuery(getLastIdQuery);

                if (rs.next()) {
                    String lastId = rs.getString("user_id");
                    if (lastId != null && lastId.length() >= 4) {
                        int num = Integer.parseInt(lastId.substring(4));
                        userId = String.format("user%03d", num + 1);
                    } else {
                        userId = "user001";
                    }
                } else {
                    userId = "user001";
                }

                // Query insert data baru dengan type dari ComboBox
                query = "INSERT INTO user (user_id, full_name, email, password, type) "
                        + "VALUES ('" + userId + "', '" + fullName + "', '" + email + "', '" + Password + "', '" + userType + "')";

                st.execute(query);

                JOptionPane.showMessageDialog(null, "New user has been added successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "User registration canceled.");
            }
        } catch (Exception e) {
            System.out.println("Error! " + e.getMessage());
        }

    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        String fullName, email, Password, query, userType;
        String SUrl, SUser, SPass;
        SUrl = "jdbc:MySQL://localhost:3306/studicase_pupuk";
        SUser = "root";
        SPass = "";

        // Cek apakah sudah memilih data
        if (selectedUserId == null) {
            JOptionPane.showMessageDialog(null, "Please select a user to edit", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
            Statement st = con.createStatement();

            // Membuat panel untuk input data
            JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
            JTextField tfFullName = new JTextField(15);
            JTextField tfEmail = new JTextField(15);
            JPasswordField pfPassword = new JPasswordField(15);
            JCheckBox showPassword = new JCheckBox("Show Password");

            // ComboBox untuk memilih Type
            String[] userTypes = {"user", "admin"};
            JComboBox<String> cbUserType = new JComboBox<>(userTypes);

            // Mengisi field dengan data dari variabel global
            tfFullName.setText(selectedFullName);
            tfEmail.setText(selectedEmail);
            cbUserType.setSelectedItem(selectedUserType);

            // Menambahkan komponen ke panel
            panel.add(new JLabel("Full Name:"));
            panel.add(tfFullName);
            panel.add(new JLabel("Email Address:"));
            panel.add(tfEmail);
            panel.add(new JLabel("New Password:"));
            panel.add(pfPassword);
            panel.add(new JLabel("")); // Kosong untuk posisi
            panel.add(showPassword);
            panel.add(new JLabel("Type:"));
            panel.add(cbUserType);

            // Event Listener untuk Show Password
            showPassword.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (showPassword.isSelected()) {
                        pfPassword.setEchoChar((char) 0); // Tampilkan password
                    } else {
                        pfPassword.setEchoChar('\u2022'); // Sembunyikan dengan simbol peluru
                    }
                }
            });

            int result = JOptionPane.showConfirmDialog(null, panel,
                    "Edit User Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                fullName = tfFullName.getText().trim();
                email = tfEmail.getText().trim();
                Password = new String(pfPassword.getPassword()).trim();
                userType = cbUserType.getSelectedItem().toString();

                // Validasi input
                if (fullName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Full Name is required", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (!fullName.matches("[a-zA-Z ]+")) {
                    JOptionPane.showMessageDialog(null, "Full Name must only contain letters and spaces", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Email Address is required", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Jika password diisi, hash password baru
                if (!Password.isEmpty()) {
                    Password = PasswordUtil.hashPassword(Password);
                    query = "UPDATE user SET full_name = '" + fullName + "', email = '" + email + "', "
                            + "password = '" + Password + "', type = '" + userType + "' WHERE user_id = '" + selectedUserId + "'";
                } else {
                    query = "UPDATE user SET full_name = '" + fullName + "', email = '" + email + "', "
                            + "type = '" + userType + "' WHERE user_id = '" + selectedUserId + "'";
                }

                st.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "User data has been updated successfully!");
                // Refresh tabel setelah update
                loadData();
            } else {
                JOptionPane.showMessageDialog(null, "Edit canceled.");
            }
        } catch (Exception e) {
            System.out.println("Error! " + e.getMessage());
        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // Cek apakah ada user yang dipilih
        if (selectedUserId == null || selectedUserId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a user to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Konfirmasi penghapusan
        int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete this user?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Connection c = Koneksi.getKoneksi();
                Statement s = c.createStatement();

                // Query untuk menghapus data berdasarkan user_id
                String sql = "DELETE FROM user WHERE user_id = '" + selectedUserId + "'";
                int rowsAffected = s.executeUpdate(sql);

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "User has been deleted successfully.");

                    // Refresh tabel setelah data dihapus
                    searchUser("");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete user. User not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                s.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_btn_hapusActionPerformed

    public void searchUser(String keyword) {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection c = Koneksi.getKoneksi();
            Statement s = c.createStatement();

            // Query pencarian untuk tabel user
            String sql = "SELECT * FROM user WHERE "
                    + "user_id LIKE '%" + keyword + "%' OR "
                    + "full_name LIKE '%" + keyword + "%' OR "
                    + "email LIKE '%" + keyword + "%' OR "
                    + "type LIKE '%" + keyword + "%'";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                Object[] o = new Object[4]; // Sesuaikan ukuran array menjadi 4 (tanpa password)
                o[0] = r.getString("user_id");
                o[1] = r.getString("full_name");
                o[2] = r.getString("email");
                o[3] = r.getString("type");

                model.addRow(o);
            }
            if (model.getRowCount() == 0) {
                // Menambah baris dengan pesan "Tidak ada data yang relevan"
                table.addRow(new Object[]{"Tidak ada data yang relevan", "", "", ""});
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.MyButton btn_edit;
    private button.MyButton btn_hapus;
    private button.MyButton btn_tambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javaswingdev.swing.table.Table table;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
