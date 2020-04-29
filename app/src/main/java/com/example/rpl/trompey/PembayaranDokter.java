package com.example.rpl.trompey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PembayaranDokter extends AppCompatActivity {
    TextView nama_dokter, harga_dokter, jadwal_dokter, jam_janjian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_dokter);

        nama_dokter = findViewById(R.id.nama_dokter);
        harga_dokter = findViewById(R.id.harga_dokter);
        jadwal_dokter = findViewById(R.id.jadwal_dokter);
        jam_janjian = findViewById(R.id.jam_janjian);


        String nama = getIntent().getStringExtra("nama");
        String harga = getIntent().getStringExtra("harga");
        String jadwal = getIntent().getStringExtra("jadwal");
        String jam = getIntent().getStringExtra("waktu");

        nama_dokter.setText(nama);
        harga_dokter.setText(harga);
        jadwal_dokter.setText(jadwal);
        jam_janjian.setText(jam);

    }

    public void upload_bukti(View view) {
        startActivity(new Intent(this, DokterCheckOut.class));
        Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();
    }
}
