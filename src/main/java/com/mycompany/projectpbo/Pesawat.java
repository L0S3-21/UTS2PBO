package com.mycompany.projectpbo;
import java.util.Scanner;

public class Pesawat extends Kendaraan {

    private String   jamBerangkat;
    private String   jamTiba;
    private String   kelasTiket;     
    private double   hargaPerKelas;   
    private double   hargaDasar;


    private static final double HARGA_EKONOMI     =       0;
    private static final double HARGA_BISNIS      = 1_500_000;
    private static final double HARGA_FIRST_CLASS = 3_500_000;

    public Pesawat(String idKendaraan, String namaKendaraan,
                   String[] rute,
                   String jamBerangkat, String jamTiba,
                   double hargaDasar) {

        super(idKendaraan, "Pesawat", namaKendaraan, 60, rute);

        this.jamBerangkat  = jamBerangkat;
        this.jamTiba       = jamTiba;
        this.hargaDasar    = hargaDasar;
        this.kelasTiket    = "Ekonomi";
        this.hargaPerKelas = HARGA_EKONOMI;
        this.tempatDuduk   = null;
    }

    public String getJamBerangkat()  { return jamBerangkat; }
    public String getJamTiba()       { return jamTiba; }
    public String getKelasTiket()    { return kelasTiket; }
    public double getHargaDasar()    { return hargaDasar; }
    public double getHargaPerKelas() { return hargaPerKelas; }

    public void pilihKelas(Scanner scanner) {
        System.out.println();

        System.out.println("          PILIHAN KELAS TIKET           ");
        System.out.printf ("    1. Ekonomi      (+Rp %,12.0f)  %n", HARGA_EKONOMI);
        System.out.printf ("    2. Bisnis       (+Rp %,12.0f)  %n", HARGA_BISNIS);
        System.out.printf ("    3. First Class  (+Rp %,12.0f)  %n", HARGA_FIRST_CLASS);
        System.out.print  ("  Pilih kelas (1-3) : ");

        int pilihan = 0;
        while (true) {
            try {
                pilihan = Integer.parseInt(scanner.nextLine().trim());
                if (pilihan >= 1 && pilihan <= 3) break;
                System.out.print("  Pilihan tidak valid. Masukkan 1-3 : ");
            } catch (NumberFormatException e) {
                System.out.print("  Input tidak valid. Masukkan angka 1-3 : ");
            }
        }

        switch (pilihan) {
            case 1 -> { kelasTiket = "Ekonomi";     hargaPerKelas = HARGA_EKONOMI; }
            case 2 -> { kelasTiket = "Bisnis";       hargaPerKelas = HARGA_BISNIS; }
            case 3 -> { kelasTiket = "First Class";  hargaPerKelas = HARGA_FIRST_CLASS; }
        }
        System.out.println("  Kelas dipilih : " + kelasTiket);
    }

    @Override
    public double hitungHarga(int jumlahTiket) {
        return (hargaDasar + hargaPerKelas) * jumlahTiket;
    }

    @Override
    public String pilihKursi(Scanner scanner) {

        System.out.println();
        System.out.println("            DENAH TEMPAT DUDUK          ");
        System.out.println("                [ PESAWAT ]             ");
        System.out.println();
        char[] sisiKiri  = {'A', 'B', 'C'};
        char[] sisiKanan = {'D', 'E', 'F'};

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
                System.out.println("  Format salah");
                continue;
            }

            char kolom = kursiDipilih.charAt(0);
            int  baris;
            try {
                baris = Integer.parseInt(kursiDipilih.substring(1));
            } catch (NumberFormatException e) {
                System.out.println("  Format salah.");
                continue;
            }

            if ("ABCDEF".indexOf(kolom) == -1) {
                System.out.println("  Kolom tidak valid");
                continue;
            }

            if (baris < 1 || baris > 10) {
                System.out.println("  Baris tidak valid. Pilih antara 1-10.");
                continue;
            }

            System.out.println("  Kursi " + kursiDipilih + " berhasil dipilih.");
            break;
        }
        return kursiDipilih;
    }

    @Override
    public void tampilInfo() {
        System.out.println("  Jenis Kendaraan  : " + jenisKendaraan);
        System.out.println("  ID Kendaraan     : " + idKendaraan);
        System.out.println("  Nama Maskapai    : " + namaKendaraan);
        System.out.println("  Rute             : " + rute[0] + " ke " + rute[1]);
        System.out.println("  Jam Berangkat    : " + jamBerangkat);
        System.out.println("  Jam Tiba         : " + jamTiba);
        System.out.println("  Kelas Tiket      : " + kelasTiket);
    }
}