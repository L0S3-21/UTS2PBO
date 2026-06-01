public class Mobil extends Kendaraan {

    private String namaSupir;
    private String platNomor;

    private double hargaPerJam = 50000;
    private double hargaPerHari = 450000;
    private double biayaSupir = 150000;

    private int lamaSewa;
    private String tipeSewa;

    public Mobil() {

        super("MBL001", "Toyota Innova", "Rental Mobil");

        namaSupir = "Budi Santoso";
        platNomor = "B 1234 XYZ";
    }

    public void setSewa(int lamaSewa, String tipeSewa) {

        this.lamaSewa = lamaSewa;
        this.tipeSewa = tipeSewa;
    }

    @Override
    public double hitungHarga() {

        if (tipeSewa.equalsIgnoreCase("Jam")) {
            return (hargaPerJam * lamaSewa) + biayaSupir;
        }

        return (hargaPerHari * lamaSewa) + biayaSupir;
    }

    @Override
    public void pilihKursi() {
    }

    public String getNamaSupir() {
        return namaSupir;
    }

    public String getPlatNomor() {
        return platNomor;
    }

    @Override
    public void tampilInfo() {

        System.out.println("Jenis Kendaraan : " + jenisKendaraan);
        System.out.println("Nama Kendaraan  : " + namaKendaraan);
        System.out.println("Nama Supir      : " + namaSupir);
        System.out.println("Plat Nomor      : " + platNomor);
        System.out.println("Lama Sewa       : " + lamaSewa + " " + tipeSewa);
    }
}