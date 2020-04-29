package com.example.rpl.trompey;

public class GroomingHistory {
    private String paket, isi, harga, metode, status;

    public GroomingHistory(String paket, String isi, String harga, String metode, String status) {
        this.paket = paket;
        this.isi = isi;
        this.harga = harga;
        this.metode = metode;
        this.status = status;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {

        this.harga = harga;
    }

    public String getMetode() {
        return metode;
    }

    public void setMetode(String metode) {

        this.metode = metode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }
}