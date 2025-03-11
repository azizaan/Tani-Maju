/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaswingdev.form.admin;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class BarcodeGenerator {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/studicase_pupuk";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    public static BufferedImage generateBarcode(String kodePupuk) {
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(kodePupuk, BarcodeFormat.CODE_128, 300, 100);
            return MatrixToImageWriter.toBufferedImage(matrix);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String saveBarcodeImage(String idPupuk) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String query = "SELECT kode_pupuk FROM data_pupuk WHERE id_pupuk = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, idPupuk);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String kodePupuk = rs.getString("kode_pupuk");
                BufferedImage barcode = generateBarcode(kodePupuk);
                if (barcode != null) {
                    String filePath = "barcodes/" + kodePupuk + ".png";
                    File outputfile = new File(filePath);
                    outputfile.getParentFile().mkdirs(); // Buat folder jika belum ada
                    ImageIO.write(barcode, "png", outputfile);
                    return filePath;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Data not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            rs.close();
            pst.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
