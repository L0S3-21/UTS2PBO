public abstract class Pembayaran implements CekPembayaran {

    protected String idPembayaran;
    protected String statusPembayaran;
    protected double totalBayar;
    protected String tanggalPembayaran;

    public Pembayaran(String idPembayaran,
                      double totalBayar,
                      String tanggalPembayaran) {

        this.idPembayaran = idPembayaran;
        this.totalBayar = totalBayar;
        this.tanggalPembayaran = tanggalPembayaran;
        this.statusPembayaran = "Belum Dibayar";
    }

    public String getIdPembayaran() {
        return idPembayaran;
    }

    public String getStatusPembayaran() {
        return statusPembayaran;
    }

    public double getTotalBayar() {
        return totalBayar;
    }

    public String getTanggalPembayaran() {
        return tanggalPembayaran;
    }

    public void setStatusPembayaran(String statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }

    @Override
    public void bayar() {
        statusPembayaran = "Pembayaran Berhasil";
    }

    @Override
    public void refund() {
        statusPembayaran = "Refund";
    }

    @Override
    public String cekStatus() {
        return statusPembayaran;
    }

    public void tampilPembayaran() {

        System.out.println("ID Pembayaran     : " + idPembayaran);
        System.out.println("Tanggal Bayar     : " + tanggalPembayaran);
        System.out.println("Total Bayar       : Rp" + totalBayar);
        System.out.println("Status            : " + statusPembayaran);
    }
}