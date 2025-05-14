/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaswingdev.form.admin;

/**
 *
 * @author ACER
 */
// Superclass Keluarga
class Keluarga {
    String nama;

    public Keluarga(String nama) {
        this.nama = nama;
    }

    public void perkenalan() {
        System.out.println("Saya anggota keluarga bernama " + nama);
    }
}

// Subclass KakekNenek
class KakekNenek extends Keluarga {
    public KakekNenek(String nama) {
        super(nama);
    }

    @Override
    public void perkenalan() {
        System.out.println("Saya Kakek/Nenek bernama " + nama);
    }
}

// Subclass AyahIbu
class AyahIbu extends Keluarga {
    public AyahIbu(String nama) {
        super(nama);
    }

    @Override
    public void perkenalan() {
        System.out.println("Saya Ayah/Ibu bernama " + nama);
    }
}

// Subclass Anak
class Anak extends Keluarga {
    public Anak(String nama) {
        super(nama);
    }

    @Override
    public void perkenalan() {
        System.out.println("Saya Anak-Anak bernama " + nama);
    }
}

// Kelas abstrak Warisan
abstract class Warisan {
    String namaWarisan;

    public Warisan(String namaWarisan) {
        this.namaWarisan = namaWarisan;
    }

    public abstract void bagiWarisan(Keluarga penerima);
}

// Warisan untuk Anak Saja
class WarisanUntukAnak extends Warisan {
    public WarisanUntukAnak(String namaWarisan) {
        super(namaWarisan);
    }

    @Override
    public void bagiWarisan(Keluarga penerima) {
        if (penerima instanceof Anak) {
            System.out.println("Warisan '" + namaWarisan + "' diberikan kepada " + penerima.nama);
        } else {
            System.out.println(penerima.nama + " tidak berhak menerima warisan '" + namaWarisan + "'");
        }
    }
}

// Warisan untuk Anak dan Cucu
class WarisanUntukAnakCucu extends Warisan {
    public WarisanUntukAnakCucu(String namaWarisan) {
        super(namaWarisan);
    }

    @Override
    public void bagiWarisan(Keluarga penerima) {
        if (penerima instanceof Anak || penerima instanceof AyahIbu) {
            System.out.println("Warisan '" + namaWarisan + "' diberikan kepada " + penerima.nama);
        } else {
            System.out.println(penerima.nama + " tidak berhak menerima warisan '" + namaWarisan + "'");
        }
    }
}

public class HappyFamily {
    public static void main(String[] args) {
        // Membuat anggota keluarga
        KakekNenek kakek = new KakekNenek("Leon Abbad Bari Giovano");
        AyahIbu ayah = new AyahIbu("Muhammad Yassir Thoriq  Fathirurrizqy");
        Anak anak = new Anak("Abdillah Aziz Putra Susan");

        // Perkenalan anggota keluarga
        System.out.println("=== Perkenalan Keluarga ===");
        kakek.perkenalan();
        ayah.perkenalan();
        anak.perkenalan();

        // Membuat objek warisan
        Warisan sepedaMotor = new WarisanUntukAnak("Sepeda Motor");
        Warisan rumah = new WarisanUntukAnakCucu("Rumah");

        System.out.println("\n=== Pembagian Warisan ===");

        // Polimorfisme: Bagi warisan
        sepedaMotor.bagiWarisan(kakek);    // Tidak berhak
        sepedaMotor.bagiWarisan(ayah);     // Tidak berhak
        sepedaMotor.bagiWarisan(anak);     // Berhak

        rumah.bagiWarisan(kakek);           // Tidak berhak
        rumah.bagiWarisan(ayah);            // Berhak
        rumah.bagiWarisan(anak);            // Berhak
    }
}
