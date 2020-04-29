package com.example.rpl.trompey;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ObatHewanDetail extends AppCompatActivity {

    ImageView gambar;
    TextView tvNama, tvHarga, tvDeskripsi;
    String nama, harga, deskripsi, idGambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obat_hewan_detail);

        setTitle("Detail Obat");

        gambar = findViewById(R.id.DetailGambarObat);
        tvNama = findViewById(R.id.DetailNamaObat);
        tvHarga = findViewById(R.id.DetailHargaObat);
        tvDeskripsi = findViewById(R.id.DetailDeskripsiObat);

        nama = getIntent().getStringExtra("nama");
        harga = getIntent().getStringExtra("harga");
        idGambar = getIntent().getStringExtra("gambar");
        deskripsi = getIntent().getStringExtra("deskripsi");

        tvNama.setText(nama);
        tvHarga.setText(harga);
        Glide.with(this).load(idGambar).into(gambar);
        tvDeskripsi.setText(deskripsi);
    }
}
