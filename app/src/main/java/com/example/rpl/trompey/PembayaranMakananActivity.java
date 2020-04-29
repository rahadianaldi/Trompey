package com.example.rpl.trompey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PembayaranMakananActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_makanan);
    }

    public void upload(View view) {
        Intent upload = new Intent(PembayaranMakananActivity.this, BuktiTransferActivity.class);
        startActivity(upload);
    }
}
