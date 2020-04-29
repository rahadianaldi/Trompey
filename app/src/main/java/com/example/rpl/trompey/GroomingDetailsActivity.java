package com.example.rpl.trompey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class GroomingDetailsActivity extends AppCompatActivity {
    String paket,isi,harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_grooming);
        setTitle("Details Grooming");
        Intent intent = getIntent();

        TextView tvPaket = findViewById(R.id.tv_paket);
        TextView tvIsi = findViewById(R.id.tv_isi);
        TextView tvHarga = findViewById(R.id.tv_harga);

        paket = intent.getStringExtra("PAKET");
        isi = intent.getStringExtra("ISI");
        harga = intent.getStringExtra("HARGA");

        tvPaket.setText(paket);
        tvIsi.setText(isi);
        tvHarga.setText(harga);


    }
    public void Book(View view) {
        Intent book = new Intent(this, GroomingMetodePembayaranActivity.class);
        book.putExtra("PAKET", paket);
        book.putExtra("ISI",isi);
        book.putExtra("HARGA",harga);
        startActivity(book);

    }
}
