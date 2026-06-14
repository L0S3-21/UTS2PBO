package com.mycompany.projectpbo;
public abstract class Pembayaran implements TampilInfo {

    protected String idPembayaran;
    protected String tanggalPembayaran;

    public Pembayaran(String idPembayaran, String tanggalPembayaran) {
        this.idPembayaran      = idPembayaran;
        this.tanggalPembayaran = tanggalPembayaran;
    }

    public String getIdPembayaran()      { return idPembayaran; }
    public String getTanggalPembayaran() { return tanggalPembayaran; }

    @Override
    public abstract void tampilInfo();
}