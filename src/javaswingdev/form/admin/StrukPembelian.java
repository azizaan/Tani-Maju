/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaswingdev.form.admin;

/**
 *
 * @author ACER
 */
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.io.File;
import java.io.*;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javaswingdev.login.SessionManager;

public class StrukPembelian {

    public static void simpanStrukPDF(String transaksiID, List<String[]> dataTransaksi, String pelanggan, double totalBayar, double tunai, int diskon, double subtotal) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Document document = new Document(PageSize.A6);
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Font fontTitle = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font fontNormal = new Font(Font.HELVETICA, 8, Font.NORMAL);
            Font fontBold = new Font(Font.HELVETICA, 8, Font.BOLD);

            // Tambahkan logo di atas nama toko
            try {
                Image logo = Image.getInstance("src/Icon/Logo_panjang.png"); // Sesuaikan dengan path logo
                logo.scaleToFit(150, 150);
                logo.setAlignment(Element.ALIGN_CENTER);
                document.add(logo);
            } catch (Exception e) {
                System.err.println("Gagal menambahkan logo: " + e.getMessage());
            }

            // Header
            Paragraph toko = new Paragraph("PT TANI MAJU SEJAHTERA", fontTitle);
            toko.setAlignment(Element.ALIGN_CENTER);
            document.add(toko);

            Paragraph alamat = new Paragraph("AJUNG - JEMBER\nDSN CURAHKATES RT03RW10\nKLOMPANGAN AJUNG JEMBER, 68175\n", fontNormal);
            alamat.setAlignment(Element.ALIGN_CENTER);
            document.add(alamat);
            document.add(new Paragraph("-----------------------------------------------------------------------------------", fontNormal));

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy - HH:mm");
            String waktu = sdf.format(new Date());
            String namaKasir = SessionManager.currentName;
            // Informasi transaksi dua kolom
            PdfPTable infoTable = new PdfPTable(2);
            infoTable.setWidthPercentage(100);
            infoTable.setWidths(new float[]{1f, 1f});
            infoTable.setSpacingBefore(2f); // sebelumnya 10f
            infoTable.setSpacingAfter(2f);  // sebelumnya 5f

// Baris 1: Date - Cashier
            infoTable.addCell(createInfoCell("Tanggal & Waktu", Element.ALIGN_LEFT, fontNormal));
            infoTable.addCell(createInfoCell("Nama Kasir", Element.ALIGN_RIGHT, fontNormal));

            infoTable.addCell(createInfoCell(waktu, Element.ALIGN_LEFT, fontBold));
            infoTable.addCell(createInfoCell(namaKasir, Element.ALIGN_RIGHT, fontBold));

// Baris 2: Trx ID - Customer name
            infoTable.addCell(createInfoCell("Transaksi ID", Element.ALIGN_LEFT, fontNormal));
            infoTable.addCell(createInfoCell("Nama Pelanggan", Element.ALIGN_RIGHT, fontNormal));

            infoTable.addCell(createInfoCell(transaksiID, Element.ALIGN_LEFT, fontBold));
            infoTable.addCell(createInfoCell(pelanggan, Element.ALIGN_RIGHT, fontBold));

            document.add(infoTable);

            Paragraph garis = new Paragraph("-----------------------------------------------------------------------------------", fontNormal);
            garis.setSpacingBefore(0f); // Tambahan untuk mengurangi jarak atas
            garis.setSpacingAfter(0f);  // Tambahan untuk mengurangi jarak bawah
            garis.setAlignment(Element.ALIGN_CENTER);
            document.add(garis);

            DecimalFormat numberFormat = new DecimalFormat("#,###");

            // Tabel Item
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{3, 1, 3, 3});

            // Header tabel
            table.addCell(getStyledCell("Nama Item", fontBold, Element.ALIGN_LEFT));
            table.addCell(getStyledCell("Qty", fontBold, Element.ALIGN_CENTER));
            table.addCell(getStyledCell("Harga/pcs", fontBold, Element.ALIGN_RIGHT));
            table.addCell(getStyledCell("Total Harga", fontBold, Element.ALIGN_RIGHT));

            for (String[] item : dataTransaksi) {
                table.addCell(getStyledCell(item[0], fontNormal, Element.ALIGN_LEFT));
                table.addCell(getStyledCell(String.valueOf((int) Double.parseDouble(item[2])), fontNormal, Element.ALIGN_CENTER));
                int hargaPerPcs = Integer.parseInt(item[1].replaceAll("[^0-9]", ""));
                table.addCell(getStyledCell(numberFormat.format(hargaPerPcs), fontNormal, Element.ALIGN_RIGHT));
                int totalHarga = hargaPerPcs * (int) Double.parseDouble(item[2]);
                table.addCell(getStyledCell(numberFormat.format(totalHarga), fontNormal, Element.ALIGN_RIGHT));
            }

            document.add(table);
            document.add(new Paragraph("-----------------------------------------------------------------------------------", fontNormal));

            // Total Pembayaran
            PdfPTable tableTotal = new PdfPTable(2);
            tableTotal.setWidthPercentage(100);
            tableTotal.setWidths(new float[]{4, 2});

            tableTotal.addCell(getStyledCell("SUBTOTAL :", fontNormal, Element.ALIGN_LEFT));
            tableTotal.addCell(getStyledCell("Rp " + String.format("%,.2f", subtotal), fontNormal, Element.ALIGN_RIGHT));

            tableTotal.addCell(getStyledCell("DISKON :", fontNormal, Element.ALIGN_LEFT));
            tableTotal.addCell(getStyledCell(diskon + " %", fontNormal, Element.ALIGN_RIGHT));

            tableTotal.addCell(getStyledCell("TOTAL :", fontBold, Element.ALIGN_LEFT));
            tableTotal.addCell(getStyledCell("Rp " + String.format("%,.2f", totalBayar), fontBold, Element.ALIGN_RIGHT));

            tableTotal.addCell(getStyledCell("TUNAI :", fontNormal, Element.ALIGN_LEFT));
            tableTotal.addCell(getStyledCell("Rp " + String.format("%,.2f", tunai), fontNormal, Element.ALIGN_RIGHT));

            tableTotal.addCell(getStyledCell("KEMBALI :", fontBold, Element.ALIGN_LEFT));
            tableTotal.addCell(getStyledCell("Rp " + String.format("%,.2f", tunai - totalBayar), fontBold, Element.ALIGN_RIGHT));

            document.add(tableTotal);
            document.add(new Paragraph("-----------------------------------------------------------------------------------", fontNormal));

            // Footer
            Paragraph footer = new Paragraph("LAYANAN KONSUMEN\nSMS/WA 082143618116 \ntanimaju1224@gmail.com\n\nBELANJA LEBIH MUDAH DI TaniKasir\n", fontNormal);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            document.close();

            // Simpan sementara ke file temp
            File tempFile = File.createTempFile("Struk", ".pdf");
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(outputStream.toByteArray());
            }

            // Buka file PDF langsung untuk dicetak
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(tempFile);
                Thread.sleep(2000); // tunggu PDF terbuka

                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_P);
                robot.keyRelease(KeyEvent.VK_P);
                robot.keyRelease(KeyEvent.VK_CONTROL);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menampilkan struk: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private static PdfPCell createInfoCell(String text, int alignment, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    private static PdfPCell getStyledCell(String text, Font font, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(alignment);
        return cell;
    }
}
