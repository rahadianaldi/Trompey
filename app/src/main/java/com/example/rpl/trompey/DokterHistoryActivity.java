package com.example.rpl.trompey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DokterHistoryActivity extends AppCompatActivity {
    private RecyclerView rvDokterHistory;
    private ArrayList<DokterHistory> history;
    private DokterHistoryAdapter mAdapter;
    private DatabaseReference myRef;
    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter_history_activity);

        setTitle("History Dokter");

        rvDokterHistory = findViewById(R.id.rv_history_dokter);

        rvDokterHistory.setLayoutManager(new LinearLayoutManager(this));

        history = new ArrayList<>();
        Auth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("Data Konsultasi Dokter");

        mAdapter = new DokterHistoryAdapter(this, history);
        rvDokterHistory.setAdapter(mAdapter);

        getDataDokter();
    }

    private void getDataDokter() {
        final String email = Auth.getCurrentUser().getEmail();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot DS : dataSnapshot.getChildren()) {
                    String user = DS.child("email").getValue().toString();

                    if (user.equalsIgnoreCase(email)) {
                        String nama = DS.child("nama").getValue().toString();
                        String harga = DS.child("harga").getValue().toString();
                        String jadwal = DS.child("jadwal").getValue().toString();
                        String waktu = DS.child("waktu").getValue().toString();

                        DokterHistory DH = new DokterHistory(nama, harga, jadwal, waktu);

                        history.add(DH);
                    }
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                showMessage("error", databaseError.getMessage());

            }
        });
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
