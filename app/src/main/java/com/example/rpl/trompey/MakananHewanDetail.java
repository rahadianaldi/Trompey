package com.example.rpl.trompey;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MakananHewanDetail extends AppCompatActivity {
    ImageView gambar;
    TextView tvNama, tvHarga, tvDeskripsi;
    String nama, harga, deskripsi;
    int idGambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makanan_hewan_detail);

        gambar = findViewById(R.id.gambarmakandetail);
        tvNama = findViewById(R.id.namamakandetail);
        tvHarga = findViewById(R.id.hargamakandetail);
        tvDeskripsi = findViewById(R.id.desc_makan);

        nama = getIntent().getStringExtra("nama");
        harga = getIntent().getStringExtra("harga");
        idGambar = getIntent().getIntExtra("gambar",0);
        deskripsi = getIntent().getStringExtra("deskripsi");

        tvNama.setText(nama);
        tvHarga.setText(harga);
        Glide.with(this).load(idGambar).into(gambar);
        tvDeskripsi.setText(deskripsi);
    }
}
