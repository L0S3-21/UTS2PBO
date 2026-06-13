public abstract class Pembayaran implements TampilInfo {

    // ── Atribut ───────────────────────────────────────────────────────────────
    protected String idPembayaran;
    protected String tanggalPembayaran;

    // ── Constructor ───────────────────────────────────────────────────────────
    public Pembayaran(String idPembayaran, String tanggalPembayaran) {
        this.idPembayaran      = idPembayaran;
        this.tanggalPembayaran = tanggalPembayaran;
    }

    // ── Getter ────────────────────────────────────────────────────────────────
    public String getIdPembayaran()      { return idPembayaran; }
    public String getTanggalPembayaran() { return tanggalPembayaran; }

    // ── Abstract ──────────────────────────────────────────────────────────────
    @Override
    public abstract void tampilInfo();
}