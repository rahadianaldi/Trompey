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

public class GroomingHistoryActivity extends AppCompatActivity {
    private RecyclerView rvGroomingHistory;
    private ArrayList<GroomingHistory>history;
    private GroomingHistoryAdapter mAdapter;
    private DatabaseReference myRef;
    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grooming_history);

        setTitle("History Grooming");
        rvGroomingHistory =findViewById(R.id.list_history_grooming);
        rvGroomingHistory.setLayoutManager(new LinearLayoutManager(this));

        history = new ArrayList<>();
        Auth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("Data Paket Grooming");

        mAdapter = new GroomingHistoryAdapter(this, history);
        rvGroomingHistory.setAdapter(mAdapter);

        getDataGrooming();
    }
    private void getDataGrooming(){
        final String email = Auth.getCurrentUser().getEmail();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot DS : dataSnapshot.getChildren()){
                    String user = DS.child("email").getValue().toString();

                    if (user.equalsIgnoreCase(email)){
                        String paket = DS.child("Paket").getValue().toString();
                        String isi = DS.child("Isi").getValue().toString();
                        String harga = DS.child("Harga").getValue().toString();
                        String metode = DS.child("Metode Pembayaran").getValue().toString();
                        String status = DS.child("Status").getValue().toString();

                        GroomingHistory PaketGrooming = new GroomingHistory(paket,isi,harga,metode,status);
                        history.add(PaketGrooming);
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
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
