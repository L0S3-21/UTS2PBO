package com.mycompany.projectpbo;
public class TransferBank extends Pembayaran {

    private String namaBank;
    private String noRekening;
    private String namaPemilikRekening;

    public static final String[][] DAFTAR_BANK = {
        { "BCA",     "1234567890", "PT Travelis Indonesia" },
        { "Mandiri", "0987654321", "PT Travelis Indonesia" },
        { "BNI",     "1122334455", "PT Travelis Indonesia" },
        { "BRI",     "5544332211", "PT Travelis Indonesia" }
    };

    public TransferBank(String idPembayaran, String tanggalPembayaran,
                        String namaBank, String noRekening,
                        String namaPemilikRekening) {
        super(idPembayaran, tanggalPembayaran);
        this.namaBank            = namaBank;
        this.noRekening          = noRekening;
        this.namaPemilikRekening = namaPemilikRekening;
    }

    public String getNamaBank()            { return namaBank; }
    public String getNoRekening()          { return noRekening; }
    public String getNamaPemilikRekening() { return namaPemilikRekening; }

    @Override
    public void tampilInfo() {
        System.out.println("Metode Pembayaran  : Transfer Bank");
        System.out.println("  ID Pembayaran      : " + idPembayaran);
        System.out.println("  Tanggal Pembayaran : " + tanggalPembayaran);
        System.out.println("  Nama Bank          : " + namaBank);
        System.out.println("  No. Rekening       : " + noRekening);
        System.out.println("  Nama Pemilik Rek.  : " + namaPemilikRekening);
    }
}