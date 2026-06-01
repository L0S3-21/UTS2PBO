public class EWallet extends Pembayaran {

    private String namaWallet;
    private String nomorHP;

    public EWallet(String idPembayaran,
                   double totalBayar,
                   String tanggalPembayaran,
                   String namaWallet,
                   String nomorHP) {

        super(idPembayaran, totalBayar, tanggalPembayaran);

        this.namaWallet = namaWallet;
        this.nomorHP = nomorHP;
    }

    @Override
    public void tampilPembayaran() {

        super.tampilPembayaran();

        System.out.println("Metode            : E-Wallet");
        System.out.println("Provider          : " + namaWallet);
        System.out.println("No HP             : " + nomorHP);
    }
}