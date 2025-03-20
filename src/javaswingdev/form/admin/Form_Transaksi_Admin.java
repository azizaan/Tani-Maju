package javaswingdev.form.admin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaswingdev.form.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javaswingdev.form.admin.FormBayar;
import java.sql.SQLException; // Import PreparedStatement
import java.sql.PreparedStatement; // Import PreparedStatement
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

public class Form_Transaksi_Admin extends javax.swing.JPanel {

    public Form_Transaksi_Admin(String name) {
        initComponents();
//        lb.setText("Form " + name);
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Mencegah edit langsung di tabel
            }
        };

        table.setModel(model);

        // Menghapus kolom password dari tabel
        model.addColumn("ID Pupuk");
        model.addColumn("Nama Pupuk");
        model.addColumn("Harga Pupuk (per pcs)");
        model.addColumn("Jumlah Stock");

        loadData();
        TotalHarga.setText("Rp 0,00");

        txtcari.requestFocusInWindow();
    }

    private DefaultTableModel model;

    public void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection c = Koneksi.getKoneksi();
            Statement s = c.createStatement();

            // JOIN tabel data_pupuk dengan stock_pupuk berdasarkan id_pupuk
            String sql = "SELECT dp.id_pupuk, dp.nama_pupuk, dp.harga_pupuk, sp.jumlah_stock "
                    + "FROM data_pupuk dp "
                    + "LEFT JOIN stock_pupuk sp ON dp.id_pupuk = sp.id_pupuk";

            ResultSet r = s.executeQuery(sql);

            // Gunakan DecimalFormat untuk menambahkan titik pemisah ribuan
            DecimalFormat formatRupiah = new DecimalFormat("#,###");

            while (r.next()) {
                Object[] o = new Object[4]; // Tambah kolom untuk stok
                o[0] = r.getString("id_pupuk");
                o[1] = r.getString("nama_pupuk");

                // Ambil harga dari database dan format ke Rupiah dengan titik pemisah ribuan
                double harga = r.getDouble("harga_pupuk");
                String hargaFormatted = "Rp" + formatRupiah.format(harga);
                o[2] = hargaFormatted;

                // Ambil stok dari tabel stock_pupuk
                o[3] = r.getInt("jumlah_stock"); // Pastikan kolom stok tampil

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
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTransaksi = new javaswingdev.swing.table.Table();
        txtcari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        TotalHarga = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Bayar = new javax.swing.JButton();

        setOpaque(false);

        lb.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        lb.setForeground(new java.awt.Color(125, 125, 125));
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Form Transaksi Admin");

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
                tablemouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        tableTransaksi.setForeground(new java.awt.Color(0, 0, 255));
        tableTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product", "Harga Per pcs", "Jumlah", "Total Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableTransaksimouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableTransaksi);

        txtcari.setBackground(new java.awt.Color(255, 255, 255));
        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Cari(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Cari Data");

        TotalHarga.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        TotalHarga.setText("00");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("TOTAL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(544, Short.MAX_VALUE)
                .addComponent(TotalHarga)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(476, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TotalHarga, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        Bayar.setText("Bayar");
        Bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BayarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(Bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcari, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(19, 19, 19))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(286, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lb)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(98, 98, 98)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(196, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private String selectedIdPupuk;
    private String selectedNamaPupuk;
    private String selectedHargaPupuk;
//    private String selectedKodePupuk;


    private void tablemouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablemouseClicked
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row from the table.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String idPupuk = table.getValueAt(row, 0).toString();
        String namaPupuk = table.getValueAt(row, 1).toString();
        String hargaPupukStr = table.getValueAt(row, 2).toString();
        int jumlahStock = Integer.parseInt(table.getValueAt(row, 3).toString());

        String jumlahBeliStr = JOptionPane.showInputDialog(null,
                "Masukkan jumlah yang ingin dibeli:",
                "Input Jumlah Beli",
                JOptionPane.QUESTION_MESSAGE);

        if (jumlahBeliStr == null || jumlahBeliStr.trim().isEmpty()) {
            return;
        }

        try {
            int jumlahBeli = Integer.parseInt(jumlahBeliStr);

            if (jumlahBeli <= 0) {
                JOptionPane.showMessageDialog(null, "Jumlah harus lebih dari 0!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (jumlahBeli > jumlahStock) {
                JOptionPane.showMessageDialog(null, "Jumlah melebihi stok yang tersedia!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 🔥 Hapus "Rp" dan titik (.) pada harga sebelum parsing
            String hargaBersih = hargaPupukStr.replace("Rp", "").replace(".", "").trim();
            int hargaPupuk = Integer.parseInt(hargaBersih);
            int totalHarga = hargaPupuk * jumlahBeli;

            DefaultTableModel transaksiModel = (DefaultTableModel) tableTransaksi.getModel();
            boolean sudahAda = false;

            for (int i = 0; i < transaksiModel.getRowCount(); i++) {
                String namaPupukDiTabel = transaksiModel.getValueAt(i, 0).toString();

                if (namaPupuk.equals(namaPupukDiTabel)) {
                    int jumlahLama = Integer.parseInt(transaksiModel.getValueAt(i, 2).toString());
                    int jumlahBaru = jumlahLama + jumlahBeli;

                    if (jumlahBaru > jumlahStock) {
                        JOptionPane.showMessageDialog(null, "Total pembelian melebihi stok yang tersedia!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    transaksiModel.setValueAt(jumlahBaru, i, 2);
                    transaksiModel.setValueAt(formatRupiah(hargaPupuk * jumlahBaru), i, 3);
                    sudahAda = true;
                    break;
                }
            }

            if (!sudahAda) {
                transaksiModel.addRow(new Object[]{
                    namaPupuk, formatRupiah(hargaPupuk), jumlahBeli, formatRupiah(totalHarga)
                });
            }

            hitungTotalHarga(); // 🔥 Hitung ulang total harga setelah update

            JOptionPane.showMessageDialog(null, "Berhasil ditambahkan ke transaksi!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Masukkan angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_tablemouseClicked

    public String formatRupiah(int angka) {
        DecimalFormat formatRupiah = new DecimalFormat("#,###");
        return "Rp" + formatRupiah.format(angka);
    }

    private void tableTransaksimouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTransaksimouseClicked
        int row = tableTransaksi.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Silakan pilih item di tabel transaksi.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String namaPupuk = tableTransaksi.getValueAt(row, 0).toString();
        String hargaPupukStr = tableTransaksi.getValueAt(row, 1).toString();
        int jumlahBeli = Integer.parseInt(tableTransaksi.getValueAt(row, 2).toString());

        String jumlahKurangiStr = JOptionPane.showInputDialog(null,
                "Masukkan jumlah yang ingin dikurangi:",
                "Kurangi Jumlah Beli",
                JOptionPane.QUESTION_MESSAGE);

        if (jumlahKurangiStr == null || jumlahKurangiStr.trim().isEmpty()) {
            return;
        }

        try {
            int jumlahKurangi = Integer.parseInt(jumlahKurangiStr);

            if (jumlahKurangi <= 0) {
                JOptionPane.showMessageDialog(null, "Jumlah pengurangan harus lebih dari 0!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (jumlahKurangi > jumlahBeli) {
                JOptionPane.showMessageDialog(null, "Jumlah pengurangan melebihi jumlah yang dibeli!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int jumlahBaru = jumlahBeli - jumlahKurangi;
            DefaultTableModel model = (DefaultTableModel) tableTransaksi.getModel();

            // 🔥 Hapus "Rp" dan titik sebelum parsing harga
            String hargaBersih = hargaPupukStr.replace("Rp", "").replace(".", "").trim();
            int hargaPupuk = Integer.parseInt(hargaBersih);
            int totalHargaBaru = hargaPupuk * jumlahBaru;

            if (jumlahBaru == 0) {
                model.removeRow(row);
            } else {
                tableTransaksi.setValueAt(jumlahBaru, row, 2);
                tableTransaksi.setValueAt(formatRupiah(totalHargaBaru), row, 3);
            }

            hitungTotalHarga(); // 🔥 Hitung ulang total harga setelah pengurangan

            JOptionPane.showMessageDialog(null, "Jumlah berhasil dikurangi!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Masukkan angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_tableTransaksimouseClicked

    private void hitungTotalHarga() {
        DefaultTableModel model = (DefaultTableModel) tableTransaksi.getModel();
        double totalHarga = 0.0;
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        for (int i = 0; i < model.getRowCount(); i++) {
            try {
                totalHarga += formatRupiah.parse(model.getValueAt(i, 3).toString()).doubleValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        TotalHarga.setText(formatRupiah.format(totalHarga));
    }


    private void Cari(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Cari
        String keyword = txtcari.getText().trim();
        handleSearchOrSelect(keyword);
    }//GEN-LAST:event_Cari

    private void BayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BayarActionPerformed
        DefaultTableModel model = (DefaultTableModel) tableTransaksi.getModel();

        // Cek apakah tabel kosong
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tidak ada transaksi yang diproses!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return; // Hentikan proses jika tabel kosong
        }
        List<String[]> dataTransaksi = new ArrayList<>();
        int totalKeseluruhan = 0;

        // Ambil data dari tabel transaksi
        for (int i = 0; i < model.getRowCount(); i++) {
            String namaPupuk = model.getValueAt(i, 0).toString();
            String hargaPupuk = model.getValueAt(i, 1).toString();
            String jumlahBeli = model.getValueAt(i, 2).toString();
            String totalHarga = model.getValueAt(i, 3).toString();

            // Simpan ke dalam list untuk dikirim ke FormBayar
            dataTransaksi.add(new String[]{namaPupuk, hargaPupuk, jumlahBeli, totalHarga});

            // Hitung total keseluruhan transaksi
            try {
                String nilaiStr = totalHarga.replaceAll("[^0-9,]", "").replace(",", ".");
                if (!nilaiStr.isEmpty()) {
                    totalKeseluruhan += Double.parseDouble(nilaiStr);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error parsing angka: " + totalHarga);
            }
        }

        // Debugging: Cek apakah data transaksi sudah terkumpul sebelum dikirim
        System.out.println("=== Data Transaksi ===");
        for (String[] transaksi : dataTransaksi) {
            System.out.println("Nama Pupuk: " + transaksi[0]
                    + ", Harga: " + transaksi[1]
                    + ", Jumlah: " + transaksi[2]
                    + ", Total: " + transaksi[3]);
        }
        System.out.println("======================");

        // Kirim data transaksi ke FormBayar
        FormBayar bayarFrame = new FormBayar(dataTransaksi, totalKeseluruhan, this);
        bayarFrame.setVisible(true);
    }//GEN-LAST:event_BayarActionPerformed

    public void refreshTable() {
        // Menghapus semua data di tabel sebelum memuat ulang
        DefaultTableModel model = (DefaultTableModel) tableTransaksi.getModel();
        model.setRowCount(0);

        // Panggil metode yang memuat ulang data transaksi
        loadData();

        // Jika ada komponen lain yang perlu diperbarui, tambahkan di sini
        System.out.println("Tabel transaksi diperbarui dan dikosongkan sebelum reload.");
    }

    public void searchData(String keyword) {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection c = Koneksi.getKoneksi();
            Statement s = c.createStatement();

            // Query pencarian dengan JOIN stock_pupuk
            String sql = "SELECT dp.id_pupuk, dp.nama_pupuk, dp.harga_pupuk, sp.jumlah_stock "
                    + "FROM data_pupuk dp "
                    + "LEFT JOIN stock_pupuk sp ON dp.id_pupuk = sp.id_pupuk "
                    + "WHERE dp.id_pupuk LIKE '%" + keyword + "%' OR "
                    + "dp.nama_pupuk LIKE '%" + keyword + "%' OR "
                    + "dp.harga_pupuk LIKE '%" + keyword + "%' OR "
                    + "sp.jumlah_stock LIKE '%" + keyword + "%'";
            ResultSet r = s.executeQuery(sql);

            NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

            while (r.next()) {
                Object[] o = new Object[4];
                o[0] = r.getString("id_pupuk");
                o[1] = r.getString("nama_pupuk");
                double harga = r.getDouble("harga_pupuk");
                o[2] = rupiahFormat.format(harga);
                o[3] = r.getInt("jumlah_stock");
                model.addRow(o);
            }
            r.close();
            s.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Timer searchDelayTimer;
    private long lastKeyPressTime = 0;
    private static final int SCAN_THRESHOLD = 50;

    private void handleSearchOrSelect(String keyword) {
        if (keyword.isEmpty()) {
            return;
        }

        long currentTime = System.currentTimeMillis();

        // Deteksi apakah input adalah scan (masuk dalam waktu cepat)
        if (currentTime - lastKeyPressTime < SCAN_THRESHOLD && isKodePupukValid(keyword)) {
            autoSelectPupuk(keyword);
            return;
        }

        lastKeyPressTime = currentTime;

        // Gunakan Timer untuk delay pencarian manual
        if (searchDelayTimer != null && searchDelayTimer.isRunning()) {
            searchDelayTimer.stop();
        }

        searchDelayTimer = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchData(keyword);
            }
        });
        searchDelayTimer.setRepeats(false);
        searchDelayTimer.start();
    }

    private boolean isKodePupukValid(String kodePupuk) {
        try {
            Connection c = Koneksi.getKoneksi();
            PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM data_pupuk WHERE kode_pupuk = ?");
            ps.setString(1, kodePupuk);
            ResultSet rs = ps.executeQuery();
            rs.next();
            boolean exists = rs.getInt(1) > 0;
            rs.close();
            ps.close();
            return exists;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void autoSelectPupuk(String kodePupuk) {
        try {
            Connection c = Koneksi.getKoneksi();
            String sql = "SELECT dp.kode_pupuk, dp.nama_pupuk, dp.harga_pupuk, sp.jumlah_stock "
                    + "FROM data_pupuk dp "
                    + "LEFT JOIN stock_pupuk sp ON dp.id_pupuk = sp.id_pupuk " // Perbaikan JOIN
                    + "WHERE dp.kode_pupuk = ?"; // Pencarian tetap berdasarkan kode_pupuk
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, kodePupuk);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String namaPupuk = rs.getString("nama_pupuk");
                double hargaPupuk = rs.getDouble("harga_pupuk");
                int jumlahStock = rs.getInt("jumlah_stock");

                if (jumlahStock <= 0) {
                    JOptionPane.showMessageDialog(null, "Stok habis!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int jumlahBeli = Math.min(1, jumlahStock);
                double totalHarga = hargaPupuk * jumlahBeli;

                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
                DefaultTableModel transaksiModel = (DefaultTableModel) tableTransaksi.getModel();

                boolean sudahAda = false;

                for (int i = 0; i < transaksiModel.getRowCount(); i++) {
                    if (namaPupuk.equals(transaksiModel.getValueAt(i, 0).toString())) {
                        int jumlahLama = Integer.parseInt(transaksiModel.getValueAt(i, 2).toString());
                        int jumlahBaru = jumlahLama + jumlahBeli;

                        if (jumlahBaru > jumlahStock) {
                            JOptionPane.showMessageDialog(null, "Total pembelian melebihi stok yang tersedia!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        transaksiModel.setValueAt(jumlahBaru, i, 2);
                        transaksiModel.setValueAt(formatRupiah.format(hargaPupuk * jumlahBaru), i, 3);
                        sudahAda = true;
                        break;
                    }
                }

                if (!sudahAda) {
                    transaksiModel.addRow(new Object[]{
                        namaPupuk, formatRupiah.format(hargaPupuk), jumlahBeli, formatRupiah.format(totalHarga)
                    });
                }

                hitungTotalHarga();
                JOptionPane.showMessageDialog(null, "Berhasil ditambahkan ke transaksi!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengambil data pupuk!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bayar;
    private javax.swing.JLabel TotalHarga;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb;
    private javaswingdev.swing.table.Table table;
    private javaswingdev.swing.table.Table tableTransaksi;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
