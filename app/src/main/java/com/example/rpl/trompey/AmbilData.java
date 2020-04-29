package com.example.rpl.trompey;

public class AmbilData {
    private String name, nomor, alamat;
    String username;

    public AmbilData(String name, String username, String nomor, String alamat) {
        this.name = name;
        this.username = username;
        this.nomor = nomor;
        this.alamat = alamat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
