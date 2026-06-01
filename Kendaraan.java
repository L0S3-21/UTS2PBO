public abstract class Kendaraan {

    protected String idKendaraan;
    protected String namaKendaraan;
    protected String jenisKendaraan;

    public Kendaraan(String idKendaraan, String namaKendaraan, String jenisKendaraan) {
        this.idKendaraan = idKendaraan;
        this.namaKendaraan = namaKendaraan;
        this.jenisKendaraan = jenisKendaraan;
    }

    public String getIdKendaraan() {
        return idKendaraan;
    }

    public String getNamaKendaraan() {
        return namaKendaraan;
    }

    public String getJenisKendaraan() {
        return jenisKendaraan;
    }

    public abstract double hitungHarga();
    public abstract void pilihKursi();
    public abstract void tampilInfo();
}