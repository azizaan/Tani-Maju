/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaswingdev.login;

/**
 *
 * @author ACER
 */
import javax.swing.*;

public class NFCKeyboard {
    public static void main(String[] args) {
        // Buat frame (window)
        JFrame frame = new JFrame("NFC Reader");
        JTextField textField = new JTextField(20);
        
        // Atur tampilan
        frame.setLayout(new java.awt.FlowLayout());
        frame.add(new JLabel("Tempelkan kartu NFC di HP:"));
        frame.add(textField);
        
        frame.setSize(350, 120);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        // Fokus otomatis ke text field untuk menerima input
        textField.requestFocus(); 

        JOptionPane.showMessageDialog(frame, "Scan kartu NFC di HP. UID akan muncul di sini.");
        System.out.println("Aplikasi siap menerima input NFC...");
    }
}
