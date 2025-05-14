package javaswingdev.login;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ACER
 */
import com.fazecast.jSerialComm.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BluetoothReader {
    public static void main(String[] args) {
        SerialPort port = SerialPort.getCommPort("COM4"); // Ganti dengan port yang benar
        port.setBaudRate(9600); // Sesuaikan baud rate jika perlu
        port.openPort();

        if (port.isOpen()) {
            System.out.println("Terhubung ke NFC via Bluetooth...");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(port.getInputStream()));
                String rfidTag;

                while ((rfidTag = reader.readLine()) != null) {
                    System.out.println("UID NFC diterima: " + rfidTag);
                    loginWithRFID(rfidTag); // Panggil fungsi login
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                port.closePort();
            }
        } else {
            System.out.println("Gagal membuka port!");
        }
    }

    private static void loginWithRFID(String rfidTag) {
        // Proses login seperti yang Anda buat sebelumnya
        System.out.println("Login dengan UID: " + rfidTag);
    }
}
