package com.mycompany.projectpbo;
import java.util.Scanner;

public class Mobil extends Kendaraan {

    private int    lamaSewa;          
    private double hargaPerJam;
    private double upahSupirPerJam;
    private double uangTambahan;
    private String namaSupir;

    public Mobil(String idKendaraan, String namaKendaraan,
                 String namaSupir,
                 double hargaPerJam, double upahSupirPerJam, double uangTambahan) {

        super(idKendaraan, "Mobil", namaKendaraan, 3, new String[]{"", ""});

        this.namaSupir       = namaSupir;
        this.hargaPerJam     = hargaPerJam;
        this.upahSupirPerJam = upahSupirPerJam;
        this.uangTambahan    = uangTambahan;
        this.lamaSewa        = 0;
        this.tempatDuduk = null;
    }

    public int    getLamaSewa()        { return lamaSewa; }
    public double getHargaPerJam()     { return hargaPerJam; }
    public double getUpahSupirPerJam() { return upahSupirPerJam; }
    public double getUangTambahan()    { return uangTambahan; }
    public String getNamaSupir()       { return namaSupir; }

    public void inputLamaSewa(Scanner scanner) {
        System.out.println();
        System.out.println("  ==================================");
        System.out.println("             LAMA SEWA MOBIL            ");
        System.out.printf ("    Harga per jam    : Rp %,10.0f   %n", hargaPerJam);
        System.out.printf ("    Upah supir/jam   : Rp %,10.0f   %n", upahSupirPerJam);
        System.out.printf ("    Uang tambahan    : Rp %,10.0f   %n", uangTambahan);
        System.out.println("  ===================================");
        System.out.print  ("  Masukkan lama sewa (jam) : ");

        while (true) {
            try {
                lamaSewa = Integer.parseInt(scanner.nextLine().trim());
                if (lamaSewa > 0) break;
                System.out.print("  Lama sewa harus lebih dari 0 jam : ");
            } catch (NumberFormatException e) {
                System.out.print("  Input tidak valid. Masukkan angka : ");
            }
        }
        System.out.printf("  Lama sewa : %d jam%n", lamaSewa);
    }

    @Override
    public double hitungHarga(int jumlahTiket) {
        return (hargaPerJam + upahSupirPerJam) * lamaSewa + uangTambahan;
    }

    @Override
    public String pilihKursi(Scanner scanner) {
        return "-";
    }

    @Override
    public void tampilInfo() {
        System.out.println("  Jenis Kendaraan  : " + jenisKendaraan);
        System.out.println("  ID Kendaraan     : " + idKendaraan);
        System.out.println("  Nama Kendaraan   : " + namaKendaraan);
        System.out.println("  Kapasitas        : " + kapasitas + " penumpang");
        System.out.println("  Lama Sewa        : " + lamaSewa + " jam");
        System.out.println("  Nama Supir       : " + namaSupir);
    }
}