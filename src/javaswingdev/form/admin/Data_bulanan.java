package javaswingdev.form.admin;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
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
import javax.swing.JTextField;
import java.sql.PreparedStatement; // Import PreparedStatement
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import java.sql.SQLException;
import java.util.UUID;
import javax.swing.JFileChooser;

public class Data_bulanan extends javax.swing.JPanel {

    public Data_bulanan(String name) {
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
        model.addColumn("ID Laporan");
        model.addColumn("Nama Laporan");
        model.addColumn("file_path");

        loadData();
//        table.setEnabled(false);
    }

    private DefaultTableModel model;

    public void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection c = Koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM laporan_bulanan";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                Object[] o = new Object[4];
                o[0] = r.getString("id_laporan");
                o[1] = r.getString("nama_laporan");
                o[2] = r.getString("file_path");

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
        btnTambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();

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
                Data_bulanan.this.mouseClicked(evt);
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

        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
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
                                .addGap(6, 6, 6)
                                .addComponent(btnTambah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEdit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapus)
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
                    .addComponent(btnTambah)
                    .addComponent(btnEdit)
                    .addComponent(btnHapus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cari(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cari
        String keyword = txtcari.getText().trim();
        searchLaporan(keyword);
    }//GEN-LAST:event_cari

    private void mouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseClicked

    }//GEN-LAST:event_mouseClicked


    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        String idLaporan = UUID.randomUUID().toString(); // Generate ID unik
        String namaLaporan = JOptionPane.showInputDialog("Masukkan Nama Laporan:");

        if (namaLaporan == null || namaLaporan.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nama laporan tidak boleh kosong.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Pilih File Laporan");
        int result = fileChooser.showOpenDialog(null);

        if (result != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(null, "Pemilihan file dibatalkan.");
            return;
        }

        String filePath = fileChooser.getSelectedFile().getAbsolutePath();

        try {
            Connection c = Koneksi.getKoneksi();
            PreparedStatement ps = c.prepareStatement("INSERT INTO laporan_bulanan (id_laporan, nama_laporan, file_path) VALUES (?, ?, ?)");
            ps.setString(1, idLaporan);
            ps.setString(2, namaLaporan);
            ps.setString(3, filePath);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Laporan berhasil ditambahkan.");
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menambah laporan: " + e.getMessage());
        }
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Pilih laporan yang ingin diedit.");
            return;
        }

        String idLaporan = (String) table.getValueAt(selectedRow, 0);
        String namaLama = (String) table.getValueAt(selectedRow, 1);
        String fileLama = (String) table.getValueAt(selectedRow, 2);

        String namaBaru = JOptionPane.showInputDialog("Edit Nama Laporan:", namaLama);
        if (namaBaru == null || namaBaru.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nama laporan tidak boleh kosong.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Pilih File Baru (opsional)");
        int result = fileChooser.showOpenDialog(null);
        String fileBaru = fileLama;

        if (result == JFileChooser.APPROVE_OPTION) {
            fileBaru = fileChooser.getSelectedFile().getAbsolutePath();
        }

        try {
            Connection c = Koneksi.getKoneksi();
            PreparedStatement ps = c.prepareStatement("UPDATE laporan_bulanan SET nama_laporan = ?, file_path = ? WHERE id_laporan = ?");
            ps.setString(1, namaBaru);
            ps.setString(2, fileBaru);
            ps.setString(3, idLaporan);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Laporan berhasil diperbarui.");
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal memperbarui laporan: " + e.getMessage());
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Pilih laporan yang ingin dihapus.");
            return;
        }

        String idLaporan = (String) table.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus laporan ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            Connection c = Koneksi.getKoneksi();
            PreparedStatement ps = c.prepareStatement("DELETE FROM laporan_bulanan WHERE id_laporan = ?");
            ps.setString(1, idLaporan);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Laporan berhasil dihapus.");
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menghapus laporan: " + e.getMessage());
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    public void searchLaporan(String keyword) {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection c = Koneksi.getKoneksi();

            // Query pencarian untuk laporan bulanan
            String sql = "SELECT id_laporan, nama_laporan, file_path FROM laporan_bulanan WHERE "
                    + "id_laporan LIKE ? OR "
                    + "nama_laporan LIKE ? OR "
                    + "file_path LIKE ?";

            PreparedStatement ps = c.prepareStatement(sql);
            for (int i = 1; i <= 3; i++) {
                ps.setString(i, "%" + keyword + "%");
            }

            ResultSet r = ps.executeQuery();

            boolean dataDitemukan = false;

            while (r.next()) {
                Object[] o = new Object[3]; // Sesuaikan dengan jumlah kolom laporan_bulanan
                o[0] = r.getString("id_laporan");
                o[1] = r.getString("nama_laporan");
                o[2] = r.getString("file_path");

                model.addRow(o);
                dataDitemukan = true;
            }

            // Jika tidak ada data yang ditemukan, tambahkan pesan ke tabel
            if (!dataDitemukan) {
                model.addRow(new Object[]{"Tidak ada data yang relevan", "", ""});
            }

            r.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javaswingdev.swing.table.Table table;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
