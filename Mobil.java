import java.util.Scanner;

public class Mobil extends Kendaraan {

    // ── Atribut tambahan ──────────────────────────────────────────────────────
    private int    lamaSewa;          // dalam jam
    private double hargaPerJam;
    private double upahSupirPerJam;
    private double uangTambahan;
    private String namaSupir;

    // ── Constructor ───────────────────────────────────────────────────────────
    public Mobil(String idKendaraan, String namaKendaraan,
                 String namaSupir,
                 double hargaPerJam, double upahSupirPerJam, double uangTambahan) {

        // Mobil : kapasitas 3 orang, tanpa rute tetap
        super(idKendaraan, "Mobil", namaKendaraan, 3, new String[]{"", ""});

        this.namaSupir       = namaSupir;
        this.hargaPerJam     = hargaPerJam;
        this.upahSupirPerJam = upahSupirPerJam;
        this.uangTambahan    = uangTambahan;
        this.lamaSewa        = 0;

        // Mobil tidak pakai array tempat duduk (kursi tidak dipilih)
        this.tempatDuduk = null;
    }

    // ── Getter ────────────────────────────────────────────────────────────────
    public int    getLamaSewa()        { return lamaSewa; }
    public double getHargaPerJam()     { return hargaPerJam; }
    public double getUpahSupirPerJam() { return upahSupirPerJam; }
    public double getUangTambahan()    { return uangTambahan; }
    public String getNamaSupir()       { return namaSupir; }

    // ── Input lama sewa ───────────────────────────────────────────────────────
    public void inputLamaSewa(Scanner scanner) {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════╗");
        System.out.println("  ║           LAMA SEWA MOBIL            ║");
        System.out.printf ("  ║  Harga per jam    : Rp %,10.0f   ║%n", hargaPerJam);
        System.out.printf ("  ║  Upah supir/jam   : Rp %,10.0f   ║%n", upahSupirPerJam);
        System.out.printf ("  ║  Uang tambahan    : Rp %,10.0f   ║%n", uangTambahan);
        System.out.println("  ╚══════════════════════════════════════╝");
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

    // ── Hitung harga ──────────────────────────────────────────────────────────
    // Total = (hargaPerJam + upahSupirPerJam) × lamaSewa + uangTambahan
    @Override
    public double hitungHarga(int jumlahTiket) {
        return (hargaPerJam + upahSupirPerJam) * lamaSewa + uangTambahan;
    }

    // ── Mobil tidak menggunakan pilihKursi ────────────────────────────────────
    @Override
    public String pilihKursi(Scanner scanner) {
        // Tidak ada pemilihan kursi untuk mobil sewaan
        return "-";
    }

    // ── tampilInfo ────────────────────────────────────────────────────────────
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