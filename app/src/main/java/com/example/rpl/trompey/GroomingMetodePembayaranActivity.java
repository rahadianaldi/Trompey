package com.example.rpl.trompey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class GroomingMetodePembayaranActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout transfer, cod, gopay;
    String paket, isi, harga, metode, status;
    DatabaseReference myRef;
    FirebaseAuth Auth;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grooming_metode_pembayaran);
        setTitle("Metode Pembayaran");

        transfer = findViewById(R.id.transfer);
        cod = findViewById(R.id.cod);
        gopay = findViewById(R.id.gopay);

        transfer.setOnClickListener(this);
        cod.setOnClickListener(this);
        gopay.setOnClickListener(this);


        Intent intent = getIntent();
        paket = getIntent().getStringExtra("PAKET");
        isi = getIntent().getStringExtra("ISI");
        harga = getIntent().getStringExtra("HARGA");

        Auth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("Data Paket Grooming");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.transfer:
                metode = "Transfer Bank";
                break;
            case R.id.cod:
                metode = "Cash On Delivery";
                break;
            case R.id.gopay:
                metode = "Gopay";
                break;
            default:
                return;
        }
        String id = myRef.push().getKey();
        String email = Auth.getCurrentUser().getEmail();

        HashMap<Object, String> mp = new HashMap<>();
        mp.put("id", id);
        mp.put("email", email);
        mp.put("Paket", paket);
        mp.put("Isi", isi);
        mp.put("Harga", harga);
        mp.put("Metode Pembayaran", metode);
        mp.put("Status", "Waiting...");

        myRef.child(id).setValue(mp);
        Toast.makeText(this, "Succes",Toast.LENGTH_SHORT).show();
        Intent metode = new Intent(this, GroomingBookActivity.class);
        startActivity(metode);

    }
}