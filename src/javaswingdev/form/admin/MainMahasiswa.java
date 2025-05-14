/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaswingdev.form.admin;

/**
 *
 * @author ACER
 */
// Importing Library
import java.util.Scanner;

// Class dengan Enkapsulasi
class Mahasiswa {

    // Atribut private (enkapsulasi)
    private String nama;
    private String nim;
    private double ipk;

    // Constructor
    public Mahasiswa(String nama, String nim, double ipk) {
        this.nama = nama;
        this.nim = nim;
        this.ipk = ipk;
    }

    // Setter dan Getter (akses terkontrol)
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }

    public void setIpk(double ipk) {
        if (ipk >= 0.0 && ipk <= 4.0) {
            this.ipk = ipk;
        } else {
            System.out.println("Nilai IPK tidak valid!");
        }
    }

    public double getIpk() {
        return ipk;
    }

    public void tampilkanInfo() {
        System.out.println("Nama: " + getNama());
        System.out.println("NIM : " + getNim());
        System.out.println("IPK : " + getIpk());
    }
}

// Main class
public class MainMahasiswa {

    public static void main(String[] args) {
        // Menggunakan library Scanner
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan nama: ");
        String nama = input.nextLine();

        System.out.print("Masukkan NIM: ");
        String nim = input.nextLine();

        System.out.print("Masukkan IPK: ");
        double ipk = input.nextDouble();

        // Membuat objek Mahasiswa dengan enkapsulasi
        Mahasiswa mhs = new Mahasiswa(nama, nim, ipk);

        System.out.println("\nData Mahasiswa:");
        mhs.tampilkanInfo();

        input.close();
    }
}
