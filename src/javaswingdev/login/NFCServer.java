/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaswingdev.login;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.io.*;
import java.net.*;

/**
 *
 * @author ACER
 */
public class NFCServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server NFC berjalan di port 8080...");

            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String rfidTag = input.readLine();
                System.out.println("UID NFC diterima: " + rfidTag);

                // Panggil fungsi loginWithRFID()
                loginWithRFID(rfidTag);

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loginWithRFID(String rfidTag) {
        // Proses login dengan RFID seperti yang sudah Anda buat
        System.out.println("Login dengan UID: " + rfidTag);
    }
}
