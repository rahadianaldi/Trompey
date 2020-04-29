package com.example.rpl.trompey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;


public class MakananHewan extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private ArrayList<Makanan> makanan;
    private String[] judulmakan;
    private TypedArray gambarmakan;
    private String[] hargamakan;
    private String[] deskripsimakan;

    private RecyclerView mylistview;
    private MakananAdapter adapterlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_makanan);
        setTitle("Menu Makanan");
        makanan = new ArrayList<Makanan>();
        judulmakan = getResources().getStringArray(R.array.judulmakan);
        gambarmakan = getResources().obtainTypedArray(R.array.gambarmakan);
        hargamakan = getResources().getStringArray(R.array.hargamakan);
        deskripsimakan = getResources().getStringArray(R.array.deskripsi_makan);

        for (int i = 0; i < judulmakan.length; i++) {
            Makanan item = new Makanan(judulmakan[i],
                    gambarmakan.getResourceId(i, -1), hargamakan[i],deskripsimakan[i]);
            makanan.add(item);
        }
        mylistview = findViewById(R.id.list);
        mylistview.setLayoutManager(new GridLayoutManager(this,2));
        adapterlist = new MakananAdapter(this, makanan);
        mylistview.setAdapter(adapterlist);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }

    public void beli(View view) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void historymakan(View view) {
        Intent upload = new Intent(MakananHewan.this, MakananHewanHistory.class);
        startActivity(upload);
    }
}




