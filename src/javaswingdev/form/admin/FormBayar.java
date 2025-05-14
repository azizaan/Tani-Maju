/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaswingdev.form.admin;

/**
 *
 * @author ACER
 */
import java.sql.Connection;
import java.sql.PreparedStatement; // Import PreparedStatement
import java.sql.Statement; // Import PreparedStatement
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import javaswingdev.form.Koneksi;
import javaswingdev.login.SessionManager;
import javaswingdev.form.admin.Form_Transaksi_Admin;
import javaswingdev.form.admin.StrukPembelian;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.Arrays;
import static javaswingdev.form.admin.StrukPembelian.simpanStrukPDF;

public class FormBayar extends JFrame {

    private JTextField txtSubTotal, txtGrandTotal, txtDibayar, txtKembalian, txtPelanggan;
    private JSpinner spinDiskon;
    private JButton btnSimpan, btnBatal, btnUangPas, btnKosongkan;
    private double totalHarga;
//    public String transaksiID = generateTransaksiID();
    private final DecimalFormat df = new DecimalFormat("#,###");
    private Form_Transaksi_Admin formTransaksiAdmin;
//    private int userID; // Simpan user_id dari login

    private boolean isFormatting = false;

    private List<String[]> dataTransaksi;

    public FormBayar(List dataTransaksi, int total, Form_Transaksi_Admin formTransaksiAdmin) {
        this.totalHarga = total;
        this.dataTransaksi = dataTransaksi;
        this.formTransaksiAdmin = formTransaksiAdmin;
        setTitle("Bayar");
        setSize(700, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);

        JPanel panelInput = new JPanel(new GridBagLayout());

        // Pelanggan (tanpa tombol "Pilih" dan tidak disabled)
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelInput.add(new JLabel("Pelanggan"), gbc);
        txtPelanggan = new JTextField(10);
        gbc.gridx = 1;
        panelInput.add(txtPelanggan, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelInput.add(new JLabel("Sub Total"), gbc);
        txtSubTotal = new JTextField("Rp " + df.format(total), 10);
        txtSubTotal.setEditable(false);
        gbc.gridx = 1;
        panelInput.add(txtSubTotal, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelInput.add(new JLabel("Diskon (%)"), gbc);
        spinDiskon = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        ((JSpinner.DefaultEditor) spinDiskon.getEditor()).getTextField().setHorizontalAlignment(JTextField.RIGHT);
        gbc.gridx = 1;
        panelInput.add(spinDiskon, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelInput.add(new JLabel("Grand Total"), gbc);
        txtGrandTotal = new JTextField("Rp " + df.format(total), 10);
        txtGrandTotal.setEditable(false);
        txtGrandTotal.setBackground(Color.YELLOW);
        gbc.gridx = 1;
        panelInput.add(txtGrandTotal, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelInput.add(new JLabel("Dibayar"), gbc);
        txtDibayar = new JTextField("0", 10);
        gbc.gridx = 1;
        panelInput.add(txtDibayar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panelInput.add(new JLabel("Kembali"), gbc);
        txtKembalian = new JTextField("0", 10);
        txtKembalian.setEditable(false);
        gbc.gridx = 1;
        panelInput.add(txtKembalian, gbc);

        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnUangPas = new JButton("Uang Pas [F7]");
        btnKosongkan = new JButton("Kosongkan [F8]");
        btnSimpan = new JButton("Simpan");
        btnBatal = new JButton("Batal");
        panelButton.add(btnUangPas);
        panelButton.add(btnKosongkan);
        panelButton.add(btnSimpan);
        panelButton.add(btnBatal);

        // Event Listeners
        txtDibayar.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                formatDibayar();
            }

            public void removeUpdate(DocumentEvent e) {
                formatDibayar();
            }

            public void changedUpdate(DocumentEvent e) {
                formatDibayar();
            }
        });

        spinDiskon.addChangeListener(e -> hitungGrandTotal());
        btnUangPas.addActionListener(e -> uangPas());
        btnKosongkan.addActionListener(e -> kosongkan());
        btnSimpan.addActionListener(e -> {
            simpanTransaksi();
        });
        btnBatal.addActionListener(e -> dispose());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(panelInput, gbc);
        gbc.gridy = 1;
        add(panelButton, gbc);
    }

    private void hitungGrandTotal() {
        double subTotal = totalHarga;
        double diskon = ((Number) spinDiskon.getValue()).doubleValue() / 100 * subTotal;
        double grandTotal = subTotal - diskon;
        txtGrandTotal.setText("Rp " + df.format(Math.max(grandTotal, 0)));
        hitungKembalian();
    }

    private void hitungKembalian() {
        double dibayar = getDouble(txtDibayar.getText());
        double grandTotal = getDouble(txtGrandTotal.getText().replace("Rp ", ""));
        double kembali = dibayar - grandTotal;

        if (kembali < 0) {
            txtKembalian.setText("- Rp " + df.format(Math.abs(kembali)));
            txtKembalian.setForeground(Color.RED);
        } else {
            txtKembalian.setText("Rp " + df.format(kembali));
            txtKembalian.setForeground(Color.BLACK);
        }
    }

    private void uangPas() {
        txtDibayar.setText(txtGrandTotal.getText());
        hitungKembalian();
    }

    private void kosongkan() {
        txtDibayar.setText("0");
        txtKembalian.setText("0");
    }

    private String generateTransaksiID(Connection con) throws SQLException {
        String sql = "SELECT transaksi_id FROM transaksi ORDER BY transaksi_id DESC LIMIT 1";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                String lastID = rs.getString("transaksi_id");
                int number = Integer.parseInt(lastID.substring(4)); // Ambil angka setelah 'trx_'
                number++; // Increment
                return String.format("trx_%03d", number); // Format menjadi trx_001, trx_002, dll
            } else {
                return "trx_001"; // Jika belum ada transaksi
            }
        }
    }

    private void simpanTransaksi() {
        String pelanggan = txtPelanggan.getText().trim();
        String userId = SessionManager.currentUserIDKasir;
        double grandTotal = getDouble(txtGrandTotal.getText().replace("Rp ", ""));
        int diskon = (int) spinDiskon.getValue();

        if (pelanggan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pelanggan harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection con = Koneksi.getKoneksi()) {
            if (con == null || con.isClosed()) {
                JOptionPane.showMessageDialog(this, "Koneksi ke database gagal.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            con.setAutoCommit(false);

            String transaksiID = generateTransaksiID(con);

            String sqlTransaksi = "INSERT INTO transaksi (transaksi_id, user_id, nama_pembeli, total_harga, diskon, status, waktu_transaksi) VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

            try (PreparedStatement pstTransaksi = con.prepareStatement(sqlTransaksi, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pstTransaksi.setString(1, transaksiID);
                pstTransaksi.setString(2, userId);
                pstTransaksi.setString(3, pelanggan);
                pstTransaksi.setDouble(4, grandTotal);
                pstTransaksi.setInt(5, diskon);
                pstTransaksi.setString(6, "Lunas");

                int rowsInserted = pstTransaksi.executeUpdate();
                if (rowsInserted == 0) {
                    throw new SQLException("Gagal menyimpan transaksi.");
                }

                String sqlDetail = "INSERT INTO transaksi_detail (transaksi_id, id_pupuk, jumlah_beli) VALUES (?, ?, ?)";
                String sqlInsertKartuStock = "INSERT INTO kartu_stock (id_kartu, id_pupuk, tanggal, jumlah_keluar, sisa, keterangan) VALUES (?, ?, CURRENT_DATE, ?, ?, ?)";

                try (PreparedStatement pstDetail = con.prepareStatement(sqlDetail); PreparedStatement pstInsertKartu = con.prepareStatement(sqlInsertKartuStock)) {

                    for (String[] item : dataTransaksi) {
                        String namaPupuk = item[0];
                        int jumlah = Integer.parseInt(item[2]);

                        String idPupuk = getIdPupuk(con, namaPupuk);
                        if (idPupuk == null) {
                            throw new SQLException("ID pupuk untuk " + namaPupuk + " tidak ditemukan.");
                        }

                        int stokSebelum = getStokTerakhir(con, idPupuk);
                        int stokBaru = stokSebelum - jumlah;
                        String idKartu = generateIdKartu(con); // pastikan method ini Anda buat

                        // simpan detail transaksi
                        pstDetail.setString(1, transaksiID);
                        pstDetail.setString(2, idPupuk);
                        pstDetail.setInt(3, jumlah);
                        pstDetail.addBatch();

//                        // simpan ke kartu_stock sebagai transaksi keluar
//                        pstInsertKartu.setString(1, idKartu);
//                        pstInsertKartu.setString(2, idPupuk);
//                        pstInsertKartu.setInt(3, jumlah); // jumlah_keluar
//                        pstInsertKartu.setInt(4, stokBaru); // sisa stok
//                        pstInsertKartu.setString(5, "Penjualan");
//                        pstInsertKartu.addBatch();
                    }

                    pstDetail.executeBatch();
                    pstInsertKartu.executeBatch();
                }

                con.commit();
                JOptionPane.showMessageDialog(this, "Pembayaran dan transaksi berhasil disimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                formTransaksiAdmin.refreshTable();
                double dibayar = getDouble(txtDibayar.getText().replace("Rp ", ""));
                double subtotal = getDouble(txtSubTotal.getText().replace("Rp ", ""));
                StrukPembelian.simpanStrukPDF(transaksiID, dataTransaksi, pelanggan, grandTotal, dibayar, diskon, subtotal);


                dispose();
            } catch (SQLException e) {
                if (con != null && !con.isClosed()) {
                    con.rollback();
                }
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int getStokTerakhir(Connection con, String idPupuk) throws SQLException {
        String sql = "SELECT sisa FROM kartu_stock WHERE id_pupuk = ? ORDER BY tanggal DESC, id_kartu DESC LIMIT 1";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, idPupuk);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("sisa");
                } else {
                    return 0; // kalau belum ada data
                }
            }
        }
    }

    private String generateIdKartu(Connection con) throws SQLException {
        String prefix = "kst_";
        String query = "SELECT id_kartu FROM kartu_stock WHERE id_kartu LIKE ? ORDER BY id_kartu DESC LIMIT 1";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, prefix + "%");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String lastId = rs.getString("id_kartu");
                    int nextNum = Integer.parseInt(lastId.substring(prefix.length())) + 1;
                    return prefix + String.format("%03d", nextNum);
                } else {
                    return prefix + "001";
                }
            }
        }
    }

// Method to fetch id_pupuk based on nama_pupuk
    private String getIdPupuk(Connection con, String namaPupuk) throws SQLException {
        String idPupuk = null;
        String query = "SELECT id_pupuk FROM data_pupuk WHERE nama_pupuk = ?";

        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, namaPupuk);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                idPupuk = rs.getString("id_pupuk");
            }
        }
        return idPupuk;
    }

    private double getDouble(String text) {
        try {
            return Double.parseDouble(text.replace("Rp ", "").replace(".", "").replace(",", "."));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void formatDibayar() {
        if (isFormatting) {
            return;
        }
        isFormatting = true;

        SwingUtilities.invokeLater(() -> {
            String text = txtDibayar.getText().replace("Rp ", "").replaceAll("[^\\d]", "");

            if (text.isEmpty()) {
                txtDibayar.setText("Rp 0");
                isFormatting = false;
                return;
            }

            try {
                long value = Long.parseLong(text);
                String formatted = "Rp " + df.format(value);

                if (!txtDibayar.getText().equals(formatted)) {
                    txtDibayar.setText(formatted);
                }
                hitungKembalian();
            } catch (NumberFormatException e) {
                txtDibayar.setText("Rp 0");
            }

            isFormatting = false;
        });
    }

    private String generateTransaksiID() {
        String newID = "TRX001";  // Default ID in case the database is empty
        try (Connection con = Koneksi.getKoneksi()) {
            String sql = "SELECT transaksi_id FROM transaksi ORDER BY transaksi_id DESC LIMIT 1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String lastID = rs.getString("transaksi_id");

                // Ensure the last ID is at least 6 characters long ("TRX" + number)
                if (lastID != null && lastID.length() > 3) {
                    String numberPart = lastID.substring(3);  // Get the numeric part after "TRX"
                    int number = Integer.parseInt(numberPart) + 1;
                    newID = String.format("TRX%03d", number);  // Format to ensure leading zeros
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newID;
    }

    public static void main(String[] args) {
        List<String> transaksi = Arrays.asList(new String[]{"Item1", "2", "1000"}); // Sesuaikan format datanya
        new FormBayar(transaksi, 2000, null).setVisible(true);
//        simpanStrukPDF(dataTransaksi, "Pelanggan A", 50000, 60000, 10, 55000);

    }

}
