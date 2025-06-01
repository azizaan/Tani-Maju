package javaswingdev.form.admin;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.Phrase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.Color;
import java.awt.Dimension;
//import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import java.math.BigDecimal;
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
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Form_Data_Pupuk extends javax.swing.JPanel {

    public Form_Data_Pupuk(String name) {
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
        model.addColumn("ID Pupuk");
        model.addColumn("Nama Pupuk");
        model.addColumn("Harga Pupuk (per pcs)");
        model.addColumn("Kode Pupuk");

        loadData();

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

        // Tombol CETAK
        btn_cetak.setText("⎙ Cetak"); // Bisa juga diganti dengan ikon print nanti
        btn_cetak.setColor(new Color(39, 174, 96));            // Warna hijau utama (#27AE60)
        btn_cetak.setColorOver(new Color(33, 150, 83));        // Saat hover (lebih gelap)
        btn_cetak.setColorClick(new Color(28, 130, 72));       // Saat klik (lebih tua)
        btn_cetak.setBorderColor(new Color(33, 150, 83));      // Serasi dengan hover
        btn_cetak.setForeground(Color.WHITE);                 // Tulisan putih
        btn_cetak.setRadius(20);

//        table.setEnabled(false);
    }

    private DefaultTableModel model;

    public void loadData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        try {
            Connection c = Koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM v_data_pupuk";
            ResultSet r = s.executeQuery(sql);

            // Format angka ke Rupiah tanpa nol di belakang koma
            DecimalFormat df = new DecimalFormat("#,###.##");
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setGroupingSeparator('.');
            symbols.setDecimalSeparator(',');
            df.setDecimalFormatSymbols(symbols);

            while (r.next()) {
                Object[] o = new Object[4]; // Tanpa kode_pupuk jika tidak diperlukan
                o[0] = r.getString("id_pupuk");
                o[1] = r.getString("nama_pupuk");

                // Ambil harga dari database
                double harga = r.getDouble("harga_pupuk");

                // Format harga tanpa nol di belakang koma
                String hargaFormatted = "Rp" + df.format(harga);
                o[2] = hargaFormatted;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javaswingdev.swing.table.Table();
        txtcari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_tambah = new button.MyButton();
        btn_edit = new button.MyButton();
        btn_hapus = new button.MyButton();
        btn_cetak = new button.MyButton();

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
                Form_Data_Pupuk.this.mouseClicked(evt);
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

        btn_cetak.setText("cetak");
        btn_cetak.setBorderPainted(false);
        btn_cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cetakActionPerformed(evt);
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_cetak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cetak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

            // Panel input data
            JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
            JTextField tfNamaPupuk = new JTextField(15);
            JTextField tfHargaPupuk = new JTextField(15);

            panel.add(new JLabel("Nama Pupuk:"));
            panel.add(tfNamaPupuk);
            panel.add(new JLabel("Harga Pupuk:"));
            panel.add(tfHargaPupuk);

            int result = JOptionPane.showConfirmDialog(null, panel,
                    "Tambah Data Pupuk", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                namaPupuk = tfNamaPupuk.getText().trim();
                hargaPupuk = tfHargaPupuk.getText().trim();

                // Validasi
                if (namaPupuk.isEmpty() || !namaPupuk.matches("[a-zA-Z ]+")) {
                    JOptionPane.showMessageDialog(null, "Nama Pupuk tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (hargaPupuk.isEmpty() || !hargaPupuk.matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(null, "Harga Pupuk tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Generate ID Pupuk
                String getLastIdQuery = "SELECT id_pupuk FROM data_pupuk ORDER BY id_pupuk DESC LIMIT 1";
                ResultSet rs = st.executeQuery(getLastIdQuery);
                if (rs.next()) {
                    String lastId = rs.getString("id_pupuk");
                    int num = Integer.parseInt(lastId.substring(5));
                    idPupuk = String.format("pupuk%03d", num + 1);
                } else {
                    idPupuk = "pupuk001";
                }
                rs.close();

                // Generate Kode Pupuk
                String getLastKodeQuery = "SELECT kode_pupuk FROM data_pupuk ORDER BY kode_pupuk DESC LIMIT 1";
                rs = st.executeQuery(getLastKodeQuery);
                if (rs.next()) {
                    String lastKode = rs.getString("kode_pupuk");
                    int num = Integer.parseInt(lastKode.substring(1));
                    kodePupuk = String.format("P%03d", num + 1);
                } else {
                    kodePupuk = "P001";
                }
                rs.close();

                // Insert ke tabel data_pupuk
                query = "INSERT INTO data_pupuk (id_pupuk, nama_pupuk, harga_pupuk, kode_pupuk) VALUES ('" + idPupuk + "', '" + namaPupuk + "'"
                        + ", '" + hargaPupuk + "', '" + kodePupuk + "')";
                st.execute(query);

                JOptionPane.showMessageDialog(null, "Data pupuk berhasil ditambahkan.");
                loadData(); // Refresh data tabel
            } else {
                JOptionPane.showMessageDialog(null, "Penambahan data dibatalkan.");
            }

            st.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Error! " + e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
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
            JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
            JTextField tfNamaPupuk = new JTextField(selectedNamaPupuk, 15);

            // Menghapus format harga sebelum ditampilkan
            String hargaBersih = selectedHargaPupuk.replaceAll("[^0-9]", "");
            JTextField tfHargaPupuk = new JTextField(hargaBersih, 15);

            panel.add(new JLabel("Nama Pupuk:"));
            panel.add(tfNamaPupuk);
            panel.add(new JLabel("Harga Pupuk:"));
            panel.add(tfHargaPupuk);

            int result = JOptionPane.showConfirmDialog(null, panel, "Edit Data Pupuk", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String namaPupuk = tfNamaPupuk.getText().trim();
                String hargaPupuk = tfHargaPupuk.getText().trim();

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

                // Query Update tanpa mengedit kode_pupuk
                String query = "UPDATE data_pupuk SET nama_pupuk = ?, harga_pupuk = ? WHERE id_pupuk = ?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, namaPupuk);
                pst.setDouble(2, harga);
                pst.setString(3, selectedIdPupuk);

                int updated = pst.executeUpdate();

                if (updated > 0) {
                    JOptionPane.showMessageDialog(null, "Data Pupuk berhasil diperbarui!");
                    loadData(); // Panggil fungsi refresh tabel
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal memperbarui data.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                pst.close();
                con.close();
                loadData();
            } else {
                JOptionPane.showMessageDialog(null, "Edit data dibatalkan.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
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
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cetakActionPerformed
        String[] options = {"Cetak Satu", "Cetak Beberapa", "Cetak Semua"};
        int choice = JOptionPane.showOptionDialog(null, "Pilih opsi cetak:", "Opsi Cetak",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        List<String> idPupukList = new ArrayList<>();
        Map<String, String> pupukMap = getPupukMap(); // Ambil dari DB (id -> nama)

        if (choice == 0) { // Cetak Satu
            String[] namaPupukArray = pupukMap.values().toArray(new String[0]);
            String selectedName = (String) JOptionPane.showInputDialog(null, "Pilih pupuk yang ingin dicetak:", "Pilih Pupuk",
                    JOptionPane.QUESTION_MESSAGE, null, namaPupukArray, namaPupukArray[0]);

            if (selectedName != null) {
                for (Map.Entry<String, String> entry : pupukMap.entrySet()) {
                    if (entry.getValue().equals(selectedName)) {
                        idPupukList.add(entry.getKey());
                        break;
                    }
                }
            }
        } else if (choice == 1) { // Cetak Beberapa
            DefaultTableModel model = new DefaultTableModel(new Object[]{"Nama Pupuk", "Pilih"}, 0);
            for (Map.Entry<String, String> entry : pupukMap.entrySet()) {
                model.addRow(new Object[]{entry.getValue(), false});
            }

            JTable table = new JTable(model) {
                @Override
                public Class<?> getColumnClass(int column) {
                    return column == 1 ? Boolean.class : String.class;
                }
            };

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(400, 300));

            int confirm = JOptionPane.showConfirmDialog(null, scrollPane, "Pilih Pupuk untuk Dicetak", JOptionPane.OK_CANCEL_OPTION);

            if (confirm == JOptionPane.OK_OPTION) {
                for (int i = 0; i < model.getRowCount(); i++) {
                    if ((Boolean) model.getValueAt(i, 1)) {
                        String namaDipilih = (String) model.getValueAt(i, 0);
                        for (Map.Entry<String, String> entry : pupukMap.entrySet()) {
                            if (entry.getValue().equals(namaDipilih)) {
                                idPupukList.add(entry.getKey());
                                break;
                            }
                        }
                    }
                }
            }
        } else if (choice == 2) { // Cetak Semua
            idPupukList.addAll(pupukMap.keySet());
        }

        if (idPupukList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tidak ada data untuk dicetak.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Simpan sebagai PDF");
        chooser.setSelectedFile(new File("barcode_pupuk.pdf"));
        int userSelection = chooser.showSaveDialog(null);

        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = chooser.getSelectedFile();

        try {
            Document document = new Document(PageSize.A4, 50, 50, 50, 50); // Margin
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            for (String idPupuk : idPupukList) {
                String barcodePath = BarcodeGenerator.saveBarcodeImage(idPupuk);
                if (barcodePath == null) {
                    continue;
                }

                // Tambahkan barcode
                Image barcodeImage = Image.getInstance(barcodePath);
                barcodeImage.scaleToFit(200, 80);
                barcodeImage.setAlignment(Image.ALIGN_CENTER);
                document.add(barcodeImage);

                // Tambahkan nama pupuk
                String namaPupuk = pupukMap.getOrDefault(idPupuk, idPupuk);
                Font fontNama = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);
                Paragraph nama = new Paragraph(namaPupuk, fontNama);

                nama.setAlignment(Element.ALIGN_CENTER);
                document.add(nama);

                // Tambahkan spasi antar barcode
                document.add(new Paragraph(" "));
                document.add(Chunk.NEWLINE);
            }

            document.close();
            JOptionPane.showMessageDialog(null, "Barcode berhasil disimpan ke PDF!", "Sukses", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal menyimpan PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_cetakActionPerformed

    public Map<String, String> getPupukMap() {
        Map<String, String> map = new LinkedHashMap<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = Koneksi.getKoneksi(); // pastikan class Koneksi punya method getKoneksi()
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT id_pupuk, nama_pupuk FROM data_pupuk");

            while (rs.next()) {
                String id = rs.getString("id_pupuk");
                String nama = rs.getString("nama_pupuk");
                map.put(id, nama);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal mengambil data pupuk: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return map;
    }

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

    public String formatHarga(double harga) {
        DecimalFormat df = new DecimalFormat("#,###.##");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        df.setDecimalFormatSymbols(symbols);

        return "Rp" + df.format(harga);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.MyButton btn_cetak;
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
