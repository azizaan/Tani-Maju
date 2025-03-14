package javaswingdev.form.admin;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.Dimension;
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

public class Data_Transaksi_Admin extends javax.swing.JPanel {

    public Data_Transaksi_Admin(String name) {
        initComponents();
        lb.setText("Data Transaksi " + name);
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Mencegah edit langsung di tabel
            }
        };

        table.setModel(model);

        // Menghapus kolom password dari tabel
        model.addColumn("ID Transaksi");
        model.addColumn("Nama Kasir");
        model.addColumn("Nama Pembeli");
        model.addColumn("Total Harga");
        model.addColumn("Diskon");

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

            // Query hanya untuk data transaksi tanpa pupuk
            String sql = "SELECT t.transaksi_id, u.full_name AS nama_kasir, "
                    + "t.nama_pembeli, t.total_harga, t.diskon "
                    + "FROM transaksi t "
                    + "JOIN user u ON t.user_id = u.user_id";

            ResultSet r = s.executeQuery(sql);

            // Format angka ke Rupiah
            NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

            while (r.next()) {
                Object[] o = new Object[5]; // Hanya 5 kolom, tanpa nama_pupuk
                o[0] = r.getString("transaksi_id");
                o[1] = r.getString("nama_kasir");
                o[2] = r.getString("nama_pembeli");

                // Konversi total harga ke format Rupiah
                double totalHarga = r.getDouble("total_harga");
                o[3] = rupiahFormat.format(totalHarga);

                o[4] = r.getInt("diskon"); // Diskon dalam bentuk integer

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
        btnCetak = new javax.swing.JButton();

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
                Data_Transaksi_Admin.this.mouseClicked(evt);
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

        btnCetak.setText("Cetak");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
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
                                .addGap(259, 259, 259)
                                .addComponent(btnCetak)
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
                    .addComponent(btnCetak))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cari(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cari
        String keyword = txtcari.getText().trim();
        searchUser(keyword);
    }//GEN-LAST:event_cari
    private String selectedIdPupuk;
    private String selectedNamaPupuk;
    private String selectedHargaPupuk;
    private String selectedKodePupuk;


    private void mouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseClicked
        int row = table.getSelectedRow();

        // Cek apakah ada baris yang dipilih
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row from the table.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Ambil transaksi_id dari tabel transaksi
        String transaksiId = table.getValueAt(row, 0).toString();

        try {
            Connection con = Koneksi.getKoneksi();
            String sql = "SELECT p.nama_pupuk, td.jumlah_beli "
                    + "FROM transaksi_detail td "
                    + "JOIN data_pupuk p ON td.id_pupuk = p.id_pupuk "
                    + "WHERE td.transaksi_id = ?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, transaksiId);
            ResultSet rs = pst.executeQuery();

            // Model tabel untuk menampilkan data dalam JOptionPane
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Nama Pupuk");
            model.addColumn("Jumlah Beli");

            int rowCount = 0; // Hitung jumlah baris data
            while (rs.next()) {
                Object[] rowData = {
                    rs.getString("nama_pupuk"),
                    rs.getInt("jumlah_beli")
                };
                model.addRow(rowData);
                rowCount++;
            }

            rs.close();
            pst.close();
            con.close();

            // Jika ada data, tampilkan dalam tabel JOptionPane
            if (rowCount > 0) {
                JTable tablePupuk = new JTable(model);
                tablePupuk.setEnabled(false); // Agar data tidak bisa diedit oleh pengguna

                // Buat JScrollPane dengan ukuran dinamis
                JScrollPane scrollPane = new JScrollPane(tablePupuk);

                // Menyesuaikan tinggi berdasarkan jumlah baris (maksimal 5 baris)
                int rowHeight = tablePupuk.getRowHeight();
                int height = Math.min(rowHeight * rowCount + 30, rowHeight * 5 + 30); // Maksimal 5 baris sebelum perlu scroll

                // Menentukan lebar tabel otomatis berdasarkan konten
                tablePupuk.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                tablePupuk.setPreferredScrollableViewportSize(new Dimension(300, height));

                JOptionPane.showMessageDialog(null, scrollPane, "Detail Pupuk dalam Transaksi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Tidak ada pupuk dalam transaksi ini.",
                        "Detail Pupuk", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil data pupuk: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_mouseClicked

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        // Pilih opsi cetak
        String[] options = {"Cetak Satu", "Cetak Beberapa", "Cetak Semua"};
        int choice = JOptionPane.showOptionDialog(null, "Pilih opsi cetak:", "Opsi Cetak",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        List<String> idPupukList = new ArrayList<>();

        if (choice == 0) { // Cetak Satu
            List<String> allIds = getAllPupukIds();
            String selected = (String) JOptionPane.showInputDialog(null, "Pilih pupuk yang ingin dicetak:", "Pilih Pupuk",
                    JOptionPane.QUESTION_MESSAGE, null,
                    allIds.toArray(), allIds.get(0));
            if (selected != null) {
                idPupukList.add(selected);
            }
        } else if (choice == 1) { // Cetak Beberapa dengan JTable
            List<String> allIds = getAllPupukIds();
            DefaultTableModel model = new DefaultTableModel(new Object[]{"Pilih", "ID Pupuk"}, 0);
            for (String id : allIds) {
                model.addRow(new Object[]{false, id});
            }

            JTable table = new JTable(model) {
                @Override
                public Class<?> getColumnClass(int column) {
                    return column == 0 ? Boolean.class : String.class;
                }
            };

            JScrollPane scrollPane = new JScrollPane(table);
            int confirm = JOptionPane.showConfirmDialog(null, scrollPane, "Pilih Pupuk untuk Dicetak", JOptionPane.OK_CANCEL_OPTION);

            if (confirm == JOptionPane.OK_OPTION) {
                for (int i = 0; i < model.getRowCount(); i++) {
                    if ((Boolean) model.getValueAt(i, 0)) {
                        idPupukList.add((String) model.getValueAt(i, 1));
                    }
                }
            }
        } else if (choice == 2) { // Cetak Semua
            idPupukList.addAll(getAllPupukIds());
        }

        if (idPupukList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tidak ada data untuk dicetak.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cetak semua barcode dalam daftar
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new Printable() {
            @Override
            public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
                int barcodesPerPage = 5; // Sesuaikan dengan ukuran halaman dan barcode
                int barcodeHeight = 100; // Perkiraan tinggi barcode dengan teks
                int startY = (int) pf.getImageableY();
                int startX = (int) pf.getImageableX();
                int barcodeWidth = 200;

                int startIndex = pageIndex * barcodesPerPage;
                if (startIndex >= idPupukList.size()) {
                    return Printable.NO_SUCH_PAGE;
                }

                for (int i = 0; i < barcodesPerPage && (startIndex + i) < idPupukList.size(); i++) {
                    String idPupuk = idPupukList.get(startIndex + i);
                    String barcodePath = BarcodeGenerator.saveBarcodeImage(idPupuk);
                    if (barcodePath == null) {
                        return Printable.NO_SUCH_PAGE;
                    }

                    try {
                        BufferedImage barcode = ImageIO.read(new File(barcodePath));
                        g.drawImage(barcode, startX, startY + (i * barcodeHeight), barcodeWidth, barcodeHeight - 20, null);

                        // Tambahkan teks ID di bawah barcode
                        g.setFont(new Font("Arial", Font.BOLD, 12));
                        g.drawString(idPupuk, startX + (barcodeWidth / 2) - 20, startY + (i * barcodeHeight) + barcodeHeight - 5);

                    } catch (Exception e) {
                        return Printable.NO_SUCH_PAGE;
                    }
                }
                return Printable.PAGE_EXISTS;
            }
        });

        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                job.print();
                JOptionPane.showMessageDialog(null, "Barcode berhasil dicetak!");
            } catch (PrinterException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mencetak: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnCetakActionPerformed

    private List<String> getAllPupukIds() {
        List<String> pupukIds = new ArrayList<>();

        try {
            Connection con = Koneksi.getKoneksi(); // Pastikan metode Koneksi.getKoneksi() sesuai dengan proyek Anda
            String sql = "SELECT id_pupuk FROM data_pupuk"; // Sesuaikan nama tabel dan kolom dengan database Anda
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                pupukIds.add(rs.getString("id_pupuk"));
            }

            rs.close();
            pst.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil data pupuk: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return pupukIds;
    }

    public void searchUser(String keyword) {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection c = Koneksi.getKoneksi();
            Statement s = c.createStatement();

            // Query pencarian untuk tabel user
            String sql = "SELECT * FROM data_pupuk WHERE "
                    + "id_pupuk LIKE '%" + keyword + "%' OR "
                    + "nama_pupuk LIKE '%" + keyword + "%' OR "
                    + "harga_pupuk LIKE '%" + keyword + "%' OR "
                    + "kode_pupuk LIKE '%" + keyword + "%'";
            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                Object[] o = new Object[4]; // Sesuaikan ukuran array menjadi 4 (tanpa password)
                o[0] = r.getString("id_pupuk");
                o[1] = r.getString("nama_pupuk");
                o[2] = r.getString("harga_pupuk");
                o[3] = r.getString("kode_pupuk");

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
    private javax.swing.JButton btnCetak;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javaswingdev.swing.table.Table table;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
