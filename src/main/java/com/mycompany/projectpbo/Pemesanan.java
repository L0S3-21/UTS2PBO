package com.mycompany.projectpbo;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Pemesanan {

    private Scanner    scanner;
    private Penumpang  penumpang;
    private Kendaraan  kendaraan;
    private Pembayaran pembayaran;

    private String tanggalKeberangkatan;
    private String ruteYangDipilih;
    private String kursiDipilih;
    private int    jumlahTiket;
    private double totalHarga;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final String[] TANGGAL_LIBUR = {
        "25/12/2026", "31/12/2026", "01/01/2027"
    };

    public Pemesanan() {
        this.scanner = new Scanner(System.in);
    }

    public void jalankan() {
        boolean lanjut = true;
        while (lanjut) {
            resetState();

            // 1. Menu utama
            tampilMenuUtama();
            int pilihanKendaraan = inputMenuUtama();
            buatKendaraan(pilihanKendaraan);

            // 2. Input data diri
            inputDataDiri();

            // 3. Tanggal keberangkatan
            inputTanggalKeberangkatan();

            
            if (kendaraan instanceof Mobil) {
                Mobil mobil = (Mobil) kendaraan;
                mobil.inputLamaSewa(scanner);
                inputJumlahPenumpangMobil();

            
            } else {
                // Pilih rute & jumlah tiket
                boolean valid = pilihRuteDanJumlahTiket();
                if (!valid) {
                    tekanEnterLanjut();
                    continue;
                }

                // Pilih kelas (Pesawat) atau gerbong (Kereta)
                if (kendaraan instanceof Pesawat) {
                    ((Pesawat) kendaraan).pilihKelas(scanner);
                } else if (kendaraan instanceof Kereta) {
                    ((Kereta) kendaraan).pilihGerbong(scanner);
                }

                pilihKursiSemua();
            }

            totalHarga = kendaraan.hitungHarga(jumlahTiket);

            prosesPembayaran();

            tampilDetailPemesanan();

            lanjut = tanyaKembaliMenu();
        }

        System.out.println();
        System.out.println("  Terima kasih telah menggunakan Travelis!");
        System.out.println("  Selamat bepergian!");
        System.out.println();
        scanner.close();
    }

    private void resetState() {
        penumpang            = null;
        kendaraan            = null;
        pembayaran           = null;
        tanggalKeberangkatan = "";
        ruteYangDipilih      = "";
        kursiDipilih         = "";
        jumlahTiket          = 0;
        totalHarga           = 0.0;
    }

    private void tampilMenuUtama() {
        System.out.println();
        System.out.println("  =======================================");
        System.out.println("         SELAMAT DATANG DI TRAVELIS      ");
        System.out.println("  =======================================");
        System.out.println(" Silahkan memilih tiket yang anda inginkan  ");
        System.out.println("  =======================================");
        System.out.println("                                                ");
        System.out.println("     1.  Pesawat                                ");
        System.out.println("     2.  Kereta                                 ");
        System.out.println("     3.  Bus                                    ");
        System.out.println("     4.  Mobil                                  ");
        System.out.println("  =======================================");
    }

    private int inputMenuUtama() {
        while (true) {
            System.out.print("  Masukkan pilihan (1-4) : ");
            try {
                int p = Integer.parseInt(scanner.nextLine().trim());
                if (p >= 1 && p <= 4) return p;
                System.out.println("  Pilihan tidak valid. Masukkan angka 1-4.");
            } catch (NumberFormatException e) {
                System.out.println("  Input tidak valid. Masukkan angka 1-4.");
            }
        }
    }

    private void buatKendaraan(int pilihan) {
        switch (pilihan) {
            case 1:
                kendaraan = new Pesawat(
                    "PA-GAA",
                    "Garuda Airlines",
                    new String[]{
                        "Bandara Soekarno-Hatta",
                        "Yogyakarta International Airport"
                    },
                    "08:00", "09:15",
                    1_200_000
                );
                break;
            case 2:
                kendaraan = new Kereta(
                    "CC 206 13 100",
                    "Argo Semeru",
                    new String[]{"Stasiun Pasar Senen", "Stasiun Lempuyangan"},
                    250_000
                );
                break;
            case 3:
                kendaraan = new Bus(
                    "BUS-001",
                    "Sinar Jaya",
                    new String[]{"Bogor", "Jogja"},
                    150_000
                );
                break;
            case 4:
                kendaraan = new Mobil(
                    "MOB-001",
                    "Toyota Innova",
                    "Budi Santoso",
                    150_000,
                    75_000,
                    50_000
                );
                break;
        }
    }

    private void inputDataDiri() {
        System.out.println();
        System.out.println("  =======================================");
        System.out.println("             DATA DIRI PENUMPANG         ");
        System.out.println("  =======================================");

        System.out.print("  Nama lengkap   : ");
        String nama = scanner.nextLine().trim();

        System.out.print("  No. HP         : ");
        String noHp = scanner.nextLine().trim();

        System.out.print("  Email          : ");
        String email = scanner.nextLine().trim();

        System.out.print("  NIK            : ");
        String nik = scanner.nextLine().trim();

        String tanggalPemesanan = LocalDate.now().format(FMT);
        String idPenumpang      = generateId("PNP");

        penumpang = new Penumpang(idPenumpang, nama, noHp, email, nik, tanggalPemesanan);

        System.out.println();
        System.out.println("  Data diri berhasil disimpan.");
    }

    private void inputTanggalKeberangkatan() {
        while (true) {
            System.out.println();
            System.out.println("  =======================================");
            System.out.println("             TANGGAL KEBERANGKATAN                ");
            System.out.println("  =======================================");
            System.out.print("  Masukkan tanggal (dd/mm/yyyy) : ");

            String input = scanner.nextLine().trim();
            LocalDate tgl;

            try {
                tgl = LocalDate.parse(input, FMT);
            } catch (DateTimeParseException e) {
                System.out.println("  Format tanggal salah.");
                continue;
            }

            if (tgl.isBefore(LocalDate.now())) {
                System.out.println("  Tanggal tidak valid. Pilih tanggal yang akan datang.");
                continue;
            }

            String formatted = tgl.format(FMT);
            boolean libur = false;
            for (String lib : TANGGAL_LIBUR) {
                if (lib.equals(formatted)) { libur = true; break; }
            }

            if (libur) {
                System.out.println();
                System.out.println("  =======================================");
                System.out.println("             TIKET TIDAK TERSEDIA            ");
                System.out.println("    Tanggal " + formatted + " tidak tersedia.          ");
                System.out.println("    Silahkan pilih tanggal lain.                ");
                System.out.println("  =======================================");
                continue;
            }

            tanggalKeberangkatan = formatted;
            System.out.println("  Tanggal keberangkatan : " + tanggalKeberangkatan);
            break;
        }
    }

    private boolean pilihRuteDanJumlahTiket() {
        String[] rute = kendaraan.getRute();

        System.out.println();
        System.out.println("  =======================================");
        System.out.println("             RUTE PERJALANAN             ");
        System.out.println("  =======================================");
        System.out.println("  1.  " + rute[0] + " ke " + rute[1]);
        System.out.println("  2.  " + rute[1] + " ke " + rute[0]);
        System.out.println();

        int pilihanRute;
        while (true) {
            System.out.print("  Pilih rute (1/2) : ");
            try {
                pilihanRute = Integer.parseInt(scanner.nextLine().trim());
                if (pilihanRute == 1 || pilihanRute == 2) break;
                System.out.println("  Pilihan tidak valid. Masukkan 1 atau 2.");
            } catch (NumberFormatException e) {
                System.out.println("  Input tidak valid. Masukkan 1 atau 2.");
            }
        }

        ruteYangDipilih = (pilihanRute == 1)
            ? rute[0] + " ke " + rute[1]
            : rute[1] + " ke " + rute[0];

        System.out.println("  Rute dipilih : " + ruteYangDipilih);

        // Input jumlah tiket
        System.out.println();
        System.out.println("  Kapasitas kendaraan : " + kendaraan.getKapasitas() + " kursi");

        while (true) {
            System.out.print("  Masukkan jumlah tiket : ");
            try {
                int jml = Integer.parseInt(scanner.nextLine().trim());

                if (jml <= 0) {
                    System.out.println("  Jumlah tiket harus lebih dari 0.");
                    continue;
                }

                if (jml > kendaraan.getKapasitas()) {
                    System.out.println();
                    System.out.println("  =======================================");
                    System.out.println("           PEMESANAN TIDAK VALID           ");
                    System.out.println("  =======================================");
                    System.out.printf ("   Melebihi kapasitas kendaraan (%d kursi).%n",
                                       kendaraan.getKapasitas());
                    System.out.println("   Kembali ke menu utama                   ");
                    System.out.println("  =======================================");
                    return false;
                }

                jumlahTiket = jml;
                break;

            } catch (NumberFormatException e) {
                System.out.println("  Input tidak valid. Masukkan angka.");
            }
        }

        System.out.println("  Jumlah tiket : " + jumlahTiket);
        return true;
    }

    private void inputJumlahPenumpangMobil() {
        System.out.println();
        System.out.println("  Kapasitas mobil : " + kendaraan.getKapasitas() + " penumpang");

        while (true) {
            System.out.print("  Masukkan jumlah penumpang : ");
            try {
                int jml = Integer.parseInt(scanner.nextLine().trim());
                if (jml <= 0) {
                    System.out.println("  Jumlah harus lebih dari 0.");
                    continue;
                }
                if (jml > kendaraan.getKapasitas()) {
                    System.out.println("  Melebihi kapasitas (" + kendaraan.getKapasitas() + " orang).");
                    continue;
                }
                jumlahTiket = jml;
                break;
            } catch (NumberFormatException e) {
                System.out.println("  Input tidak valid. Masukkan angka.");
            }
        }
        System.out.println("  Jumlah penumpang : " + jumlahTiket);
    }

    private void pilihKursiSemua() {
        kursiDipilih = "";
        System.out.println();
        System.out.println("  ================================================");
        System.out.println("              PEMILIHAN TEMPAT DUDUK              ");
        System.out.println("  =================================================");

        for (int t = 1; t <= jumlahTiket; t++) {
            System.out.println();
            System.out.println("  >> Penumpang ke-" + t + " <<");
            String kursi = kendaraan.pilihKursi(scanner);
            if (t == 1) kursiDipilih  = kursi;
            else        kursiDipilih += ", " + kursi;
        }
    }

    private void prosesPembayaran() {
        // Ringkasan harga
        System.out.println();
        System.out.println("  ================================================");
        System.out.println("                  RINGKASAN HARGA                 ");
        System.out.println("  ================================================");

        if (kendaraan instanceof Pesawat) {
            Pesawat p = (Pesawat) kendaraan;
            System.out.printf("  Harga dasar        : Rp %,15.0f%n", p.getHargaDasar());
            System.out.printf("  Tambahan kelas %-9s: Rp %,15.0f%n",
                "(" + p.getKelasTiket() + ")", p.getHargaPerKelas());
            System.out.printf("  Jumlah tiket       :    %15d%n", jumlahTiket);
        } else if (kendaraan instanceof Mobil) {
            Mobil m = (Mobil) kendaraan;
            System.out.printf("  Harga per jam      : Rp %,15.0f%n", m.getHargaPerJam());
            System.out.printf("  Upah supir per jam : Rp %,15.0f%n", m.getUpahSupirPerJam());
            System.out.printf("  Lama sewa          :    %12d jam%n", m.getLamaSewa());
            System.out.printf("  Uang tambahan      : Rp %,15.0f%n", m.getUangTambahan());
        } else {
            double hargaSatuan = (jumlahTiket > 0) ? totalHarga / jumlahTiket : 0;
            // Untuk Kereta & Bus harga belum dihitung di sini, ambil langsung
            if (kendaraan instanceof Kereta) {
                hargaSatuan = ((Kereta) kendaraan).getHargaDasar();
            } else if (kendaraan instanceof Bus) {
                hargaSatuan = ((Bus) kendaraan).getHargaDasar();
            }
            System.out.printf("  Harga per tiket    : Rp %,15.0f%n", hargaSatuan);
            System.out.printf("  Jumlah tiket       :    %15d%n", jumlahTiket);
        }

        System.out.println("  =================================================");
        System.out.printf ("  TOTAL HARGA        : Rp %,15.0f%n", totalHarga);
        System.out.println("  =================================================");

        // Pilih metode pembayaran
        System.out.println();
        System.out.println("  =================================================");
        System.out.println("            PILIH METODE PEMBAYARAN              ");
        System.out.println("  =================================================");
        System.out.println("   1.  Transfer Bank                           ");
        System.out.println("   2.  E-Wallet                                ");
        System.out.println("  =================================================");

        int pilihanMetode;
        while (true) {
            System.out.print("  Pilih metode (1/2) : ");
            try {
                pilihanMetode = Integer.parseInt(scanner.nextLine().trim());
                if (pilihanMetode == 1 || pilihanMetode == 2) break;
                System.out.println("  Pilihan tidak valid. Masukkan 1 atau 2.");
            } catch (NumberFormatException e) {
                System.out.println("  Input tidak valid. Masukkan 1 atau 2.");
            }
        }

        String idPembayaran = generateId("PAY");
        String tanggalBayar = LocalDate.now().format(FMT);

        if (pilihanMetode == 1) {
            pembayaran = prosesTransferBank(idPembayaran, tanggalBayar);
        } else {
            pembayaran = prosesEWallet(idPembayaran, tanggalBayar);
        }

        // Konfirmasi bayar
        System.out.println();
        System.out.println("  ==================================================");
        System.out.println("            KONFIRMASI PEMBAYARAN");
        System.out.println("  ==================================================");
        System.out.printf("  %-20s Rp %,15.0f%n", "Total Bayar :", totalHarga);
        System.out.println("  ==================================================");

        System.out.println();
        System.out.println("  =================================================");
        System.out.println("             PEMBAYARAN BERHASIL!            ");
        System.out.println("  =================================================");
    }

    private TransferBank prosesTransferBank(String idPembayaran, String tanggalBayar) {
        System.out.println();
        System.out.println("  ================================================");
        System.out.println("                TRANSFER BANK                   ");
        System.out.println("  ================================================");
        for (int i = 0; i < TransferBank.DAFTAR_BANK.length; i++) {
            System.out.printf("%d.  %8s  No. Rek : %18s%n",
                i + 1,
                TransferBank.DAFTAR_BANK[i][0],
                TransferBank.DAFTAR_BANK[i][1]);
        }
        System.out.println("  ==============================================");

        int pilihan;
        while (true) {
            System.out.print("  Pilih bank (1-" + TransferBank.DAFTAR_BANK.length + ") : ");
            try {
                pilihan = Integer.parseInt(scanner.nextLine().trim());
                if (pilihan >= 1 && pilihan <= TransferBank.DAFTAR_BANK.length) break;
                System.out.println("  Pilihan tidak valid.");
            } catch (NumberFormatException e) {
                System.out.println("  Input tidak valid.");
            }
        }

        String[] bank = TransferBank.DAFTAR_BANK[pilihan - 1];
        System.out.println();
        System.out.println("  Detail Transfer");
        System.out.printf("  %-20s %s%n", "Nama Bank :", bank[0]);
        System.out.printf("  %-20s %s%n", "No. Rekening :", bank[1]);
        System.out.printf("  %-20s %s%n", "Atas Nama :", bank[2]);
        System.out.printf("  %-20s Rp %,15.0f%n", "Nominal Transfer :", totalHarga);
        System.out.println("  --------------------------------------------");

        return new TransferBank(idPembayaran, tanggalBayar, bank[0], bank[1], bank[2]);
    }

    private EWallet prosesEWallet(String idPembayaran, String tanggalBayar) {
        System.out.println();
        System.out.println("  ===============================================");
        System.out.println("                   E-WALLET                     ");
        System.out.println("  ===============================================");
        for (int i = 0; i < EWallet.pilihan_Ewallet.length; i++) {
            System.out.printf("%d.  %12s  No. HP : %14s%n",
                i + 1,
                EWallet.pilihan_Ewallet[i][0],
                EWallet.pilihan_Ewallet[i][1]);
        }
        System.out.println("  ============================================");

        int pilihan;
        while (true) {
            System.out.print("  Pilih e-wallet (1-" + EWallet.pilihan_Ewallet.length + ") : ");
            try {
                pilihan = Integer.parseInt(scanner.nextLine().trim());
                if (pilihan >= 1 && pilihan <= EWallet.pilihan_Ewallet.length) break;
                System.out.println("  Pilihan tidak valid.");
            } catch (NumberFormatException e) {
                System.out.println("  Input tidak valid.");
            }
        }

        String[] ew = EWallet.pilihan_Ewallet[pilihan - 1];
        System.out.println();
        System.out.println("  Detail E-Wallet");
        System.out.printf("  %-20s %s%n", "Nama E-Wallet :", ew[0]);
        System.out.printf("  %-20s %s%n", "Nomor Tujuan :", ew[1]);
        System.out.printf("  %-20s Rp %,15.0f%n", "Nominal Transfer :", totalHarga);
        System.out.println("  ----------------------------------------------");

        return new EWallet(idPembayaran, tanggalBayar, ew[0], ew[1]);
    }

    private void tampilDetailPemesanan() {
        System.out.println("=====================================================");
        System.out.println("       Tiket Perjalanan & Konfirmasi Pemesanan     ");
        System.out.println("=====================================================");
        
        // Data penumpang
        System.out.println();
        System.out.println("=====================================================");
        System.out.println("                 DATA PENUMPANG                   ");
        System.out.println("=====================================================");
        penumpang.tampilInfo();

        // Detail perjalanan
        System.out.println();
        System.out.println("=====================================================");
        System.out.println("                DETAIL PERJALANAN                 ");
        System.out.println("=====================================================");
        kendaraan.tampilInfo();
        System.out.println("  Tanggal Berangkat  : " + tanggalKeberangkatan);

        if (!(kendaraan instanceof Mobil)) {
            System.out.println("  Rute               : " + ruteYangDipilih);
            System.out.println("  Kursi              : " + kursiDipilih);
            System.out.println("  Jumlah Tiket       : " + jumlahTiket);
        } else {
            System.out.println("  Jumlah Penumpang   : " + jumlahTiket);
        }

        // Detail pembayaran
        System.out.println();
        System.out.println("=====================================================");
        System.out.println("                DETAIL PEMBAYARAN                 ");
        System.out.println("=====================================================");
        pembayaran.tampilInfo();
        System.out.println("=====================================================");
        System.out.printf ("  TOTAL BAYAR        : Rp %,15.0f%n", totalHarga);

        System.out.println();
        System.out.println("=====================================================");
        System.out.println("   Simpan tiket ini sebagai bukti pemesanan Anda. ");
        System.out.println("        Selamat bepergian bersama Travelis!       ");
        System.out.println("=====================================================");
    }

    private boolean tanyaKembaliMenu() {
        System.out.println();
        System.out.print("  Kembali ke Menu? (ya/tidak) : ");
        String jawaban = scanner.nextLine().trim().toLowerCase();
        return jawaban.equals("ya") || jawaban.equals("y");
    }

    private String generateId(String prefix) {
        return prefix + "-" + (System.currentTimeMillis() % 100_000);
    }

    private void tekanEnterLanjut() {
        System.out.println();
        System.out.print("  Tekan ENTER untuk kembali ke menu utama.");
        scanner.nextLine();
    }
}