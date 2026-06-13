public class EWallet extends Pembayaran {

    private String namaEWallet;
    private String nomorTujuan;

    public static final String[][] pilihan_Ewallet = {
        { "GoPay",  "0812-3456-7890" },
        { "OVO",    "0812-3456-7890" },
        { "DANA",   "0812-3456-7890" },
        { "ShopeePay", "0812-3456-7890" }
    };

    public EWallet(String idPembayaran, String tanggalPembayaran,
                   String namaEWallet, String nomorTujuan) {
        super(idPembayaran, tanggalPembayaran);
        this.namaEWallet = namaEWallet;
        this.nomorTujuan = nomorTujuan;
    }

    public String getNamaEWallet() { return namaEWallet; }
    public String getNomorTujuan() { return nomorTujuan; }

    @Override
    public void tampilInfo() {
        System.out.println("  Metode Pembayaran  : E-Wallet");
        System.out.println("  ID Pembayaran      : " + idPembayaran);
        System.out.println("  Tanggal Pembayaran : " + tanggalPembayaran);
        System.out.println("  Nama E-Wallet      : " + namaEWallet);
        System.out.println("  Nomor Tujuan       : " + nomorTujuan);
    }
}