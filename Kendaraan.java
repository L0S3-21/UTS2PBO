import java.util.Scanner;

public abstract class Kendaraan implements TampilInfo {

    protected String idKendaraan;
    protected String jenisKendaraan;
    protected String namaKendaraan;
    protected int kapasitas;
    protected String[] rute;
    protected char[][] tempatDuduk;

    public Kendaraan(String idKendaraan, String jenisKendaraan, String namaKendaraan, int kapasitas, String[] rute) {
        this.idKendaraan    = idKendaraan;
        this.jenisKendaraan = jenisKendaraan;
        this.namaKendaraan  = namaKendaraan;
        this.kapasitas      = kapasitas;
        this.rute           = rute;
    }

    // ── Getter ────────────────────────────────────────────────────────────────
    public String getIdKendaraan()    { return idKendaraan; }
    public String getJenisKendaraan() { return jenisKendaraan; }
    public String getNamaKendaraan()  { return namaKendaraan; }
    public int    getKapasitas()      { return kapasitas; }
    public String[] getRute()         { return rute; }
    public char[][] getTempatDuduk()  { return tempatDuduk; }

    // ── Abstract methods ──────────────────────────────────────────────────────
    public abstract double hitungHarga(int jumlahTiket);
    public abstract String pilihKursi(Scanner scanner);

    @Override
    public abstract void tampilInfo();
}