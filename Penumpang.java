public class Penumpang {

    private String idPenumpang;
    private String nama;
    private String noHP;
    private String email;

    public Penumpang(String idPenumpang, String nama, String noHP, String email) {

        this.idPenumpang = idPenumpang;
        this.nama = nama;
        this.noHP = noHP;
        this.email = email;
    }

    public void tampilInfo() {

        System.out.println("ID Penumpang : " + idPenumpang);
        System.out.println("Nama         : " + nama);
        System.out.println("No HP        : " + noHP);
        System.out.println("Email        : " + email);
    }
}
