package javaswingdev.form.user;

import javaswingdev.form.admin.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Timestamp;
import javaswingdev.form.*;
import javaswingdev.card.ModelCard;
import javaswingdev.form.Koneksi;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.sql.PreparedStatement; // Import PreparedStatement
import javaswingdev.login.SessionManager;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Form_Dashboard_User extends javax.swing.JPanel {

    public Form_Dashboard_User() {
        initComponents();
        init();
        this.setVisible(true); // Pastikan form terlihat
        loadDataTransaksi();
        loadDataKeuntungan();
        loadDataPengeluaran();
        updateWaktu(); // Set waktu pertama kali
        startTimer();
        SimpleDateFormat sdfKosong = new SimpleDateFormat("--_--_---- --:--:--");
        txt_check_in.setText(sdfKosong.format(new Date(0)));
        txt_check_out.setText(sdfKosong.format(new Date(0)));
        loadAbsensi();
        loadAbsensiTable();
//        table.addColumn("id absen");
    }

    private void updateWaktu() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String waktuSekarang = sdf.format(new Date()); // Ambil waktu saat ini
        txt_hari_ini.setText("Waktu Sekarang : " + waktuSekarang); // Set ke JLabel
    }

    private void startTimer() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateWaktu(); // Update waktu setiap detik
            }
        });
        timer.start();
    }

    private void loadDataTransaksi() {
        try {
            Connection con = Koneksi.getKoneksi();
            Statement stmt = con.createStatement();

            // Mendapatkan bulan dan tahun saat ini
            LocalDate now = LocalDate.now();
            int bulan = now.getMonthValue();
            int tahun = now.getYear();

            // Query untuk menghitung jumlah transaksi dalam bulan ini
            String sql = "SELECT COUNT(transaksi_id) AS total_transaksi "
                    + "FROM transaksi "
                    + "WHERE MONTH(waktu_transaksi) = " + bulan + " AND YEAR(waktu_transaksi) = " + tahun;

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String jumlahTransaksi = rs.getString("total_transaksi");

                // Mengatur data card1 dengan jumlah transaksi
                card1.setData(new ModelCard(null, null, null, jumlahTransaksi, "Jumlah Transaksi Bulan Ini"));
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDataKeuntungan() {
        try {
            Connection con = Koneksi.getKoneksi();
            Statement stmt = con.createStatement();

            // Mendapatkan bulan dan tahun saat ini
            LocalDate now = LocalDate.now();
            int bulan = now.getMonthValue();
            int tahun = now.getYear();

            // Query untuk menghitung total keuntungan per bulan
            String sql = "SELECT SUM(t.total_harga) AS total_keuntungan "
                    + "FROM transaksi t "
                    + "WHERE MONTH(t.waktu_transaksi) = " + bulan + " AND YEAR(t.waktu_transaksi) = " + tahun;

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                double totalKeuntungan = rs.getDouble("total_keuntungan");

                // Format ke Rupiah tanpa desimal
                DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

                formatRp.setCurrencySymbol("Rp ");
                formatRp.setMonetaryDecimalSeparator(',');
                formatRp.setGroupingSeparator('.');

                kursIndonesia.setDecimalFormatSymbols(formatRp);
                kursIndonesia.setMaximumFractionDigits(0); // Menghilangkan angka desimal

                String keuntunganFormatted = kursIndonesia.format(totalKeuntungan);

                // Set data ke card2 dengan format Rupiah
                card2.setData(new ModelCard(null, null, null, keuntunganFormatted, "Keuntungan Bulan Ini"));
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDataPengeluaran() {
        try {
            Connection con = Koneksi.getKoneksi();
            Statement stmt = con.createStatement();

            // Mendapatkan bulan dan tahun saat ini
            LocalDate now = LocalDate.now();
            int bulan = now.getMonthValue();
            int tahun = now.getYear();

            // Query untuk menghitung total pengeluaran per bulan berdasarkan restock
            String sql = "SELECT SUM(CAST(d.jumlah_beli AS UNSIGNED) * CAST(p.harga_pupuk AS UNSIGNED)) AS total_pengeluaran "
                    + "FROM transaksi_detail d "
                    + "JOIN data_pupuk p ON d.id_pupuk = p.id_pupuk "
                    + "JOIN transaksi t ON d.transaksi_id = t.transaksi_id "
                    + "WHERE MONTH(t.waktu_transaksi) = " + bulan + " AND YEAR(t.waktu_transaksi) = " + tahun;

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                double pengeluaran = rs.getDouble("total_pengeluaran");

                // Format ke Rupiah tanpa ",0" di belakang
                String formattedPengeluaran = formatRupiah(pengeluaran);

                // Mengatur data untuk card2 dengan pengeluaran bulanan
                card3.setData(new ModelCard(null, null, null, formattedPengeluaran, "Pengeluaran Bulan Ini"));
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

// Fungsi untuk format angka ke format Rupiah
    private String formatRupiah(double angka) {
        java.text.NumberFormat format = java.text.NumberFormat.getCurrencyInstance(new java.util.Locale("id", "ID"));
        return format.format(angka).replace(",00", ""); // Menghapus ,00 di belakang
    }

    private void init() {
        txt_check_in.setHorizontalAlignment(SwingConstants.CENTER);
        txt_check_out.setHorizontalAlignment(SwingConstants.CENTER);
        txt_hari_ini.setHorizontalAlignment(SwingConstants.CENTER);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        card1 = new javaswingdev.card.Card();
        card2 = new javaswingdev.card.Card();
        card3 = new javaswingdev.card.Card();
        roundPanel1 = new javaswingdev.swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javaswingdev.swing.table.Table();
        txt_hari_ini = new javax.swing.JLabel();
        txt_check_in = new javax.swing.JLabel();
        txt_check_out = new javax.swing.JLabel();
        btn_in = new javax.swing.JButton();
        btn_out = new javax.swing.JButton();
        TXT1 = new javax.swing.JLabel();
        TXT2 = new javax.swing.JLabel();
        btn_izin = new javax.swing.JButton();

        setOpaque(false);

        card2.setColor1(new java.awt.Color(95, 211, 226));
        card2.setColor2(new java.awt.Color(26, 166, 170));
        card2.setIcon(javaswingdev.GoogleMaterialDesignIcon.PIE_CHART);

        card3.setColor1(new java.awt.Color(95, 243, 140));
        card3.setColor2(new java.awt.Color(3, 157, 27));
        card3.setIcon(javaswingdev.GoogleMaterialDesignIcon.RING_VOLUME);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        roundPanel1.setRound(10);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "Check in", "Check out"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        txt_hari_ini.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_hari_ini.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_hari_ini.setText("TXTHARIINI");
        txt_hari_ini.setToolTipText("");
        txt_hari_ini.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txt_check_in.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_check_in.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_check_in.setText("TXTCHECKIN");
        txt_check_in.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        txt_check_out.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_check_out.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_check_out.setText("TXTCHECKOUT");
        txt_check_out.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        btn_in.setText("Check In");
        btn_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inActionPerformed(evt);
            }
        });

        btn_out.setText("Check Out");
        btn_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_outActionPerformed(evt);
            }
        });

        TXT1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TXT1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TXT1.setText("WAKTU CHECK IN :");
        TXT1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        TXT2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TXT2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TXT2.setText("WAKTU CHECK OUT :");
        TXT2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btn_izin.setText("Izin");
        btn_izin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_izinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_hari_ini)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TXT2)
                                    .addComponent(TXT1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_check_in)
                                    .addComponent(txt_check_out))
                                .addGap(76, 76, 76)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_in, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_out, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_izin, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addComponent(txt_hari_ini)
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_check_in)
                    .addComponent(btn_in)
                    .addComponent(TXT1)
                    .addComponent(btn_izin))
                .addGap(18, 18, 18)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXT2)
                    .addComponent(txt_check_out)
                    .addComponent(btn_out))
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(card1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addComponent(card2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30)
                        .addComponent(card3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loadAbsensi() {
        try {
            Connection con = Koneksi.getKoneksi();
            String UserId = SessionManager.currentUserIDKasir;

            // Format tanggal hari ini
            SimpleDateFormat dateOnlyFormat = new SimpleDateFormat("yyyy-MM-dd");
            String todayDate = dateOnlyFormat.format(new Date());

            // Ambil data check-in dan check-out user untuk hari ini
            String sql = "SELECT check_in, check_out FROM absensi_pegawai WHERE user_id = ? AND DATE(check_in) = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, UserId);
            ps.setString(2, todayDate);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) { // Jika ada data check-in hari ini
                String waktuCheckIn = rs.getString("check_in");
                txt_check_in.setText(waktuCheckIn);

                String waktuCheckOut = rs.getString("check_out");
                if (waktuCheckOut != null) {
                    txt_check_out.setText(waktuCheckOut);
                } else {
                    txt_check_out.setText(""); // Jika belum check-out, kosongkan field
                }
            } else {
                // Jika belum check-in hari ini, kosongkan semua field
                txt_check_in.setText("");
                txt_check_out.setText("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error saat memuat data absensi: " + e.getMessage());
        }
    }

    private void loadAbsensiTable() {
        try {
            Connection con = Koneksi.getKoneksi();
            String UserId = SessionManager.currentUserIDKasir;

            // Query dengan ORDER BY ASC agar hari sebelum hari ini berada di atas
            String sql = "SELECT u.full_name, a.check_in, a.check_out "
                    + "FROM absensi_pegawai a "
                    + "JOIN user u ON a.user_id = u.user_id "
                    + "WHERE u.user_id = ? "
                    + "ORDER BY a.check_in DESC";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, UserId);
            ResultSet rs = ps.executeQuery();

            // Model untuk tabel
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Kosongkan tabel sebelum diisi ulang

            while (rs.next()) {
                String namaPegawai = rs.getString("full_name");
                String waktuCheckIn = rs.getString("check_in");
                String waktuCheckOut = rs.getString("check_out");

                // Tambahkan data ke tabel
                model.addRow(new Object[]{namaPegawai, waktuCheckIn, waktuCheckOut});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error saat memuat data ke tabel: " + e.getMessage());
        }
    }


    private void btn_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inActionPerformed
        try {
            Connection con = Koneksi.getKoneksi();
            String UserId = SessionManager.currentUserIDKasir;
            System.out.println(UserId);

            // Format tanggal hari ini
            SimpleDateFormat dateOnlyFormat = new SimpleDateFormat("yyyy-MM-dd");
            String todayDate = dateOnlyFormat.format(new Date());

            // Cek apakah user sudah izin hari ini
            String sqlCekIzin = "SELECT * FROM izin_pegawai WHERE id_user = ? AND DATE(tanggal_izin) = ?";
            PreparedStatement psIzin = con.prepareStatement(sqlCekIzin);
            psIzin.setString(1, UserId);
            psIzin.setString(2, todayDate);
            ResultSet rsIzin = psIzin.executeQuery();

            if (rsIzin.next()) {
                JOptionPane.showMessageDialog(null, "Anda sudah mengajukan izin hari ini, tidak bisa check-in.");
                return;
            }

            // Cek apakah user sudah check-in hari ini
            String sqlCheck = "SELECT check_in FROM absensi_pegawai WHERE user_id = ? AND DATE(check_in) = ?";
            PreparedStatement psCheck = con.prepareStatement(sqlCheck);
            psCheck.setString(1, UserId);
            psCheck.setString(2, todayDate);
            ResultSet rs = psCheck.executeQuery();

            if (rs.next()) { // Jika sudah check-in hari ini
                String waktuCheckIn = rs.getString("check_in");
                txt_check_in.setText(waktuCheckIn);
                JOptionPane.showMessageDialog(null, "Anda sudah check-in hari ini!\nWaktu Check-in: " + waktuCheckIn);
                return;
            }

            // Jika belum check-in dan belum izin, lakukan check-in
            String idAbsen = UUID.randomUUID().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String waktuCheckIn = sdf.format(new Date());

            String sqlInsert = "INSERT INTO absensi_pegawai (id_absen, user_id, check_in) VALUES (?, ?, ?)";
            PreparedStatement psInsert = con.prepareStatement(sqlInsert);
            psInsert.setString(1, idAbsen);
            psInsert.setString(2, UserId);
            psInsert.setString(3, waktuCheckIn);
            psInsert.executeUpdate();

            txt_check_in.setText(waktuCheckIn);
            loadAbsensiTable();
            JOptionPane.showMessageDialog(null, "Check-in berhasil!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error saat check-in: " + e.getMessage());
        }

    }//GEN-LAST:event_btn_inActionPerformed

    private void btn_outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_outActionPerformed
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String waktuCheckOut = sdf.format(new Date());
            String UserId = SessionManager.currentUserIDKasir;
            Connection con = Koneksi.getKoneksi();

            // Update check-out berdasarkan user_id untuk data yang belum check-out
            String sql = "UPDATE absensi_pegawai SET check_out = ? WHERE user_id = ? AND check_out IS NULL";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, waktuCheckOut);
            ps.setString(2, UserId);
            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                txt_check_out.setText(waktuCheckOut);
                JOptionPane.showMessageDialog(null, "Check-out berhasil!");
            } else {
                JOptionPane.showMessageDialog(null, "Error: Anda belum check-in!");
            }
            loadAbsensiTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error saat check-out: " + e.getMessage());
        }

    }//GEN-LAST:event_btn_outActionPerformed

    private void btn_izinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_izinActionPerformed
        try {
            Connection con = Koneksi.getKoneksi();
            String UserId = SessionManager.currentUserIDKasir;
            System.out.println(UserId);

            // Format tanggal hari ini
            SimpleDateFormat dateOnlyFormat = new SimpleDateFormat("yyyy-MM-dd");
            String todayDate = dateOnlyFormat.format(new Date());

            // Cek apakah sudah izin hari ini
            String sqlCek = "SELECT * FROM izin_pegawai WHERE id_user = ? AND DATE(tanggal_izin) = ?";
            PreparedStatement psCek = con.prepareStatement(sqlCek);
            psCek.setString(1, UserId);
            psCek.setString(2, todayDate);
            ResultSet rs = psCek.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Anda sudah mengajukan izin hari ini!\nAlasan: " + rs.getString("alasan_izin"));
                return;
            }

            // Input alasan izin (bisa kamu ganti dengan TextField jika pakai form input)
            String alasan = JOptionPane.showInputDialog("Masukkan alasan izin:");
            if (alasan == null || alasan.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Alasan tidak boleh kosong.");
                return;
            }

            String idIzin = UUID.randomUUID().toString();
            String tanggalIzin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            // id_absen bisa null atau kosong karena belum absen
            String sqlInsert = "INSERT INTO izin_pegawai (id_izin, tanggal_izin, alasan_izin, id_absen, user_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement psInsert = con.prepareStatement(sqlInsert);
            psInsert.setString(1, idIzin);
            psInsert.setString(2, tanggalIzin);
            psInsert.setString(3, alasan);
            psInsert.setString(4, null); // karena belum check in
            psInsert.setString(5, UserId);
            psInsert.executeUpdate();

            JOptionPane.showMessageDialog(null, "Izin berhasil diajukan!");
            loadAbsensiTable(); // kalau kamu ingin tampilkan di tabel
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error saat mengajukan izin: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_izinActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TXT1;
    private javax.swing.JLabel TXT2;
    private javax.swing.JButton btn_in;
    private javax.swing.JButton btn_izin;
    private javax.swing.JButton btn_out;
    private javaswingdev.card.Card card1;
    private javaswingdev.card.Card card2;
    private javaswingdev.card.Card card3;
    private javax.swing.JScrollPane jScrollPane1;
    private javaswingdev.swing.RoundPanel roundPanel1;
    private javaswingdev.swing.table.Table table;
    private javax.swing.JLabel txt_check_in;
    private javax.swing.JLabel txt_check_out;
    private javax.swing.JLabel txt_hari_ini;
    // End of variables declaration//GEN-END:variables
}
