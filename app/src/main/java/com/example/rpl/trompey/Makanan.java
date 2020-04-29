package com.example.rpl.trompey;

public class Makanan {
    private String judulmakan;
    private int gambarmakan;
    private String hargamakan;
    private String deskripsimakanan;


    public Makanan(String judulmakan, int gambarmakan, String hargamakan, String deskripsimakanan
                   ) {

        this.judulmakan = judulmakan;
        this.gambarmakan = gambarmakan;
        this.hargamakan = hargamakan;
        this.deskripsimakanan = deskripsimakanan;



    }

    public String getJudulmakan() { return judulmakan;
    }
    public int getGambarmakan() {
        return gambarmakan;
    }
    public String getHargamakan() {
        return hargamakan;
    }
    public String getDeskripsimakanan () { return deskripsimakanan;}



    }
