package com.example.rpl.trompey;

public class Dokter {
    private String nama, harga, jadwal;
    private int gambar;

    public Dokter(String nama, String harga, String jadwal, int gambar) {
        this.nama = nama;
        this.harga = harga;
        this.jadwal = jadwal;
        this.gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getJadwal() {
        return jadwal;
    }

    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }
}
