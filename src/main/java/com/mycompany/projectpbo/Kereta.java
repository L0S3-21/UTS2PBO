package com.mycompany.projectpbo;
import java.util.Scanner;

public class Kereta extends Kendaraan {

    private int    gerbong;       
    private double hargaDasar;

    private static final int    kursi_per_gerbong = 40;   
    private static final int    banyak_gerbong     = 4;

    public Kereta(String idKendaraan, String namaKendaraan,
                  String[] rute, double hargaDasar) {

       
        super(idKendaraan, "Kereta", namaKendaraan, kursi_per_gerbong, rute);

        this.hargaDasar = hargaDasar;
        this.gerbong    = 1;
        this.tempatDuduk = null;
    }

    public int    getGerbong()    { return gerbong; }
    public double getHargaDasar() { return hargaDasar; }

    public void pilihGerbong(Scanner scanner) {
        System.out.println();
        System.out.println("  =================================");
        System.out.println("           PILIHAN GERBONG            ");
        System.out.println("  =================================");
        for (int g = 1; g <= getGerbong(); g++) {
            System.out.printf("     %d. Gerbong %d  (40 kursi)            %n", g, g);
        }
        System.out.println(" ==================================");
        System.out.print  ("  Pilih gerbong (1-4) : ");

        while (true) {
            try {
                int pilihan = Integer.parseInt(scanner.nextLine().trim());
                if (pilihan >= 1 && pilihan <= banyak_gerbong) {
                    gerbong = pilihan;
                    System.out.println("  Gerbong " + gerbong + " dipilih.");
                    break;
                }
                System.out.print("  Pilihan tidak valid. Masukkan 1-4 : ");
            } catch (NumberFormatException e) {
                System.out.print("  Input tidak valid. Masukkan angka 1-4 : ");
            }
        }
    }

    @Override
    public double hitungHarga(int jumlahTiket) {
        return hargaDasar * jumlahTiket;
    }

    @Override
    public String pilihKursi(Scanner scanner) {
        System.out.println();
        System.out.println("  ==================================");
        System.out.println("          DENAH TEMPAT DUDUK          ");
        System.out.printf ("       [ KERETA - GERBONG %d ]      %n", gerbong);
        System.out.println("  ==================================");
        System.out.println();

        char[] sisiKiri  = {'A', 'B'};
        char[] sisiKanan = {'C', 'D'};

        System.out.println("  Denah Tempat Duduk :");
        System.out.println();
        for (int i = 1; i <= 10; i++) {
            System.out.print("  ");
            for (char k : sisiKiri)  System.out.printf("%-4s", k + "" + i);
            System.out.print("     ");   // lorong
            for (char k : sisiKanan) System.out.printf("%-4s", k + "" + i);
            System.out.println();
        }
        System.out.println();

        // Input kursi
        String kursiDipilih = "";
        while (true) {
            System.out.print("  Masukkan posisi kursi (contoh: A3, D10) : ");
            kursiDipilih = scanner.nextLine().trim().toUpperCase();

            if (kursiDipilih.length() < 2) {
                System.out.println("  Format salah. Gunakan format seperti A3 atau D10.");
                continue;
            }

            char kolom = kursiDipilih.charAt(0);
            int  baris;
            try {
                baris = Integer.parseInt(kursiDipilih.substring(1));
            } catch (NumberFormatException e) {
                System.out.println("  Format salah. Gunakan format seperti A3 atau D10.");
                continue;
            }

            if ("ABCD".indexOf(kolom) == -1) {
                System.out.println("  Kolom tidak valid. Pilih antara A-D.");
                continue;
            }

            if (baris < 1 || baris > 10) {
                System.out.println("  Baris tidak valid. Pilih antara 1-10.");
                continue;
            }

            System.out.println("  Kursi " + kursiDipilih + " (Gerbong " + gerbong + ") berhasil dipilih.");
            break;
        }
        return kursiDipilih + " (Gerbong " + gerbong + ")";
    }

    @Override
    public void tampilInfo() {
        System.out.println("  Jenis Kendaraan  : " + jenisKendaraan);
        System.out.println("  ID Kendaraan     : " + idKendaraan);
        System.out.println("  Nama Kereta      : " + namaKendaraan);
        System.out.println("  Rute             : " + rute[0] + " ke " + rute[1]);
        System.out.println("  Gerbong          : " + gerbong);
    }
}