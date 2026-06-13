public class Penumpang implements TampilInfo {

    // ── Atribut ───────────────────────────────────────────────────────────────
    private String idPenumpang;
    private String nama;
    private String noHp;
    private String email;
    private String nik;
    private String tanggalPemesanan;

    // ── Constructor ───────────────────────────────────────────────────────────
    public Penumpang(String idPenumpang, String nama, String noHp,
                     String email, String nik, String tanggalPemesanan) {
        this.idPenumpang      = idPenumpang;
        this.nama             = nama;
        this.noHp             = noHp;
        this.email            = email;
        this.nik              = nik;
        this.tanggalPemesanan = tanggalPemesanan;
    }

    // ── Getter ────────────────────────────────────────────────────────────────
    public String getIdPenumpang()      { return idPenumpang; }
    public String getNama()             { return nama; }
    public String getNoHp()             { return noHp; }
    public String getEmail()            { return email; }
    public String getNik()              { return nik; }
    public String getTanggalPemesanan() { return tanggalPemesanan; }

    // ── tampilInfo ────────────────────────────────────────────────────────────
    @Override
    public void tampilInfo() {
        System.out.println("  ID Penumpang       : " + idPenumpang);
        System.out.println("  Nama               : " + nama);
        System.out.println("  No. HP             : " + noHp);
        System.out.println("  Email              : " + email);
        System.out.println("  NIK                : " + nik);
        System.out.println("  Tanggal Pemesanan  : " + tanggalPemesanan);
    }
}