package com.example.rpl.trompey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DetailDokter extends AppCompatActivity {
    TextView nama_dokter, harga_dokter, jadwal_dokter;
    EditText jam;
    DatabaseReference myRef;
    FirebaseAuth Auth;
    String nama, harga, jadwal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dokter);

        nama_dokter = findViewById(R.id.nama_dokter);
        harga_dokter = findViewById(R.id.harga_dokter);
        jadwal_dokter = findViewById(R.id.jadwal_dokter);
        jam = findViewById(R.id.editText_waktu);

        nama = getIntent().getStringExtra("nama");
        harga = getIntent().getStringExtra("harga");
        jadwal = getIntent().getStringExtra("jadwal");

        nama_dokter.setText(nama);
        harga_dokter.setText(harga);
        jadwal_dokter.setText(jadwal);

        Auth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("Data Konsultasi Dokter");
    }

    public void Booking(View view) {
        String waktu = jam.getText().toString();
        if (jam.getText().toString().length()==0){
            jam.setError("Jam Diperlukan!");
        }else {
            String id = myRef.push().getKey();
            String email = Auth.getCurrentUser().getEmail();

            HashMap<Object, String> dokter = new HashMap<>();
            dokter.put("id", id);
            dokter.put("email", email);
            dokter.put("nama", nama);
            dokter.put("harga", harga);
            dokter.put("jadwal", jadwal);
            dokter.put("waktu", waktu);

            myRef.child(id).setValue(dokter);

            Intent konsul_dokter = new Intent(this, PembayaranDokter.class);
            konsul_dokter.putExtra("waktu", waktu);
            konsul_dokter.putExtra("nama", nama);
            konsul_dokter.putExtra("harga", harga);
            konsul_dokter.putExtra("jadwal", jadwal);

            startActivity(konsul_dokter);
            Toast.makeText(this, "Pesan", Toast.LENGTH_SHORT).show();
        }
    }
}
