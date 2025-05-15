package javaswingdev.form.admin;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javaswingdev.login.SessionManager;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Stock_opname extends javax.swing.JPanel {

    public Stock_opname(String name) {
        initComponents();
        lb.setText("Data " + name);
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Mencegah edit langsung di tabel
            }
        };

        table.setModel(model);

        model.addColumn("id Pupuk");
        model.addColumn("Nama Pupuk");
        model.addColumn("Harga Pupuk");
        model.addColumn("Stock Sistem");
        model.addColumn("Stock Fisik");
        model.addColumn("Tanggal Opname");
        model.addColumn("Total Selisih");
        model.addColumn("Keterangan");

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
        loadData();
//        table.setEnabled(false);
    }

    private DefaultTableModel model;

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
                Stock_opname.this.mouseClicked(evt);
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

        btn_tambah.setForeground(new java.awt.Color(60, 60, 180));
        btn_tambah.setText("Tambah");
        btn_tambah.setBorderPainted(false);
        btn_tambah.setRadius(30);
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_edit.setText("myButton1");
        btn_edit.setBorderPainted(false);
        btn_edit.setRadius(30);
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_hapus.setText("myButton1");
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
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    public void loadData() {
        String SUrl = "jdbc:mysql://localhost:3306/studicase_pupuk";
        String SUser = "root";
        String SPass = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
            Statement st = con.createStatement();

            String query = "SELECT dp.id_pupuk, dp.nama_pupuk, dp.harga_pupuk, "
                    + "do.stock_sistem, do.stock_fisik, so.tanggal_opname, "
                    + "(do.stock_fisik - do.stock_sistem) AS selisih, so.keterangan "
                    + "FROM stock_opname so "
                    + "JOIN detail_opname do ON so.id_opname = do.id_opname "
                    + "JOIN data_pupuk dp ON do.id_pupuk = dp.id_pupuk "
                    + "ORDER BY so.tanggal_opname DESC";

            ResultSet rs = st.executeQuery(query);

            model.setRowCount(0);

            while (rs.next()) {
                int sistem = rs.getInt("stock_sistem");
                int fisik = rs.getInt("stock_fisik");
                int selisih = fisik - sistem;

                model.addRow(new Object[]{
                    rs.getString("id_pupuk"),
                    rs.getString("nama_pupuk"),
                    rs.getString("harga_pupuk"),
                    sistem,
                    fisik,
                    rs.getDate("tanggal_opname"),
                    selisih,
                    rs.getString("keterangan")
                });
            }

            rs.close();
            st.close();
            con.close();

            table.setModel(model);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal load data: " + e.getMessage());
            e.printStackTrace();
        }
    }


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
        String idOpname = null;
        String SUrl = "jdbc:MySQL://localhost:3306/studicase_pupuk";
        String SUser = "root";
        String SPass = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
            Statement st = con.createStatement();

            // Ambil data pupuk dan jumlah stock ke dua Map
            Map<String, String> pupukMap = new LinkedHashMap<>();
            Map<String, Integer> stockMap = new HashMap<>();

            // Ambil data pupuk dan sisa terbaru dari kartu_stock
            ResultSet rs = st.executeQuery(
                    "SELECT dp.id_pupuk, dp.kode_pupuk, dp.nama_pupuk, ks.sisa "
                    + "FROM data_pupuk dp "
                    + "JOIN ("
                    + "   SELECT k1.* FROM kartu_stock k1 "
                    + "   INNER JOIN ("
                    + "       SELECT id_pupuk, MAX(tanggal) AS max_tanggal "
                    + "       FROM kartu_stock "
                    + "       GROUP BY id_pupuk"
                    + "   ) k2 ON k1.id_pupuk = k2.id_pupuk AND k1.tanggal = k2.max_tanggal"
                    + ") ks ON dp.id_pupuk = ks.id_pupuk"
            );
            while (rs.next()) {
                String idPupuk = rs.getString("id_pupuk");
                String label = rs.getString("kode_pupuk") + " - " + rs.getString("nama_pupuk");
                pupukMap.put(label, idPupuk);
                stockMap.put(label, rs.getInt("sisa")); // pakai kolom "sisa" dari kartu_stock
            }

            rs.close();

            JComboBox<String> cbPupuk = new JComboBox<>(pupukMap.keySet().toArray(new String[0]));
            JTextField tfStockSistem = new JTextField(15);
            tfStockSistem.setEditable(false);

            JTextField tfJumlahFisik = new JTextField(15);
            JTextField tfKeterangan = new JTextField(15);

            // Listener ganti nilai stock sistem dari Map
            cbPupuk.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        String selectedLabel = (String) cbPupuk.getSelectedItem();
                        tfStockSistem.setText(String.valueOf(stockMap.get(selectedLabel)));
                    }
                }
            });

            // Set nilai awal stock sistem
            if (cbPupuk.getItemCount() > 0) {
                cbPupuk.setSelectedIndex(0);
            }

            JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
            panel.add(new JLabel("Pilih Pupuk:"));
            panel.add(cbPupuk);
            panel.add(new JLabel("Stock Sistem:"));
            panel.add(tfStockSistem);
            panel.add(new JLabel("Jumlah Fisik:"));
            panel.add(tfJumlahFisik);
            panel.add(new JLabel("Keterangan:"));
            panel.add(tfKeterangan);

            int result = JOptionPane.showConfirmDialog(null, panel, "Input Stock Opname", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                String selectedLabel = (String) cbPupuk.getSelectedItem();
                String idPupuk = pupukMap.get(selectedLabel);
                int jumlahSistem = stockMap.get(selectedLabel);
                String jumlahFisikStr = tfJumlahFisik.getText().trim();
                String keterangan = tfKeterangan.getText().trim();

                if (jumlahFisikStr.isEmpty() || !jumlahFisikStr.matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "Jumlah fisik tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int jumlahFisik = Integer.parseInt(jumlahFisikStr);

                // Generate ID Opname
                rs = st.executeQuery("SELECT id_opname FROM stock_opname ORDER BY id_opname DESC LIMIT 1");
                if (rs.next()) {
                    int num = Integer.parseInt(rs.getString("id_opname").substring(3));
                    idOpname = String.format("opn%03d", num + 1);
                } else {
                    idOpname = "opn001";
                }
                rs.close();

                String UserId = SessionManager.currentUserIDKasir;
                String tanggal = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

                String insertOpname = "INSERT INTO stock_opname (id_opname, tanggal_opname, user_id, keterangan) "
                        + "VALUES ('" + idOpname + "', '" + tanggal + "', '" + UserId + "', '" + keterangan + "')";
                st.execute(insertOpname);

                String insertDetail = "INSERT INTO detail_opname (id_opname, id_pupuk, stock_sistem, stock_fisik) "
                        + "VALUES ('" + idOpname + "', '" + idPupuk + "', " + jumlahSistem + ", " + jumlahFisik + ")";
                st.execute(insertDetail);

                JOptionPane.showMessageDialog(null, "Stock opname berhasil ditambahkan!");
                loadData();
            } else {
                JOptionPane.showMessageDialog(null, "Input dibatalkan.");
            }

            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed

    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed

    }//GEN-LAST:event_btn_hapusActionPerformed

    public void searchUser(String keyword) {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studicase_pupuk", "root", "");

            String sql = "SELECT dp.id_pupuk, dp.nama_pupuk, dp.harga_pupuk, "
                    + "do.stock_sistem, do.stock_fisik, so.tanggal_opname, "
                    + "(do.stock_fisik - do.stock_sistem) AS selisih, so.keterangan "
                    + "FROM stock_opname so "
                    + "JOIN detail_opname do ON so.id_opname = do.id_opname "
                    + "JOIN data_pupuk dp ON do.id_pupuk = dp.id_pupuk "
                    + "WHERE dp.id_pupuk LIKE ? OR dp.nama_pupuk LIKE ? OR dp.harga_pupuk LIKE ? "
                    + "OR do.stock_sistem LIKE ? OR do.stock_fisik LIKE ? "
                    + "OR so.tanggal_opname LIKE ? OR so.keterangan LIKE ? "
                    + "ORDER BY so.tanggal_opname DESC";

            PreparedStatement ps = c.prepareStatement(sql);

            String searchKeyword = "%" + keyword + "%";
            for (int i = 1; i <= 7; i++) {
                ps.setString(i, searchKeyword);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int sistem = rs.getInt("stock_sistem");
                int fisik = rs.getInt("stock_fisik");
                int selisih = fisik - sistem;

                model.addRow(new Object[]{
                    rs.getString("id_pupuk"),
                    rs.getString("nama_pupuk"),
                    rs.getString("harga_pupuk"),
                    sistem,
                    fisik,
                    rs.getDate("tanggal_opname"),
                    selisih,
                    rs.getString("keterangan")
                });
            }

            if (model.getRowCount() == 0) {
                model.addRow(new Object[]{"Tidak ada data yang relevan", "", "", "", "", "", "", ""});
            }

            rs.close();
            ps.close();
            c.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal cari data: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("branch hahay");
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
