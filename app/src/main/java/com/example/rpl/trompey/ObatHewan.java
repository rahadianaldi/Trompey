package com.example.rpl.trompey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ObatHewan extends AppCompatActivity {

    private RecyclerView rvObatHewan;
    private ArrayList<Obat> obatlist;
    private ObatAdapter mAdapter;
    private DatabaseReference myRef;
    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obat_hewan);

        setTitle("List Obat Hewan");

        Auth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("List Data Obat");

        rvObatHewan = findViewById(R.id.rv_obat_hewan);

        rvObatHewan.setLayoutManager(new GridLayoutManager(this,2));

        obatlist = new ArrayList<>();
        mAdapter = new ObatAdapter(this,obatlist);
        rvObatHewan.setAdapter(mAdapter);

        getDataObat();
    }

    private void getDataObat() {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot DS : dataSnapshot.getChildren()){
                    String nama = DS.child("nama").getValue().toString();
                    String harga = DS.child("harga").getValue().toString();
                    String gambar = DS.child("gambar").getValue().toString();
                    String deskripsi = DS.child("deskripsi").getValue().toString();

                    Obat OH = new Obat(nama, harga, gambar, deskripsi);

                    obatlist.add(OH);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                showMessage("error", databaseError.getMessage());
            }
        });

    }

    public void history(View view) {
        startActivity(new Intent(this, ObatHewanHistory.class));
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
