import java.util.ArrayList;
import java.util.Scanner;

public class Kereta extends Kendaraan {

    private ArrayList<String> rute = new ArrayList<>();
    private ArrayList<Double> harga = new ArrayList<>();

    private String kelas;
    private int kapasitas = 80;

    private String jamBerangkat;
    private String jamTiba;

    private String kursiDipilih;
    private int jumlahTiket;

    private int indexRute;
    private double hargaKelas;

    private String[][] kursi = new String[20][4];

    public Kereta() {

        super("KRT001", "Argo Bromo", "Kereta");

        rute.add("Jakarta - Bandung");
        harga.add(150000.0);

        rute.add("Jakarta - Yogyakarta");
        harga.add(300000.0);

        rute.add("Jakarta - Surabaya");
        harga.add(450000.0);

        isiKursi();
    }

    private void isiKursi() {

        char huruf = 'A';

        for (int i = 0; i < 20; i++) {

            for (int j = 0; j < 4; j++) {
                kursi[i][j] = huruf + "" + (j + 1);
            }

            huruf++;
        }
    }

    public void pilihRute(int pilihan) {

        indexRute = pilihan;

        switch (pilihan) {

            case 0:
                jamBerangkat = "06:00";
                jamTiba = "09:00";
                break;

            case 1:
                jamBerangkat = "07:00";
                jamTiba = "14:00";
                break;

            case 2:
                jamBerangkat = "08:00";
                jamTiba = "18:00";
                break;
        }
    }

    public void pilihKelas(String kelas) {

        this.kelas = kelas;

        if (kelas.equalsIgnoreCase("Ekonomi")) {
            hargaKelas = 0;
        } else {
            hargaKelas = 150000;
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