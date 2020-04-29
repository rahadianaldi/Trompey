package com.example.rpl.trompey;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {
    private DatabaseReference myRef;
    EditText editName, editUsername, editNomor, editAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editName = findViewById(R.id.editText);
        editUsername =findViewById(R.id.editText2);
        editNomor = findViewById(R.id.editText3);
        editAlamat =findViewById(R.id.editText4);
        myRef = FirebaseDatabase.getInstance().getReference();
    }

    public void logout(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }

    public void edit(View view) {
        AmbilData setget = new AmbilData(editName.getText().toString(),
                editUsername.getText().toString(),
                editNomor.getText().toString(),editAlamat.getText().toString());
        myRef.child("User").child(editUsername.getText().toString()).setValue(setget);
    }
}
