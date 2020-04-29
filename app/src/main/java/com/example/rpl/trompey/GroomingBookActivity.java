package com.example.rpl.trompey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class GroomingBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_grooming);

        setTitle("Booking Grooming");

    }

    public void onClick(View view) {
        Intent intent = new Intent(this, GroomingActivity.class);
        startActivity(intent);
    }
}
