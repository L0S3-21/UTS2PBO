/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vino.projectpbo;

/**
 *
 * @author HP
 */
public abstract class Kendaraan {

    protected String idKendaraan;
    protected String namaKendaraan;
    protected String jenisKendaraan;

    //constructor
    public Kendaraan(String idKendaraan, String namaKendaraan, String jenisKendaraan) {
        this.idKendaraan = idKendaraan;
        this.namaKendaraan = namaKendaraan;
        this.jenisKendaraan = jenisKendaraan;
    }

    //getter dan setter idKendaraan
    public String getIdKendaraan() {
        return idKendaraan;
    }
    public void setIdKendaraan(String idKendaraan) {
        this.idKendaraan = idKendaraan;
    }

    //getter dan setter namaKendaraan
    public String getNamaKendaraan() {
        return namaKendaraan;
    }
    public void setNamaKendaraan(String namaKendaraan) {
        this.namaKendaraan = namaKendaraan;
    }

    //getter dan setter jenisKendaraan
    public String getJenisKendaraan() {
        return jenisKendaraan;
    }
    public void setJenisKendaraan(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
    }
    
    public abstract void inisialisasiKursi();
    public abstract void tampilKursi();
    public abstract void pilihKursi(String kodeKursi);
    public abstract double hitungTagihan();
    public abstract void tampilInfo();
}
