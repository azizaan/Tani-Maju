/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaswingdev.form.admin;

/**
 *
 * @author ACER
 */
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javaswingdev.login.SessionManager;

public class TestStrukPembelian {

    public static void main(String[] args) {
        // Set dummy nama kasir di SessionManager
        SessionManager.currentName = "Admin Kasir";

        // Buat frame untuk test tombol
        JFrame frame = new JFrame("Tes Cetak Struk");
        JButton btnCetak = new JButton("Cetak Struk Dummy");

        btnCetak.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Dummy data transaksi (nama item, harga, qty)
                List<String[]> dataTransaksi = new ArrayList<>();
                dataTransaksi.add(new String[]{"Urea", "12000", "2"});
                dataTransaksi.add(new String[]{"ZA", "10500", "1"});
                dataTransaksi.add(new String[]{"NPK Phonska", "13500", "3"});
                dataTransaksi.add(new String[]{"SP-36", "11000", "2"});
                dataTransaksi.add(new String[]{"KCL", "9500", "1"});
                dataTransaksi.add(new String[]{"Organik Granul", "8000", "4"});
                dataTransaksi.add(new String[]{"Pupuk Cair", "15000", "2"});

                // Dummy total
                double subtotal = 0;
                for (String[] item : dataTransaksi) {
                    double harga = Double.parseDouble(item[1]);
                    double qty = Double.parseDouble(item[2]);
                    subtotal += harga * qty;
                }

                int diskon = 10; // 10%
                double totalBayar = subtotal - (subtotal * diskon / 100);
                double tunai = totalBayar + 5000; // Lebih bayar
                String pelanggan = "Pak Budi";
                String transaksiID = "TRX" + System.currentTimeMillis();

                // Panggil method simpanStrukPDF
                StrukPembelian.simpanStrukPDF(transaksiID, dataTransaksi, pelanggan, totalBayar, tunai, diskon, subtotal);
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 120);
        frame.setLayout(null);
        btnCetak.setBounds(50, 30, 200, 30);
        frame.add(btnCetak);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
