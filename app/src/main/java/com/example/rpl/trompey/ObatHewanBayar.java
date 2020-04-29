package com.example.rpl.trompey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ObatHewanBayar extends AppCompatActivity {
    TextView nama_barang, harga_barang;
    DatabaseReference myRef;
    FirebaseAuth Auth;
    String nama, harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obat_hewan_bayar);

        nama_barang = findViewById(R.id.nama_barang);
        harga_barang = findViewById(R.id.harga_barang);

        nama = getIntent().getStringExtra("nama");
        harga = getIntent().getStringExtra("harga");

        nama_barang.setText(nama);
        harga_barang.setText(harga);

        Auth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("Data Obat Hewan");
    }

    public void Bayar(View view) {
        String id = myRef.push().getKey();
        String email = Auth.getCurrentUser().getEmail();

        HashMap<Object, String> obat = new HashMap<>();
        obat.put("id", id);
        obat.put("email", email);
        obat.put("nama", nama);
        obat.put("harga", harga);

        myRef.child(id).setValue(obat);

        startActivity(new Intent(this, ObatHewanCheckOut.class));
    }
}
