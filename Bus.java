import java.util.ArrayList;
import java.util.Scanner;

public class Bus extends Kendaraan {

    private ArrayList<String> rute = new ArrayList<>();
    private ArrayList<Double> harga = new ArrayList<>();

    private String kelas;
    private int kapasitas = 40;
    private String jamBerangkat;
    private String jamTiba;
    private String kursiDipilih;
    private int jumlahTiket;

    private String[][] kursi = new String[10][4];

    public Bus() {
        super("BUS001", "Sinar Jaya", "Bus");

        rute.add("Jakarta - Bandung");
        harga.add(120000.0);

        rute.add("Jakarta - Yogyakarta");
        harga.add(250000.0);

        rute.add("Jakarta - Surabaya");
        harga.add(350000.0);

        isiKursi();
    }

    private int indexRute;
    private double hargaKelas;

    private void isiKursi() {
        char baris = 'A';

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                kursi[i][j] = baris + "" + (j + 1);
            }
            baris++;
        }
    }

    public void pilihRute(int pilihan) {

        indexRute = pilihan;

        switch (pilihan) {
            case 0:
                jamBerangkat = "07:00";
                jamTiba = "11:00";
                break;

            case 1:
                jamBerangkat = "08:00";
                jamTiba = "18:00";
                break;

            case 2:
                jamBerangkat = "09:00";
                jamTiba = "22:00";
                break;
        }
    }

    public void pilihKelas(String kelas) {

        this.kelas = kelas;

        if (kelas.equalsIgnoreCase("Ekonomi")) {
            hargaKelas = 0;
        } else {
            hargaKelas = 75000;
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

        for (String[] row : kursi) {
            for (String seat : row) {
                System.out.print(seat + " ");
            }
            System.out.println();
        }

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