import java.util.Scanner;

public class Kereta extends Kendaraan {

    // ── Atribut tambahan ──────────────────────────────────────────────────────
    private int    gerbong;       // gerbong yang dipilih (1-4)
    private double hargaDasar;

    private static final int    KURSI_PER_GERBONG = 40;   // 10 baris × 4 kolom
    private static final int    TOTAL_GERBONG     = 4;

    // ── Constructor ───────────────────────────────────────────────────────────
    public Kereta(String idKendaraan, String namaKendaraan,
                  String[] rute, double hargaDasar) {

        // Kapasitas = 1 gerbong = 40 kursi (user hanya memilih 1 gerbong)
        super(idKendaraan, "Kereta", namaKendaraan, KURSI_PER_GERBONG, rute);

        this.hargaDasar = hargaDasar;
        this.gerbong    = 1;
        this.tempatDuduk = null;
    }

    // ── Getter tambahan ───────────────────────────────────────────────────────
    public int    getGerbong()    { return gerbong; }
    public double getHargaDasar() { return hargaDasar; }

    // ── Pilih gerbong ─────────────────────────────────────────────────────────
    public void pilihGerbong(Scanner scanner) {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════╗");
        System.out.println("  ║          PILIHAN GERBONG             ║");
        System.out.println("  ╠══════════════════════════════════════╣");
        for (int g = 1; g <= TOTAL_GERBONG; g++) {
            System.out.printf("  ║  %d. Gerbong %d  (40 kursi)            ║%n", g, g);
        }
        System.out.println("  ╚══════════════════════════════════════╝");
        System.out.print  ("  Pilih gerbong (1-4) : ");

        while (true) {
            try {
                int pilihan = Integer.parseInt(scanner.nextLine().trim());
                if (pilihan >= 1 && pilihan <= TOTAL_GERBONG) {
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

    // ── Hitung harga ──────────────────────────────────────────────────────────
    @Override
    public double hitungHarga(int jumlahTiket) {
        return hargaDasar * jumlahTiket;
    }

    // ── Tampil & pilih kursi ──────────────────────────────────────────────────
    @Override
    public String pilihKursi(Scanner scanner) {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════╗");
        System.out.println("  ║          DENAH TEMPAT DUDUK          ║");
        System.out.printf ("  ║          [ KERETA - GERBONG %d ]      ║%n", gerbong);
        System.out.println("  ╚══════════════════════════════════════╝");
        System.out.println("  Keterangan : [ ] = Tersedia  [lorong] = Jalan");
        System.out.println();

        // Denah : A1 B1 [lorong] C1 D1
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

    // ── tampilInfo ────────────────────────────────────────────────────────────
    @Override
    public void tampilInfo() {
        System.out.println("  Jenis Kendaraan  : " + jenisKendaraan);
        System.out.println("  ID Kendaraan     : " + idKendaraan);
        System.out.println("  Nama Kereta      : " + namaKendaraan);
        System.out.println("  Rute             : " + rute[0] + " → " + rute[1]);
        System.out.println("  Gerbong          : " + gerbong);
    }
}