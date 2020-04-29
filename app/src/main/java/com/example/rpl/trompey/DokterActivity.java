package com.example.rpl.trompey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class DokterActivity extends AppCompatActivity {

    private RecyclerView rvDokter;
    private ArrayList<Dokter> dokterlist;
    private DokterAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter);

        setTitle("List Dokter");

        rvDokter = findViewById(R.id.rv_dokter);

        rvDokter.setLayoutManager(new GridLayoutManager(this,2));

        dokterlist = new ArrayList<>();
        mAdapter = new DokterAdapter(this,dokterlist);
        rvDokter.setAdapter(mAdapter);

        getDataDokter();
    }

    private void getDataDokter() {
        String[] nama = getResources().getStringArray(R.array.nama_dokter);
        String[] harga = getResources().getStringArray(R.array.harga_dokter);
        String[] jadwal = getResources().getStringArray(R.array.jadwal_dokter);
        TypedArray gambar = getResources().obtainTypedArray(R.array.gambar_dokter);


        dokterlist.clear();

        for (int i = 0; i < nama.length; i++) {
            dokterlist.add(new Dokter(nama[i], harga[i], jadwal[i],
                    gambar.getResourceId(i, 0)));
        }
        gambar.recycle();
        mAdapter.notifyDataSetChanged();
    }


    public void history(View view) {
        startActivity(new Intent(this, DokterHistoryActivity.class));
    }
}
