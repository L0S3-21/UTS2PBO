import java.util.Scanner;

public class Bus extends Kendaraan {

    // ── Atribut tambahan ──────────────────────────────────────────────────────
    private double hargaDasar;

    // Layout bus :
    //   Baris 1-6  : format 2-2  → 4 kursi per baris = 24 kursi
    //   Baris 7    : format 2-2  → 4 kursi
    //   Baris 8    : kursi belakang 5 = 1 baris 5 kursi penuh (kolom 0-4, tanpa lorong)
    //   Total : (7 × 4) + 3 = 31 kursi
    // Array dimensi : 8 baris × 6 kolom
    //   Kolom 0-1  = sisi kiri (A B)
    //   Kolom 2    = lorong (kecuali baris 8)
    //   Kolom 3-4  = sisi kanan (C D)
    //   Kolom 5    = kolom extra hanya untuk baris ke-8 (kursi E)

    // ── Constructor ───────────────────────────────────────────────────────────
    public Bus(String idKendaraan, String namaKendaraan,
               String[] rute, double hargaDasar) {

        super(idKendaraan, "Bus", namaKendaraan, 31, rute);
        this.hargaDasar = hargaDasar;
        this.tempatDuduk = null;
    }

    // ── Getter ────────────────────────────────────────────────────────────────
    public double getHargaDasar() { return hargaDasar; }

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
        System.out.println("  ║               [ BUS ]                ║");
        System.out.println("  ╚══════════════════════════════════════╝");
        System.out.println("  Keterangan : [ ] = Tersedia  [lorong] = Jalan");
        System.out.println();

        // Baris 1-7 : A B [lorong] C D
        // Baris 8   : A B C D E (kursi belakang, tanpa lorong)
        char[] sisiKiri  = {'A', 'B'};
        char[] sisiKanan = {'C', 'D'};

        System.out.println("  Denah Tempat Duduk :");
        System.out.println();
        for (int i = 1; i <= 7; i++) {
            System.out.print("  ");
            for (char k : sisiKiri)  System.out.printf("%-4s", k + "" + i);
            System.out.print("     ");   // lorong
            for (char k : sisiKanan) System.out.printf("%-4s", k + "" + i);
            System.out.println();
        }
        // Baris 8 : kursi belakang A-E tanpa lorong
        System.out.print("  ");
        for (char k : new char[]{'A','B','C','D','E'}) System.out.printf("%-4s", k + "8");
        System.out.println();
        System.out.println();
        System.out.println("  * Baris 8 : 5 kursi belakang (A-E)");
        System.out.println();

        // Input kursi
        String kursiDipilih = "";
        while (true) {
            System.out.print("  Masukkan posisi kursi (contoh: A3, C7, A8) : ");
            kursiDipilih = scanner.nextLine().trim().toUpperCase();

            if (kursiDipilih.length() < 2) {
                System.out.println("  Format salah. Gunakan format seperti A3 atau C7.");
                continue;
            }

            char kolom = kursiDipilih.charAt(0);
            int  baris;
            try {
                baris = Integer.parseInt(kursiDipilih.substring(1));
            } catch (NumberFormatException e) {
                System.out.println("  Format salah. Gunakan format seperti A3 atau C7.");
                continue;
            }

            if (baris < 1 || baris > 8) {
                System.out.println("  Baris tidak valid. Pilih antara 1-8.");
                continue;
            }

            if (baris < 8) {
                if ("ABCD".indexOf(kolom) == -1) {
                    System.out.println("  Kolom tidak valid untuk baris 1-7. Pilih A-D.");
                    continue;
                }
            } else {
                if ("ABCDE".indexOf(kolom) == -1) {
                    System.out.println("  Kolom tidak valid untuk baris 8. Pilih A-E.");
                    continue;
                }
            }

            System.out.println("  Kursi " + kursiDipilih + " berhasil dipilih.");
            break;
        }
        return kursiDipilih;
    }

    // ── tampilInfo ────────────────────────────────────────────────────────────
    @Override
    public void tampilInfo() {
        System.out.println("  Jenis Kendaraan  : " + jenisKendaraan);
        System.out.println("  ID Kendaraan     : " + idKendaraan);
        System.out.println("  Nama Bus         : " + namaKendaraan);
        System.out.println("  Rute             : " + rute[0] + " → " + rute[1]);
        System.out.println("  Kapasitas        : " + kapasitas + " penumpang");
    }
}