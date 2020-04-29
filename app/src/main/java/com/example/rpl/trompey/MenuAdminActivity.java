package com.example.rpl.trompey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuAdminActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout btnGrooming, btnListDecline,btnListAccept,btnAddobat;
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

        btnGrooming = findViewById(R.id.menu_grooming);
        btnListDecline =findViewById(R.id.list_decline);
        btnListAccept = findViewById(R.id.list_accept);
        btnAddobat = findViewById(R.id.add_obat);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        btnAddobat.setOnClickListener(this);
        btnListAccept.setOnClickListener(this);
        btnListDecline.setOnClickListener(this);
        btnGrooming.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu_grooming:
                Intent list = new Intent(this,ListAdminActivity.class);
                startActivity(list);
                break;
            case R.id.list_accept:
                Intent listacc = new Intent(this,ListAdminAccept.class);
                startActivity(listacc);
                break;

            case R.id.list_decline:
                Intent listdec = new Intent(this,ListAdminDecline.class);
                startActivity(listdec);
                break;
            case R.id.add_obat:
                Intent addoabt = new Intent(this,ListAdminObat.class);
                startActivity(addoabt);
                break;
        }
    }

    public void logout(View view) {
        mFirebaseAuth.getInstance().signOut();

        SharedPreferences datalogin = this.getSharedPreferences("UserLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor clearData = datalogin.edit();
        clearData.remove("email");
        clearData.remove("uid");
        clearData.remove("token");
        clearData.apply();
        Intent login = new Intent(this,LoginActivity.class);
        startActivity(login);
        Toast.makeText(this,"Logout",Toast.LENGTH_SHORT).show();
        finish();
    }
}
