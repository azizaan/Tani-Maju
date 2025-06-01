package javaswingdev.form.admin;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import java.sql.SQLException;
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
import java.sql.PreparedStatement; // Import PreparedStatement
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class Form_Restock_Pupuk extends javax.swing.JPanel {

    public Form_Restock_Pupuk(String name) {
        initComponents();
        lb.setText("Form " + name);
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Mencegah edit langsung di tabel
            }
        };

        table.setModel(model);

// Tambahkan kolom dulu
        model.addColumn("id_restock"); // Kolom 0 (tersembunyi)
        model.addColumn("id_pupuk");   // Kolom 1 (tersembunyi)
        model.addColumn("No");         // Kolom 2
        model.addColumn("Nama Pupuk"); // Kolom 3
        model.addColumn("Jumlah Restock"); // Kolom 4
        model.addColumn("Tanggal Restock"); // Kolom 5

// Baru sembunyikan kolom id_restock dan id_pupuk
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setWidth(0);
        table.getColumnModel().getColumn(0).setPreferredWidth(0);

        table.getColumnModel().getColumn(1).setMinWidth(0);
        table.getColumnModel().getColumn(1).setMaxWidth(0);
        table.getColumnModel().getColumn(1).setWidth(0);
        table.getColumnModel().getColumn(1).setPreferredWidth(0);

// Setelah itu panggil loadData
        loadData();

//        table.setEnabled(false);
        btn_tambah.setText(" + Add ");
        btn_edit.setText(" ✎ Edit ");
        btn_hapus.setText(" 🗑 Delete ");
        // Tombol ADD
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
    }

    private DefaultTableModel model;

    public void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection c = Koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT rp.id_restock, dr.id_pupuk, dp.nama_pupuk, dr.jumlah, rp.tgl_restock "
                    + "FROM restock_pupuk rp "
                    + "JOIN detail_restock dr ON rp.id_restock = dr.id_restock "
                    + "JOIN data_pupuk dp ON dr.id_pupuk = dp.id_pupuk "
                    + "ORDER BY rp.tgl_restock ASC";

            ResultSet r = s.executeQuery(sql);

            int no = 1;
            while (r.next()) {
                Object[] o = new Object[6];
                o[0] = r.getString("id_restock");     // Kolom tersembunyi: id_restock
                o[1] = r.getString("id_pupuk");       // Kolom tersembunyi: id_pupuk
                o[2] = no++;                           // No urut
                o[3] = r.getString("nama_pupuk");      // Nama Pupuk
                o[4] = r.getInt("jumlah");             // Jumlah Restock
                o[5] = r.getDate("tgl_restock");       // Tanggal Restock

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
                Form_Restock_Pupuk.this.mouseClicked(evt);
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

        btn_hapus.setText("hapus");
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

        // Ambil data dari tabel berdasarkan indeks kolom
        selectedIdPupuk = table.getValueAt(row, 0).toString();
        selectedNamaPupuk = table.getValueAt(row, 1).toString();
        selectedHargaPupuk = table.getValueAt(row, 2).toString();
        selectedKodePupuk = table.getValueAt(row, 3).toString();
    }//GEN-LAST:event_mouseClicked

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        String SUrl = "jdbc:mysql://localhost:3306/studicase_pupuk";
        String SUser = "root";
        String SPass = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
            Statement st = con.createStatement();

            // Ambil daftar pupuk
            String getPupukQuery = "SELECT id_pupuk, nama_pupuk FROM data_pupuk";
            ResultSet rs = st.executeQuery(getPupukQuery);

            List<String> pupukList = new ArrayList<>();
            Map<String, String> pupukMap = new HashMap<>();

            while (rs.next()) {
                pupukList.add(rs.getString("nama_pupuk"));
                pupukMap.put(rs.getString("nama_pupuk"), rs.getString("id_pupuk"));
            }
            rs.close();

            if (pupukList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Tidak ada data pupuk tersedia!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Panel input
            JPanel panel = new JPanel(new BorderLayout());

            JPanel listPanel = new JPanel();
            listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

            List<JComboBox<String>> comboList = new ArrayList<>();
            List<JTextField> jumlahList = new ArrayList<>();

            JButton btnTambahBaris = new JButton("Tambah Baris");

            Runnable addRow = () -> {
                JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
                JLabel label = new JLabel("Pupuk:");
                JComboBox<String> cb = new JComboBox<>(pupukList.toArray(new String[0]));
                JTextField tf = new JTextField(5);
                JButton btnHapus = new JButton("❌");

                comboList.add(cb);
                jumlahList.add(tf);

                btnHapus.addActionListener(e -> {
                    listPanel.remove(rowPanel);
                    comboList.remove(cb);
                    jumlahList.remove(tf);
                    listPanel.revalidate();
                    listPanel.repaint();
                });

                rowPanel.add(label);
                rowPanel.add(cb);
                rowPanel.add(tf);
                rowPanel.add(btnHapus);

                listPanel.add(rowPanel);
                listPanel.revalidate();
                listPanel.repaint();
            };

            addRow.run();

            JScrollPane scrollPane = new JScrollPane(listPanel);
            scrollPane.setPreferredSize(new Dimension(310, 100));
            panel.add(scrollPane, BorderLayout.CENTER);

            panel.add(btnTambahBaris, BorderLayout.SOUTH);
            btnTambahBaris.addActionListener(e -> addRow.run());

            // Tanggal restock
            JPanel tanggalPanel = new JPanel(new FlowLayout());
            JDateChooser dateChooser = new JDateChooser();
            dateChooser.setDateFormatString("yyyy-MM-dd HH:mm:ss");
            tanggalPanel.add(new JLabel("Tanggal Restock:"));
            tanggalPanel.add(dateChooser);
            panel.add(tanggalPanel, BorderLayout.NORTH);

            int result = JOptionPane.showConfirmDialog(null, panel, "Tambah Multi-Stok Pupuk", JOptionPane.OK_CANCEL_OPTION);
            if (result != JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(null, "Penambahan stok dibatalkan.");
                return;
            }

            Date selectedDate = dateChooser.getDate();
            if (selectedDate == null) {
                JOptionPane.showMessageDialog(null, "Silakan pilih tanggal restock!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String tanggalRestock = sdf.format(selectedDate);

            // Generate id_restock baru
            String getLastRestockIdQuery = "SELECT id_restock FROM restock_pupuk ORDER BY id_restock DESC LIMIT 1";
            ResultSet rsLastRestock = st.executeQuery(getLastRestockIdQuery);
            String newIdRestock = "rst_001";
            if (rsLastRestock.next()) {
                String lastId = rsLastRestock.getString("id_restock");
                int lastNumber = Integer.parseInt(lastId.split("_")[1]);
                newIdRestock = String.format("rst_%03d", lastNumber + 1);
            }
            rsLastRestock.close();

            // Validasi input
            for (int i = 0; i < comboList.size(); i++) {
                String jumlahText = jumlahList.get(i).getText().trim();
                if (jumlahText.isEmpty() || !jumlahText.matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "Jumlah harus diisi dan berupa angka pada baris ke-" + (i + 1));
                    return;
                }
            }

            // Insert ke restock_pupuk
            String insertRestockQuery = "INSERT INTO restock_pupuk (id_restock, tgl_restock) VALUES (?, ?)";
            PreparedStatement psInsertRestock = con.prepareStatement(insertRestockQuery);
            psInsertRestock.setString(1, newIdRestock);
            psInsertRestock.setString(2, tanggalRestock);
            psInsertRestock.executeUpdate();

            // Ambil ID kartu terakhir
            String getLastKartuQuery = "SELECT id_kartu FROM kartu_stock ORDER BY id_kartu DESC LIMIT 1";
            ResultSet rsLastKartu = st.executeQuery(getLastKartuQuery);
            int lastKartuNum = 0;
            if (rsLastKartu.next()) {
                String lastId = rsLastKartu.getString("id_kartu");
                lastKartuNum = Integer.parseInt(lastId.split("_")[1]);
            }
            rsLastKartu.close();

            // Insert detail_restock dan kartu_stock
            String insertDetailRestockQuery = "INSERT INTO detail_restock (id_restock, id_pupuk, jumlah) VALUES (?, ?, ?)";
//            String insertStockQuery = "INSERT INTO kartu_stock (id_kartu, id_pupuk, id_restock, tanggal, jumlah_masuk, sisa, keterangan) VALUES (?, ?, ?, ?, ?, ?, ?)";
//            String insertStockQuery = "INSERT INTO kartu_stock (id_kartu, id_pupuk, tanggal, jumlah_masuk, sisa, keterangan) VALUES (?, ?, ?, ?, ?, ?)";

            for (int i = 0; i < comboList.size(); i++) {
                String namaPupuk = comboList.get(i).getSelectedItem().toString();
                String idPupuk = pupukMap.get(namaPupuk);
                int jumlahRestock = Integer.parseInt(jumlahList.get(i).getText().trim());

                // Insert ke detail_restock
                PreparedStatement psDetail = con.prepareStatement(insertDetailRestockQuery);
                psDetail.setString(1, newIdRestock);
                psDetail.setString(2, idPupuk);
                psDetail.setInt(3, jumlahRestock);
                psDetail.executeUpdate();

                // Ambil sisa stok terakhir
                int stokSebelumnya = 0;
                String stokQuery = "SELECT sisa FROM kartu_stock WHERE id_pupuk = ? ORDER BY id_kartu DESC LIMIT 1";
                PreparedStatement psStok = con.prepareStatement(stokQuery);
                psStok.setString(1, idPupuk);
                ResultSet rsStok = psStok.executeQuery();
                if (rsStok.next()) {
                    stokSebelumnya = rsStok.getInt("sisa");
                }
                rsStok.close();

                int stokBaru = stokSebelumnya + jumlahRestock;
                String newIdKartu = String.format("kst_%03d", ++lastKartuNum);

            }

            JOptionPane.showMessageDialog(null, "Stok pupuk berhasil ditambahkan!");
            con.close();
            loadData();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
        }

    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Silakan pilih data yang ingin diedit!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String idRestock = table.getValueAt(row, 0).toString();   // Ambil id_restock
        String idPupuk = table.getValueAt(row, 1).toString();     // Ambil id_pupuk (pastikan ini disimpan di kolom tabel)

        String SUrl = "jdbc:mysql://localhost:3306/studicase_pupuk";
        String SUser = "root";
        String SPass = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);

            // Ambil data lama dari detail_restock
            String query = "SELECT jumlah FROM detail_restock WHERE id_restock = ? AND id_pupuk = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, idRestock);
            ps.setString(2, idPupuk);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int jumlahLama = rs.getInt("jumlah");

            rs.close();
            ps.close();

            // Form edit jumlah
            JTextField tfJumlah = new JTextField(String.valueOf(jumlahLama));

            JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
            panel.add(new JLabel("Jumlah Restock:"));
            panel.add(tfJumlah);

            int result = JOptionPane.showConfirmDialog(null, panel, "Edit Jumlah Restock", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result != JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(null, "Edit dibatalkan.");
                return;
            }

            int jumlahBaru = Integer.parseInt(tfJumlah.getText().trim());

            // Update jumlah di detail_restock
            String updateQuery = "UPDATE detail_restock SET jumlah = ? WHERE id_restock = ? AND id_pupuk = ?";
            PreparedStatement psUpdate = con.prepareStatement(updateQuery);
            psUpdate.setInt(1, jumlahBaru);
            psUpdate.setString(2, idRestock);
            psUpdate.setString(3, idPupuk);
            psUpdate.executeUpdate();
            psUpdate.close();

            JOptionPane.showMessageDialog(null, "Data restock berhasil diperbarui!");
            loadData();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Silakan pilih data yang ingin dihapus!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String idRestock = table.getValueAt(row, 0).toString();  // id_restock
        String idPupuk = table.getValueAt(row, 1).toString();    // id_pupuk

        int confirm = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        String SUrl = "jdbc:mysql://localhost:3306/studicase_pupuk";
        String SUser = "root";
        String SPass = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);

            // Hapus dari detail_restock
            String deleteDetail = "DELETE FROM detail_restock WHERE id_restock = ? AND id_pupuk = ?";
            PreparedStatement psDeleteDetail = con.prepareStatement(deleteDetail);
            psDeleteDetail.setString(1, idRestock);
            psDeleteDetail.setString(2, idPupuk);
            psDeleteDetail.executeUpdate();
            psDeleteDetail.close();

            // Cek apakah masih ada data di detail_restock dengan id_restock yang sama
            String checkQuery = "SELECT COUNT(*) AS total FROM detail_restock WHERE id_restock = ?";
            PreparedStatement psCheck = con.prepareStatement(checkQuery);
            psCheck.setString(1, idRestock);
            ResultSet rsCheck = psCheck.executeQuery();

            if (rsCheck.next() && rsCheck.getInt("total") == 0) {
                // Hapus dari restock_pupuk jika tidak ada detail terkait
                String deleteRestock = "DELETE FROM restock_pupuk WHERE id_restock = ?";
                PreparedStatement psDeleteRestock = con.prepareStatement(deleteRestock);
                psDeleteRestock.setString(1, idRestock);
                psDeleteRestock.executeUpdate();
                psDeleteRestock.close();
            }
            loadData();

            rsCheck.close();
            psCheck.close();

            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    public void searchUser(String keyword) {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection c = Koneksi.getKoneksi();
            String sql = "SELECT ks.id_kartu, dp.nama_pupuk, ks.jumlah_masuk, ks.tanggal, ks.keterangan "
                    + "FROM kartu_stock ks "
                    + "JOIN data_pupuk dp ON ks.id_pupuk = dp.id_pupuk "
                    + "WHERE ks.jumlah_masuk > 0 AND ("
                    + "ks.id_kartu LIKE ? OR "
                    + "dp.nama_pupuk LIKE ? OR "
                    + "ks.jumlah_masuk LIKE ? OR "
                    + "ks.tanggal LIKE ? OR "
                    + "ks.keterangan LIKE ?) "
                    + "ORDER BY ks.tanggal ASC";

            PreparedStatement ps = c.prepareStatement(sql);

            String searchKeyword = "%" + keyword + "%";
            for (int i = 1; i <= 5; i++) {
                ps.setString(i, searchKeyword);
            }

            ResultSet r = ps.executeQuery();

            int no = 1;
            boolean adaData = false;
            while (r.next()) {
                Object[] row = new Object[5];
                row[0] = no++;
                row[1] = r.getString("nama_pupuk");
                row[2] = r.getInt("jumlah_masuk");
                row[3] = r.getDate("tanggal");
                row[4] = r.getString("keterangan");

                model.addRow(row);
                adaData = true;
            }

            if (!adaData) {
                model.addRow(new Object[]{"Data tidak ditemukan", "", "", "", ""});
            }

            r.close();
            ps.close();
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
