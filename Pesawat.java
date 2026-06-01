import java.util.ArrayList;
import java.util.Scanner;

public class Pesawat extends Kendaraan {

    private ArrayList<String> rute = new ArrayList<>();
    private ArrayList<Double> harga = new ArrayList<>();

    private String kelas;
    private String kursiDipilih;

    private int jumlahTiket;
    private int kapasitas = 180;

    private String jamBerangkat;
    private String jamTiba;

    private int indexRute;
    private double hargaKelas;

    public Pesawat() {

        super("PST001", "Garuda Indonesia", "Pesawat");

        rute.add("Jakarta - Surabaya");
        harga.add(1200000.0);

        rute.add("Jakarta - Bali");
        harga.add(1500000.0);

        rute.add("Jakarta - Medan");
        harga.add(1700000.0);
    }

    public void pilihRute(int pilihan) {

        indexRute = pilihan;

        switch (pilihan) {

            case 0:
                jamBerangkat = "08:00";
                jamTiba = "09:20";
                break;

            case 1:
                jamBerangkat = "09:00";
                jamTiba = "10:45";
                break;

            case 2:
                jamBerangkat = "11:00";
                jamTiba = "13:15";
                break;
        }
    }

    public void pilihKelas(String kelas) {

        this.kelas = kelas;

        switch (kelas.toLowerCase()) {

            case "bisnis":
                hargaKelas = 500000;
                break;

            case "first class":
                hargaKelas = 1000000;
                break;

            default:
                hargaKelas = 0;
        }
    }

    public void setJumlahTiket(int jumlahTiket) {

        if (jumlahTiket > kapasitas) {
            System.out.println("Kapasitas melebihi batas");
            return;
        }

        this.jumlahTiket = jumlahTiket;
    }

    @Override
    public double hitungHarga() {
        return (harga.get(indexRute) + hargaKelas) * jumlahTiket;
    }

    @Override
    public void pilihKursi() {

        Scanner input = new Scanner(System.in);

        System.out.print("Pilih Kursi : ");
        kursiDipilih = input.nextLine();
        input.close();
    }

    @Override
    public void tampilInfo() {

        System.out.println("Jenis Kendaraan : " + jenisKendaraan);
        System.out.println("Nama Kendaraan  : " + namaKendaraan);
        System.out.println("Rute            : " + rute.get(indexRute));
        System.out.println("Kelas           : " + kelas);
        System.out.println("Kursi           : " + kursiDipilih);
        System.out.println("Jam Berangkat   : " + jamBerangkat);
        System.out.println("Jam Tiba        : " + jamTiba);
        System.out.println("Jumlah Tiket    : " + jumlahTiket);
    }
}