package com.example.rpl.trompey;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;

public class ListAdminObat extends AppCompatActivity {

    RecyclerView rvLIstObat;
    private ArrayList<Obat> adminArrayList;
    DatabaseReference db;
    public AdminObatAdpter obatAdpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_admin_obat);

        rvLIstObat = findViewById(R.id.rv_list_admin_obat);
        rvLIstObat.setLayoutManager(new GridLayoutManager(this,2));
        adminArrayList = new ArrayList<>();

        obatAdpter = new AdminObatAdpter(adminArrayList,this);
        rvLIstObat.setAdapter(obatAdpter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(ListAdminObat.this, AddObatHewan.class);
                startActivity(add);
            }
        });

        initComponent();
    }

    private void initComponent() {
        db = FirebaseDatabase.getInstance().getReference("List Data Obat");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adminArrayList.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Obat ob = new Obat();
                    String id = ds.child("id").getValue().toString();
                    String name = ds.child("nama").getValue().toString();
                    String harga = ds.child("harga").getValue().toString();
                    String desc = ds.child("deskripsi").getValue().toString();
                    String img = ds.child("gambar").getValue().toString();

                    ob.setId(id);
                    ob.setNama(name);
                    ob.setHarga(harga);
                    ob.setDeskripsi(desc);
                    ob.setGambar(img);
                    adminArrayList.add(ob);
                }

                obatAdpter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
