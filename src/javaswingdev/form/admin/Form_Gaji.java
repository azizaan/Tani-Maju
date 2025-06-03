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
import java.text.DecimalFormat;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Form_Gaji extends javax.swing.JPanel {

    public Form_Gaji(String name) {
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
        model.addColumn("No");
        model.addColumn("Nama");
        model.addColumn("Bulan");
        model.addColumn("Tahun");
        model.addColumn("Total Hari Kerja");
        model.addColumn("Jumlah Hadir");
        model.addColumn("Jumlah Tidak Hadir");
        model.addColumn("Potongan Per Hari");
        model.addColumn("Total Potongan");
        model.addColumn("Gaji Diterima");

        loadData();

//        table.setEnabled(false);
    }

    private DefaultTableModel model;

    public String formatRupiah(int angka) {
        DecimalFormat formatRupiah = new DecimalFormat("#,###");
        return "Rp" + formatRupiah.format(angka);
    }

    public void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection c = Koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT u.full_name, r.bulan, r.tahun, r.total_hari_kerja, "
                    + "r.jumlah_hadir, r.jumlah_tidak_hadir, r.potongan_per_hari, "
                    + "r.total_potongan, r.gaji_diterima "
                    + "FROM rekap_absensi_bulanan r "
                    + "JOIN user u ON r.user_id = u.user_id";

            ResultSet r = s.executeQuery(sql);

            int no = 1;

            while (r.next()) {
                Object[] o = new Object[10];
                o[0] = no++;
                o[1] = r.getString("full_name");
                o[2] = r.getInt("bulan");
                o[3] = r.getInt("tahun");
                o[4] = r.getInt("total_hari_kerja");
                o[5] = r.getInt("jumlah_hadir");
                o[6] = r.getInt("jumlah_tidak_hadir");
                o[7] = formatRupiah(r.getInt("potongan_per_hari"));   // format rupiah
                o[8] = formatRupiah(r.getInt("total_potongan"));      // format rupiah
                o[9] = formatRupiah(r.getInt("gaji_diterima"));       // format rupiah

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
                Form_Gaji.this.mouseClicked(evt);
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 521, Short.MAX_VALUE)
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
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cari(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cari
        String keyword = txtcari.getText().trim();
        searchGaji(keyword);
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

    public void searchGaji(String keyword) {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection c = Koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT u.full_name, r.bulan, r.tahun, r.total_hari_kerja, "
                    + "r.jumlah_hadir, r.jumlah_tidak_hadir, r.potongan_per_hari, "
                    + "r.total_potongan, r.gaji_diterima "
                    + "FROM rekap_absensi_bulanan r "
                    + "JOIN user u ON r.user_id = u.user_id "
                    + "WHERE u.full_name LIKE '%" + keyword + "%' OR "
                    + "r.bulan LIKE '%" + keyword + "%' OR "
                    + "r.tahun LIKE '%" + keyword + "%'";

            ResultSet r = s.executeQuery(sql);

            int no = 1;

            while (r.next()) {
                Object[] o = new Object[10];
                o[0] = no++;
                o[1] = r.getString("full_name");
                o[2] = r.getInt("bulan");
                o[3] = r.getInt("tahun");
                o[4] = r.getInt("total_hari_kerja");
                o[5] = r.getInt("jumlah_hadir");
                o[6] = r.getInt("jumlah_tidak_hadir");
                o[7] = formatRupiah(r.getInt("potongan_per_hari"));
                o[8] = formatRupiah(r.getInt("total_potongan"));
                o[9] = formatRupiah(r.getInt("gaji_diterima"));

                model.addRow(o);
            }

            if (model.getRowCount() == 0) {
                model.addRow(new Object[]{"Tidak ada data yang relevan", "", "", "", "", "", "", "", "", ""});
            }

            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javaswingdev.swing.table.Table table;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
