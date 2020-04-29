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

public class MakananBayar extends AppCompatActivity {
    TextView judulmakan, hargamakan;
    FirebaseAuth mAuth;
    DatabaseReference db;
    String nama,harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_makanan);

        judulmakan = findViewById(R.id.judulmakan);
        hargamakan = findViewById(R.id.hargamakan);

        nama = getIntent().getStringExtra("judulmakan");
        harga = getIntent().getStringExtra("hargamakan");

        judulmakan.setText(nama);
        hargamakan.setText(harga);
        Toast.makeText(this,nama, Toast.LENGTH_SHORT).show();

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference("datamakanan");

    }

    public void upload(View view) {
        String id = db.push().getKey();
        String email = mAuth.getCurrentUser().getEmail();

        HashMap<Object,String> datamakanan = new HashMap<>();
        datamakanan.put("id",id);
        datamakanan.put("email",email);
        datamakanan.put("nama", nama);
        datamakanan.put("harga", harga);

        db.child(id).setValue(datamakanan);


        Intent upload = new Intent(MakananBayar.this, BuktiTransferActivity.class);
        startActivity(upload);

    }
}
