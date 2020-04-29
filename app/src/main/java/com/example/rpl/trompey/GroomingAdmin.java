package com.example.rpl.trompey;

public class GroomingAdmin {
    String id;
    String email;
    String paket;
    String harga;
    String status;
    public GroomingAdmin(String id, String email, String paket, String harga, String status) {
        this.id = id;
        this.email = email;
        this.paket = paket;
        this.harga = harga;
        this.status = status;
    }


    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPaket() {
        return paket;
    }

    public String getHarga() {
        return harga;
    }

    public String getStatus() {
        return status;
    }
}
