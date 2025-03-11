package javaswingdev.form.admin;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.toedter.calendar.JDateChooser;
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

        // Menghapus kolom password dari tabel
        model.addColumn("ID Restock");
        model.addColumn("ID Stock");
//        model.addColumn("Nama Pupuk");
        model.addColumn("Jumlah Restock");
        model.addColumn("Tanggal Restock");

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

            // Menggunakan JOIN untuk mengambil nama_pupuk berdasarkan id_stock
            String sql = "SELECT r.id_restock, s.id_stock, p.nama_pupuk, r.jumlah_restock, r.tgl_restock "
                    + "FROM restock_pupuk r "
                    + "JOIN stock_pupuk s ON r.id_stock = s.id_stock "
                    + "JOIN data_pupuk p ON s.id_pupuk = p.id_pupuk";

            ResultSet r = s.executeQuery(sql);

            while (r.next()) {
                Object[] o = new Object[4]; // Sesuaikan ukuran array dengan jumlah kolom
                o[0] = r.getString("id_restock");     // ID Restock
                o[1] = r.getString("id_stock");       // ID Stock
//                o[2] = r.getString("nama_pupuk");     // Nama Pupuk
                o[2] = r.getString("jumlah_restock"); // Jumlah Restock
                o[3] = r.getString("tgl_restock");// Tanggal Restock

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
                Form_Restock_Pupuk.this.mouseClicked(evt);
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
    private String selectedIdPupuk;
    private String selectedNamaPupuk;
    private String selectedHargaPupuk;
    private String selectedKodePupuk;


    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        String idPupuk = null, namaPupuk = null, jumlahStock = null;
        String SUrl = "jdbc:mysql://localhost:3306/studicase_pupuk";
        String SUser = "root";
        String SPass = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
            Statement st = con.createStatement();

            // Ambil daftar pupuk dari database
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

            // Membuat panel untuk input data stok
            JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
            JComboBox<String> cbPupuk = new JComboBox<>(pupukList.toArray(new String[0]));
            JTextField tfJumlahStock = new JTextField(15);
            JDateChooser dateChooser = new JDateChooser();
            dateChooser.setDateFormatString("yyyy-MM-dd HH:mm:ss");

            // Menambahkan komponen ke panel
            panel.add(new JLabel("Pilih Pupuk:"));
            panel.add(cbPupuk);
            panel.add(new JLabel("Jumlah Stock:"));
            panel.add(tfJumlahStock);
            panel.add(new JLabel("Tanggal Restock:"));
            panel.add(dateChooser);

            int result = JOptionPane.showConfirmDialog(null, panel,
                    "Tambah Stock Pupuk", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                namaPupuk = cbPupuk.getSelectedItem().toString();
                idPupuk = pupukMap.get(namaPupuk);
                jumlahStock = tfJumlahStock.getText().trim();
                Date selectedDate = dateChooser.getDate();

                // Validasi jumlah stok
                if (jumlahStock.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Jumlah stok harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (!jumlahStock.matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(null, "Jumlah stok harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (selectedDate == null) {
                    JOptionPane.showMessageDialog(null, "Silakan pilih tanggal restock!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String tanggalRestock = sdf.format(selectedDate);
                int stokBaru = Integer.parseInt(jumlahStock);

                // Periksa apakah stok pupuk sudah ada di tabel stock_pupuk
                String checkStockQuery = "SELECT id_stock, jumlah_stock FROM stock_pupuk WHERE id_pupuk = ?";
                PreparedStatement psCheckStock = con.prepareStatement(checkStockQuery);
                psCheckStock.setString(1, idPupuk);
                ResultSet rsStock = psCheckStock.executeQuery();

                String idStock;
                if (rsStock.next()) {
                    // Jika stok sudah ada, update jumlahnya
                    int stokLama = rsStock.getInt("jumlah_stock");
                    int totalStok = stokLama + stokBaru;
                    idStock = rsStock.getString("id_stock");

                    String updateQuery = "UPDATE stock_pupuk SET jumlah_stock = ? WHERE id_stock = ?";
                    PreparedStatement psUpdate = con.prepareStatement(updateQuery);
                    psUpdate.setInt(1, totalStok);
                    psUpdate.setString(2, idStock);
                    psUpdate.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Stok pupuk berhasil diperbarui!");
                } else {
                    // Jika stok belum ada, tambahkan stok baru
                    String insertQuery = "INSERT INTO stock_pupuk (id_pupuk, jumlah_stock) VALUES (?, ?)";
                    PreparedStatement psInsertStock = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                    psInsertStock.setString(1, idPupuk);
                    psInsertStock.setInt(2, stokBaru);
                    psInsertStock.executeUpdate();

                    // Ambil ID Stock yang baru ditambahkan
                    ResultSet rsGenKeys = psInsertStock.getGeneratedKeys();
                    if (rsGenKeys.next()) {
                        idStock = rsGenKeys.getString(1);
                    } else {
                        throw new SQLException("Gagal mendapatkan ID stok.");
                    }

                    JOptionPane.showMessageDialog(null, "Stok pupuk berhasil ditambahkan!");
                }
                rsStock.close();

                // **Menentukan ID Restock Baru**
                String getLastRestockIdQuery = "SELECT id_restock FROM restock_pupuk ORDER BY id_restock DESC LIMIT 1";
                ResultSet rsLastRestock = st.executeQuery(getLastRestockIdQuery);
                String newIdRestock = "rst_001"; // Default jika belum ada data

                if (rsLastRestock.next()) {
                    String lastId = rsLastRestock.getString("id_restock"); // Misalnya rst_005
                    int lastNumber = Integer.parseInt(lastId.split("_")[1]); // Ambil angka 5
                    newIdRestock = String.format("rst_%03d", lastNumber + 1); // Jadi rst_006
                }
                rsLastRestock.close();

                // Simpan data restock ke tabel restock_pupuk dengan ID baru
                String insertRestockQuery = "INSERT INTO restock_pupuk (id_restock, id_stock, jumlah_restock, tgl_restock) VALUES (?, ?, ?, ?)";
                PreparedStatement psInsertRestock = con.prepareStatement(insertRestockQuery);
                psInsertRestock.setString(1, newIdRestock);
                psInsertRestock.setString(2, idStock);
                psInsertRestock.setInt(3, stokBaru);
                psInsertRestock.setString(4, tanggalRestock);
                psInsertRestock.executeUpdate();

//                JOptionPane.showMessageDialog(null, "Restock berhasil dicatat dengan ID: " + newIdRestock);
                psCheckStock.close();
                con.close();
            } else {
                JOptionPane.showMessageDialog(null, "Penambahan stok dibatalkan.");
            }

        } catch (Exception e) {
            System.out.println("Error! " + e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // Pastikan pengguna sudah memilih baris di tabel
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Silakan pilih data yang ingin diedit!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String idRestock = table.getValueAt(row, 0).toString();  // Ambil id_restock dari tabel

        String SUrl = "jdbc:mysql://localhost:3306/studicase_pupuk";
        String SUser = "root";
        String SPass = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);

            // Ambil data lama dari restock_pupuk
            String query = "SELECT id_stock, jumlah_restock FROM restock_pupuk WHERE id_restock = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, idRestock);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String idStock = rs.getString("id_stock");
            int jumlahRestockLama = rs.getInt("jumlah_restock");

            rs.close();
            ps.close();

            // Form edit hanya untuk jumlah_restock
            JTextField tfJumlahRestock = new JTextField(String.valueOf(jumlahRestockLama));

            JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
            panel.add(new JLabel("Jumlah Restock:"));
            panel.add(tfJumlahRestock);

            int result = JOptionPane.showConfirmDialog(null, panel, "Edit Restock", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result != JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(null, "Edit dibatalkan.");
                return;
            }

            int jumlahRestockBaru = Integer.parseInt(tfJumlahRestock.getText().trim());

            // Hitung perubahan stok
            int selisih = jumlahRestockBaru - jumlahRestockLama;

            // Update jumlah_restock di restock_pupuk
            String updateRestockQuery = "UPDATE restock_pupuk SET jumlah_restock = ? WHERE id_restock = ?";
            PreparedStatement psUpdateRestock = con.prepareStatement(updateRestockQuery);
            psUpdateRestock.setInt(1, jumlahRestockBaru);
            psUpdateRestock.setString(2, idRestock);
            psUpdateRestock.executeUpdate();
            psUpdateRestock.close();

            // Update jumlah_stock di stock_pupuk
            String updateStockQuery = "UPDATE stock_pupuk SET jumlah_stock = jumlah_stock + ? WHERE id_stock = ?";
            PreparedStatement psUpdateStock = con.prepareStatement(updateStockQuery);
            psUpdateStock.setInt(1, selisih);
            psUpdateStock.setString(2, idStock);
            psUpdateStock.executeUpdate();
            psUpdateStock.close();

            JOptionPane.showMessageDialog(null, "Data restock berhasil diperbarui!");

            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
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
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Silakan pilih data yang ingin dihapus!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String idRestock = table.getValueAt(row, 0).toString();  // Ambil id_restock dari tabel

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

            // Ambil id_stock dan jumlah_restock sebelum dihapus
            String selectQuery = "SELECT id_stock, jumlah_restock FROM restock_pupuk WHERE id_restock = ?";
            PreparedStatement psSelect = con.prepareStatement(selectQuery);
            psSelect.setString(1, idRestock);
            ResultSet rs = psSelect.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String idStock = rs.getString("id_stock");
            int jumlahRestock = rs.getInt("jumlah_restock");

            rs.close();
            psSelect.close();

            // Kurangi jumlah stok di stock_pupuk
            String updateStockQuery = "UPDATE stock_pupuk SET jumlah_stock = jumlah_stock - ? WHERE id_stock = ?";
            PreparedStatement psUpdateStock = con.prepareStatement(updateStockQuery);
            psUpdateStock.setInt(1, jumlahRestock);
            psUpdateStock.setString(2, idStock);
            psUpdateStock.executeUpdate();
            psUpdateStock.close();

            // Hapus data dari restock_pupuk
            String deleteQuery = "DELETE FROM restock_pupuk WHERE id_restock = ?";
            PreparedStatement psDelete = con.prepareStatement(deleteQuery);
            psDelete.setString(1, idRestock);
            psDelete.executeUpdate();
            psDelete.close();

            JOptionPane.showMessageDialog(null, "Data restock berhasil dihapus!");

            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    public void searchUser(String keyword) {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection c = Koneksi.getKoneksi();
            String sql = "SELECT id_restock, id_stock, jumlah_restock, tgl_restock FROM restock_pupuk "
                    + "WHERE id_restock LIKE ? OR id_stock LIKE ? OR jumlah_restock LIKE ? OR tgl_restock LIKE ?";
            PreparedStatement ps = c.prepareStatement(sql);

            // Set parameter untuk semua kolom yang dicari
            String searchKeyword = "%" + keyword + "%";
            for (int i = 1; i <= 4; i++) {
                ps.setString(i, searchKeyword);
            }

            ResultSet r = ps.executeQuery();

            // Tambahkan hasil pencarian ke tabel
            boolean adaData = false;
            while (r.next()) {
                Object[] row = {
                    r.getString("id_restock"),
                    r.getString("id_stock"),
                    r.getInt("jumlah_restock"),
                    r.getString("tgl_restock")
                };
                model.addRow(row);
                adaData = true;
            }

            // Jika tidak ada hasil, tambahkan baris "Data tidak ditemukan"
            if (!adaData) {
                model.addRow(new Object[]{"Data tidak ditemukan", "", "", ""});
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
