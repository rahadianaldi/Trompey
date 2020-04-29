package com.example.rpl.trompey;

public class DokterHistory {
    private String nama, harga, jadwal, waktu;

    public DokterHistory(String nama, String harga, String jadwal, String waktu){
        this.nama = nama;
        this.harga = harga;
        this.jadwal = jadwal;
        this.waktu = waktu;
    }

    public String getJadwal() {
        return jadwal;
    }

    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
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
}
