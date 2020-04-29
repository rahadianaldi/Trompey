package com.example.rpl.trompey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AdminDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvEmail, tvStatus, tvPaket, tvHarga;
    Button btnAccept, btnDecline;
    DatabaseReference db;
    String id;
    Query q;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_details);

        setTitle("Detail Paket User");

        Intent data = getIntent();
        id = data.getStringExtra("id");

        tvEmail = findViewById(R.id.tv_name);
        tvStatus = findViewById(R.id.tv_status);
        tvPaket = findViewById(R.id.tv_paket);
        tvHarga = findViewById(R.id.tv_paket);
        tvHarga = findViewById(R.id.tv_harga);
        btnAccept = findViewById(R.id.btn_accept);
        btnDecline = findViewById(R.id.btn_decline);
        btnAccept.setOnClickListener(this);
        btnDecline.setOnClickListener(this);

        db = FirebaseDatabase.getInstance().getReference("Data Paket Grooming");

        Query q = db.orderByChild("id").equalTo(id);
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()){

                    String email = ds.child("email").getValue().toString();
                    String paket = ds.child("Paket").getValue().toString();
                    String harga = ds.child("Harga").getValue().toString();

                    tvEmail.setText(email);
                    tvHarga.setText("Harga :"+harga);
                    tvPaket.setText("Paket :"+paket);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_accept:
                db.child(id).child("Status").setValue("Accept");
                Toast.makeText(this, "Accept ", Toast.LENGTH_SHORT).show();
                Intent acc = new Intent(this,ListAdminAccept.class);
                startActivity(acc);
                break;
            case R.id.btn_decline:
                db.child(id).child("Status").setValue("Decline");
                Toast.makeText(this, "Decline ", Toast.LENGTH_SHORT).show();
                Intent dec = new Intent(this,ListAdminDecline.class);
                startActivity(dec);
                break;

        }
    }
}
