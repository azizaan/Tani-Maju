package javaswingdev.form.user;

import javaswingdev.form.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class Form_Transaksi_User extends javax.swing.JPanel {

    public Form_Transaksi_User(String name) {
        initComponents();
        lb.setText("Form " + name);
        model = new DefaultTableModel();

        table.setModel(model);

        // Menghapus kolom password dari tabel
        model.addColumn("User ID");
        model.addColumn("Full name");
        model.addColumn("Email");
        model.addColumn("Type");

        loadData();

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
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javaswingdev.swing.table.Table();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setOpaque(false);

        lb.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        lb.setForeground(new java.awt.Color(125, 125, 125));
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Form Kasir");

        jButton1.setText("Tambah");

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
        jScrollPane1.setViewportView(table);

        jButton2.setText("Edit");

        jButton3.setText("Hapus");

        txtcari.setBackground(new java.awt.Color(255, 255, 255));
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cari(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Cari Data");

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
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
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
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cari(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cari
        String keyword = txtcari.getText().trim();
        searchUser(keyword);
    }//GEN-LAST:event_cari

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

            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javaswingdev.swing.table.Table table;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
