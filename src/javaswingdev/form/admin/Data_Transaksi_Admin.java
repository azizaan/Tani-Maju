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
        lb.setText("Form " + name);
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
        model.addColumn("Nama Pupuk");
        model.addColumn("Total Harga");
        model.addColumn("Diskon");

//        loadData();
//        table.setEnabled(false);
    }

    private DefaultTableModel model;

    public void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection c = Koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM data_pupuk";
            ResultSet r = s.executeQuery(sql);

            // Format angka ke Rupiah
            NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

            while (r.next()) {
                Object[] o = new Object[4]; // Sesuaikan ukuran array menjadi 4 (tanpa password)
                o[0] = r.getString("id_pupuk");
                o[1] = r.getString("nama_pupuk");

                // Ambil harga dari database dan ubah ke format Rupiah
                double harga = r.getDouble("harga_pupuk");
                o[2] = rupiahFormat.format(harga);

                o[3] = r.getString("kode_pupuk");

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
        btnTambah = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javaswingdev.swing.table.Table();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnCetak = new javax.swing.JButton();

        setOpaque(false);

        lb.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        lb.setForeground(new java.awt.Color(125, 125, 125));
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Data User");

        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

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
                                .addComponent(btnTambah)
                                .addGap(18, 18, 18)
                                .addComponent(btnEdit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                    .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit)
                    .addComponent(btnHapus)
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


    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        String namaPupuk, hargaPupuk, kodePupuk, query;
        String idPupuk = null;
        String SUrl, SUser, SPass;
        SUrl = "jdbc:MySQL://localhost:3306/studicase_pupuk";
        SUser = "root";
        SPass = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
            Statement st = con.createStatement();

            // Membuat panel untuk input data
            JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
            JTextField tfNamaPupuk = new JTextField(15);
            JTextField tfHargaPupuk = new JTextField(15);

            // ComboBox untuk memilih kode_pupuk
            String[] kodePupuks = {"P001", "P002", "P003", "P004"};
            JComboBox<String> cbKodePupuk = new JComboBox<>(kodePupuks);

            // Menambahkan komponen ke panel
            panel.add(new JLabel("Nama Pupuk:"));
            panel.add(tfNamaPupuk);
            panel.add(new JLabel("Harga Pupuk:"));
            panel.add(tfHargaPupuk);
            panel.add(new JLabel("Kode Pupuk:"));
            panel.add(cbKodePupuk);

            int result = JOptionPane.showConfirmDialog(null, panel,
                    "Enter New Pupuk Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                namaPupuk = tfNamaPupuk.getText().trim();
                hargaPupuk = tfHargaPupuk.getText().trim();
                kodePupuk = cbKodePupuk.getSelectedItem().toString();

                // Validasi Nama Pupuk
                if (namaPupuk.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nama Pupuk is required", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (!namaPupuk.matches("[a-zA-Z ]+")) {
                    JOptionPane.showMessageDialog(null, "Nama Pupuk must only contain letters and spaces", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validasi Harga Pupuk
                if (hargaPupuk.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Harga Pupuk is required", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (!hargaPupuk.matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(null, "Harga Pupuk must only contain numbers", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Cari id terakhir di tabel data_pupuk
                String getLastIdQuery = "SELECT id_pupuk FROM data_pupuk ORDER BY id_pupuk DESC LIMIT 1";
                ResultSet rs = st.executeQuery(getLastIdQuery);

                if (rs.next()) {
                    String lastId = rs.getString("id_pupuk");
                    if (lastId != null && lastId.length() >= 4) {
                        int num = Integer.parseInt(lastId.substring(5));
                        idPupuk = String.format("pupuk%03d", num + 1);
                    } else {
                        idPupuk = "pupuk001";
                    }
                } else {
                    idPupuk = "pupuk001";
                }

                // Query insert data ke tabel data_pupuk
                query = "INSERT INTO data_pupuk (id_pupuk, nama_pupuk, harga_pupuk, kode_pupuk) "
                        + "VALUES ('" + idPupuk + "', '" + namaPupuk + "', '" + hargaPupuk + "', '" + kodePupuk + "')";

                st.execute(query);

                JOptionPane.showMessageDialog(null, "New data_pupuk has been added successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Data entry canceled.");
            }
        } catch (Exception e) {
            System.out.println("Error! " + e.getMessage());
        }

    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        String SUrl = "jdbc:mysql://localhost:3306/studicase_pupuk";
        String SUser = "root";
        String SPass = "";

        // Cek apakah sudah memilih data
        if (selectedIdPupuk == null || selectedIdPupuk.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a row from the table.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);

            // Membuat panel input
            JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
            JTextField tfNamaPupuk = new JTextField(selectedNamaPupuk, 15);
            JTextField tfHargaPupuk = new JTextField(selectedHargaPupuk, 15);

            // ComboBox untuk memilih kode pupuk
            String[] kodePupuks = {"P001", "P002", "P003", "P004"};
            JComboBox<String> cbKodePupuk = new JComboBox<>(kodePupuks);
            cbKodePupuk.setSelectedItem(selectedKodePupuk);

            panel.add(new JLabel("Nama Pupuk:"));
            panel.add(tfNamaPupuk);
            panel.add(new JLabel("Harga Pupuk:"));
            panel.add(tfHargaPupuk);
            panel.add(new JLabel("Kode Pupuk:"));
            panel.add(cbKodePupuk);

            int result = JOptionPane.showConfirmDialog(null, panel, "Edit Data Pupuk", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String namaPupuk = tfNamaPupuk.getText().trim();
                String hargaPupuk = tfHargaPupuk.getText().trim();
                String kodePupuk = cbKodePupuk.getSelectedItem().toString();

                // Validasi Nama Pupuk
                if (namaPupuk.isEmpty() || !namaPupuk.matches("[a-zA-Z ]+")) {
                    JOptionPane.showMessageDialog(null, "Nama Pupuk harus berisi huruf dan spasi saja.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validasi Harga Pupuk
                double harga;
                try {
                    harga = Double.parseDouble(hargaPupuk);
                    if (harga < 0) {
                        JOptionPane.showMessageDialog(null, "Harga Pupuk tidak boleh negatif.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Harga Pupuk harus berupa angka valid.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Query Update menggunakan PreparedStatement untuk keamanan
                String query = "UPDATE data_pupuk SET nama_pupuk = ?, harga_pupuk = ?, kode_pupuk = ? WHERE id_pupuk = ?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, namaPupuk);
                pst.setDouble(2, harga);
                pst.setString(3, kodePupuk);
                pst.setString(4, selectedIdPupuk);

                int updated = pst.executeUpdate();

                if (updated > 0) {
                    JOptionPane.showMessageDialog(null, "Data Pupuk berhasil diperbarui!");
                    loadData(); // Panggil fungsi refresh tabel
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal memperbarui data.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                pst.close();
                con.close();
            } else {
                JOptionPane.showMessageDialog(null, "Edit data dibatalkan.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void mouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseClicked
        int row = table.getSelectedRow();

        // Cek apakah ada baris yang dipilih
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row from the table.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Ambil data dari tabel berdasarkan indeks kolom
        selectedIdPupuk = table.getValueAt(row, 0).toString();
        selectedNamaPupuk = table.getValueAt(row, 1).toString();
        selectedHargaPupuk = table.getValueAt(row, 2).toString();
        selectedKodePupuk = table.getValueAt(row, 3).toString();
    }//GEN-LAST:event_mouseClicked

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        String SUrl = "jdbc:mysql://localhost:3306/studicase_pupuk";
        String SUser = "root";
        String SPass = "";

        // Cek apakah ada data yang dipilih
        if (selectedIdPupuk == null || selectedIdPupuk.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Konfirmasi sebelum menghapus
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this data?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);

            // Query DELETE menggunakan PreparedStatement
            String query = "DELETE FROM data_pupuk WHERE id_pupuk = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, selectedIdPupuk);

            int deleted = pst.executeUpdate();

            if (deleted > 0) {
                JOptionPane.showMessageDialog(null, "Data Pupuk successfully deleted!");
                loadData(); // Refresh tabel setelah penghapusan
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete data.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            pst.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnHapusActionPerformed


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
