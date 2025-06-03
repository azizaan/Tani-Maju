package javaswingdev.form.admin;

import com.raven.chart.ModelChart;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import javaswingdev.form.*;
import javaswingdev.main.Main;
import java.time.LocalDateTime;
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
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javaswingdev.login.SessionManager;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import raven.alerts.MessageAlerts;
import raven.popup.GlassPanePopup;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;
import raven.tabbed.TabbedForm;
import raven.tabbed.WindowsTabbed;
import raven.toast.Notifications;

public class Form_Dashboard_Admin extends javax.swing.JPanel {

    public Form_Dashboard_Admin() {
        initComponents();
        init();
//        GlassPanePopup.install(this);
        this.setVisible(true); // Pastikan form terlihat
        loadDataTransaksi();
        loadDataKeuntungan();
        loadDataPengeluaran();
        updateWaktu(); // Set waktu pertama kali
        startTimer();
        SimpleDateFormat sdfKosong = new SimpleDateFormat("--_--_---- --:--:--");
//        table.addColumn("id absen");
        List<Map<String, String>> notifikasiList = ambilNotifikasiDariDatabase();
        int jumlahNotifikasi = notifikasiList.size();
        badgeButton1.setText(String.valueOf(jumlahNotifikasi));
    }

    private void updateWaktu() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String waktuSekarang = sdf.format(new Date()); // Ambil waktu saat ini
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
            CallableStatement cs = con.prepareCall("{CALL getTotalTransaksi(?, ?)}");

            // Ambil bulan dan tahun saat ini
            LocalDate now = LocalDate.now();
            cs.setInt(1, now.getMonthValue());
            cs.setInt(2, now.getYear());

            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                String jumlahTransaksi = rs.getString("total_transaksi");
                card1.setData(new ModelCard(null, null, null, jumlahTransaksi, "Jumlah Transaksi Bulan Ini"));
            }

            rs.close();
            cs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDataKeuntungan() {
        try {
            Connection con = Koneksi.getKoneksi();
            CallableStatement cs = con.prepareCall("{CALL getTotalKeuntungan(?, ?)}");

            // Ambil bulan dan tahun saat ini
            LocalDate now = LocalDate.now();
            int bulan = now.getMonthValue();
            int tahun = now.getYear();

            cs.setInt(1, bulan);
            cs.setInt(2, tahun);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                double totalKeuntungan = rs.getDouble("total_keuntungan");

                // Format ke Rupiah
                String keuntunganFormatted = formatRupiah(totalKeuntungan);

                // Tampilkan ke card2
                card2.setData(new ModelCard(null, null, null, keuntunganFormatted, "Pemasukan Bulan Ini"));
            }

            rs.close();
            cs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDataPengeluaran() {
        try {
            Connection con = Koneksi.getKoneksi();
            CallableStatement cs = con.prepareCall("{CALL getTotalPengeluaran(?, ?)}");

            // Ambil bulan dan tahun saat ini
            LocalDate now = LocalDate.now();
            int bulan = now.getMonthValue();
            int tahun = now.getYear();

            cs.setInt(1, bulan);
            cs.setInt(2, tahun);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                double pengeluaran = rs.getDouble("total_pengeluaran");

                // Format ke Rupiah
                String formattedPengeluaran = formatRupiah(pengeluaran);

                // Tampilkan ke card3
                card3.setData(new ModelCard(null, null, null, formattedPengeluaran, "Pengeluaran Bulan Ini"));
            }

            rs.close();
            cs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

// Fungsi untuk format angka ke format Rupiah
    private String formatRupiah(double angka) {
        java.text.NumberFormat format = java.text.NumberFormat.getCurrencyInstance(new java.util.Locale("id", "ID"));
        return format.format(angka).replace(",00", ""); // Menghapus ,00 di belakang
    }

    private void loadChartData() {
        try {
            Connection con = Koneksi.getKoneksi();
            Statement stmt = con.createStatement();

            // Query gabungan untuk pemasukan dan pengeluaran per bulan di tahun ini
            String sql = "CALL get_chart_tahunan()";
            ResultSet rs = stmt.executeQuery(sql);

            // Reset legend & data chart
            chart.clear();  // Tambahkan fungsi ini jika ada, atau buat manual
            chart.addLegend("Pemasukan", new Color(12, 84, 175), new Color(0, 108, 247));
            chart.addLegend("Pengeluaran", new Color(54, 4, 143), new Color(104, 49, 200));

            // Inisialisasi array data kosong untuk bulan 1 sampai 12
            double[] pemasukanPerBulan = new double[12];
            double[] pengeluaranPerBulan = new double[12];

            while (rs.next()) {
                int bulan = rs.getInt("bulan") - 1; // index mulai dari 0
                pemasukanPerBulan[bulan] = rs.getDouble("total_pemasukan");
                pengeluaranPerBulan[bulan] = rs.getDouble("total_pengeluaran");
            }

            // Tambahkan data ke chart per bulan
            String[] namaBulan = {
                "Januari", "Februari", "Maret", "April", "Mei", "Juni",
                "Juli", "Agustus", "September", "Oktober", "November", "Desember"
            };

            for (int i = 0; i < 12; i++) {
                // Hanya tampilkan bulan yang memiliki data (bisa diubah sesuai kebutuhan)
                if (pemasukanPerBulan[i] > 0 || pengeluaranPerBulan[i] > 0) {
                    chart.addData(new ModelChart(namaBulan[i],
                            new double[]{pemasukanPerBulan[i], pengeluaranPerBulan[i]}));
                }
            }

            chart.start();
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        loadChartData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        card1 = new javaswingdev.card.Card();
        card2 = new javaswingdev.card.Card();
        card3 = new javaswingdev.card.Card();
        roundPanel1 = new javaswingdev.swing.RoundPanel();
        chart = new com.raven.chart.Chart();
        badgeButton1 = new notibutton.BadgeButton();

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

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addContainerGap())
        );

        badgeButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/notibutton/icon.png"))); // NOI18N
        badgeButton1.setText("0");
        badgeButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                badgeButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(card1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(30, 30, 30)
                                .addComponent(card2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(30, 30, 30)
                                .addComponent(card3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(badgeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(badgeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private JPopupMenu notificationPopup;
    private boolean isPopupVisible = false;

    private List<Map<String, String>> ambilNotifikasiDariDatabase() {
        List<Map<String, String>> daftarNotifikasi = new ArrayList<>();
        try {
            Connection con = Koneksi.getKoneksi();
            String sql = "SELECT n.id_notifikasi, p.nama_pupuk, n.tanggal_notifikasi, n.jumlah_stock "
                    + "FROM notifikasi n "
                    + "JOIN data_pupuk p ON n.id_pupuk = p.id_pupuk "
                    + "WHERE n.status_notifikasi = '1' "
                    + "ORDER BY n.tanggal_notifikasi DESC";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, String> map = new HashMap<>();
                map.put("id", rs.getString("id_notifikasi"));
                map.put("nama", rs.getString("nama_pupuk"));
                map.put("tanggal", rs.getString("tanggal_notifikasi"));
                map.put("stok", rs.getString("jumlah_stock"));  // gunakan nilai dari notifikasi

                daftarNotifikasi.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return daftarNotifikasi;
    }

    private void ubahStatusNotifikasi(String idNotifikasi) {
        try {
            Connection con = Koneksi.getKoneksi();
            String sql = "UPDATE notifikasi SET status_notifikasi = 0 WHERE id_notifikasi = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idNotifikasi);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//     private static Main main;
    private void badgeButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_badgeButton1ActionPerformed
        List<Map<String, String>> notifikasiList = ambilNotifikasiDariDatabase();
        int jumlahNotifikasi = notifikasiList.size();

        if (notificationPopup != null) {
            notificationPopup.setVisible(false);
            isPopupVisible = false;
        }

        notificationPopup = new JPopupMenu();
        notificationPopup.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(new Color(240, 240, 240));
        JLabel title = new JLabel("🔔 Anda memiliki " + jumlahNotifikasi + " notifikasi");
        title.setFont(new Font("SansSerif", Font.BOLD, 13));
        headerPanel.add(title);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);

        for (Map<String, String> notif : notifikasiList) {
            String idNotifikasi = notif.get("id");
            String namaPupuk = notif.get("nama");
            String tanggal = notif.get("tanggal"); // format: "2025-05-09 14:23:45"
            String stock = notif.get("stok");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime waktuNotifikasi = LocalDateTime.parse(tanggal, formatter);
            LocalDateTime sekarang = LocalDateTime.now();

            long selisihHari = ChronoUnit.DAYS.between(waktuNotifikasi.toLocalDate(), sekarang.toLocalDate());
            String jamMenit = waktuNotifikasi.format(DateTimeFormatter.ofPattern("HH:mm"));

            String waktuRelatif = (selisihHari == 0 ? "hari ini" : selisihHari + " hari lalu") + ", " + jamMenit;

            JPanel notifPanel = new JPanel();
            notifPanel.setLayout(new BoxLayout(notifPanel, BoxLayout.Y_AXIS));
            notifPanel.setBackground(Color.WHITE);
            notifPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
            notifPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            notifPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            notifPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            JLabel labelUtama = new JLabel("Stok " + namaPupuk + " tersisa " + stock + " unit");
            labelUtama.setFont(new Font("SansSerif", Font.PLAIN, 13));

            JLabel labelWaktu = new JLabel(waktuRelatif);
            labelWaktu.setFont(new Font("SansSerif", Font.PLAIN, 10));
            labelWaktu.setForeground(Color.GRAY);

            notifPanel.add(labelUtama);
            notifPanel.add(labelWaktu);

            notifPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ubahStatusNotifikasi(idNotifikasi);
                    notificationPopup.setVisible(false);
                    isPopupVisible = false;
                    Main.getMain().showForm(new Form_Stock_Pupuk("Stock"));
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    notifPanel.setBackground(new Color(230, 230, 250));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    notifPanel.setBackground(Color.WHITE);
                }
            });

            listPanel.add(notifPanel);
        }

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(listPanel, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setPreferredSize(new Dimension(270, 200));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

        notificationPopup.add(scrollPane, BorderLayout.CENTER);

        if (!isPopupVisible) {
            int x = badgeButton1.getWidth() - 270;
            int y = badgeButton1.getHeight();
            notificationPopup.show(badgeButton1, x, y);
            isPopupVisible = true;
        } else {
            notificationPopup.setVisible(false);
            isPopupVisible = false;
        }

    }//GEN-LAST:event_badgeButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private notibutton.BadgeButton badgeButton1;
    private javaswingdev.card.Card card1;
    private javaswingdev.card.Card card2;
    private javaswingdev.card.Card card3;
    private com.raven.chart.Chart chart;
    private javaswingdev.swing.RoundPanel roundPanel1;
    // End of variables declaration//GEN-END:variables
}
