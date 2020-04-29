package com.example.rpl.trompey;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MakananHewanHistory extends AppCompatActivity {

    private RecyclerView rvMakananHistory;
    private ArrayList<MakananHistory> history;
    private MakananHistoryAdapter mAdapter;
    private DatabaseReference myRef;
    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_makanan);

        setTitle("History Makanan Hewan");

        rvMakananHistory = findViewById(R.id.list_history_makanan);

        rvMakananHistory.setLayoutManager(new LinearLayoutManager(this));

        history = new ArrayList<>();
        Auth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("datamakanan");

        mAdapter = new MakananHistoryAdapter(this, history);
        rvMakananHistory.setAdapter(mAdapter);

        getDataObat();
    }

    private void getDataObat() {
        final String email = Auth.getCurrentUser().getEmail();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot DS : dataSnapshot.getChildren()){
                    String user = DS.child("email").getValue().toString();

                    if (user.equalsIgnoreCase(email)){
                        String nama = DS.child("nama").getValue().toString();
                        String harga = DS.child("harga").getValue().toString();

                        MakananHistory OH = new MakananHistory(nama, harga);


                        history.add(OH);
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
