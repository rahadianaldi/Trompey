package com.example.rpl.trompey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GroomingActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Paket Grooming");
        setContentView(R.layout.activity_grooming);
        Button btn_A = findViewById(R.id.btn_A);
        Button btn_B = findViewById(R.id.btn_B);
        Button btn_C = findViewById(R.id.btn_C);

        btn_A.setOnClickListener(this);
        btn_B.setOnClickListener(this);
        btn_C.setOnClickListener(this);
}
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_A:
                Intent A =new Intent(this, GroomingDetailsActivity.class);
                A.putExtra("PAKET", "A");
                A.putExtra("ISI", "  Cukur Bulu \n  Gunting Kuku Premium \n  Free Sisi Kawat");
                A.putExtra("HARGA", "  Rp. 250.0000");
                startActivity(A);
                break;
            case R.id.btn_B:
                Intent B =new Intent(this, GroomingDetailsActivity.class);
                B.putExtra("PAKET", "B");
                B.putExtra("ISI", "  Gunting Kuku \n  Free Shampo \n  Mandi ");
                B.putExtra("HARGA", "  Rp. 170.0000");
                startActivity(B);
                break;
            case R.id.btn_C:
                Intent C =new Intent(this, GroomingDetailsActivity.class);
                C.putExtra("PAKET", "C");
                C.putExtra("ISI", "  Mandi \n  Gunting Kuku \n  Free Sisi Kawat");
                C.putExtra("HARGA", "  Rp. 90.0000");
                startActivity(C);
                break;
        }
    }

    public void History_Grooming(View view) {
        Intent book = new Intent(this, GroomingHistoryActivity.class);
        startActivity(book);

    }
}
