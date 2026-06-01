public class TransferBank extends Pembayaran {

    private String namaBank;
    private String nomorRekening;
    private String namaPemilik;

    public TransferBank(String idPembayaran,
                        double totalBayar,
                        String tanggalPembayaran,
                        String namaBank,
                        String nomorRekening,
                        String namaPemilik) {

        super(idPembayaran, totalBayar, tanggalPembayaran);

        this.namaBank = namaBank;
        this.nomorRekening = nomorRekening;
        this.namaPemilik = namaPemilik;
    }

    @Override
    public void tampilPembayaran() {

        super.tampilPembayaran();

        System.out.println("Metode            : Transfer Bank");
        System.out.println("Bank              : " + namaBank);
        System.out.println("No Rekening       : " + nomorRekening);
        System.out.println("Atas Nama         : " + namaPemilik);
    }
}