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
import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StrukPembelian {

    public static void simpanStrukPDF(List<String[]> dataTransaksi, String pelanggan, double totalBayar, double tunai, int diskon, double subtotal) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Simpan Struk");
        fileChooser.setSelectedFile(new File("Struk_Indomaret.pdf"));

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File fileToSave = fileChooser.getSelectedFile();
        String filePath = fileToSave.getAbsolutePath();
        if (!filePath.endsWith(".pdf")) {
            filePath += ".pdf";
        }

        Document document = new Document(PageSize.A6);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Font fontTitle = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font fontNormal = new Font(Font.HELVETICA, 8, Font.NORMAL);
            Font fontBold = new Font(Font.HELVETICA, 8, Font.BOLD);

            // Header
            Paragraph toko = new Paragraph("PT TANI MAJU SEJAHTERA\nTaniKasir\n", fontTitle);
            toko.setAlignment(Element.ALIGN_CENTER);
            document.add(toko);

            Paragraph alamat = new Paragraph("AJUNG - JEMBER\nDSN CURAHKATES RT03RW10\nKLOMPANGAN AJUNG JEMBER, 68175\n", fontNormal);
            alamat.setAlignment(Element.ALIGN_CENTER);
            document.add(alamat);
            document.add(new Paragraph("-----------------------------------------------------------------------------------", fontNormal));

            // Waktu Transaksi
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy - HH:mm");
            String waktu = sdf.format(new Date());
            document.add(new Paragraph(waktu + " /Trx001/Nama kasir\n", fontNormal));

            document.add(new Paragraph("-----------------------------------------------------------------------------------", fontNormal));

            // Format angka dengan titik sebagai pemisah ribuan
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
                String hargaPerPcsStr = item[1].replaceAll("[^0-9]", "");
                int hargaPerPcs = hargaPerPcsStr.isEmpty() ? 0 : Integer.parseInt(hargaPerPcsStr);
                String hargaFormatted = numberFormat.format(hargaPerPcs);
                table.addCell(getStyledCell(hargaFormatted, fontNormal, Element.ALIGN_RIGHT));
                int qty = (int) Double.parseDouble(item[2]);
                int totalHarga = hargaPerPcs * qty;
                String totalHargaFormatted = numberFormat.format(totalHarga);
                table.addCell(getStyledCell(totalHargaFormatted, fontNormal, Element.ALIGN_RIGHT));
            }

            document.add(table);
            document.add(new Paragraph("-----------------------------------------------------------------------------------", fontNormal));

            // Total Pembayaran
            double kembalian = tunai - totalBayar;

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
            JOptionPane.showMessageDialog(null, "Struk berhasil disimpan di: " + filePath, "Sukses", JOptionPane.INFORMATION_MESSAGE);
            // **Langsung Membuka dan Mencetak Struk**
            try {
                File savedFile = new File(filePath);
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(savedFile);  // Membuka file PDF setelah disimpan
                    desktop.print(savedFile); // Mencetak file PDF secara otomatis
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Gagal membuka/mencetak struk: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan struk: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private static PdfPCell getStyledCell(String text, Font font, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(alignment);
        return cell;
    }
}
