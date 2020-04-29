package com.example.rpl.trompey;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.IOException;

public class AddObatHewan extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 77;
    EditText etNama,etHarga,etDesc;
    ImageView imgObat;
    Button btnUpload;
    Context context;
    FirebaseDatabase fb;
    DatabaseReference db;
    FirebaseStorage fs;
    StorageReference sf;
    Uri filePath;
    public Obat ob = new Obat();

    ProgressDialog load ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_obat_hewan);



        context=this;
        etNama = findViewById(R.id.et_namaObat);
        etHarga = findViewById(R.id.et_hargaObat);
        etDesc = findViewById(R.id.et_deskripsiObat);
        imgObat = findViewById(R.id.img_obat);
        btnUpload = findViewById(R.id.btn_upload);

        db = FirebaseDatabase.getInstance().getReference("List Data Obat");

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nama,harga,des,obat;
                nama = etNama.getText().toString();
                harga = etHarga.getText().toString();
                des = etDesc.getText().toString();
                obat = imgObat.getDrawable().toString();
                if (!isValidData(nama,harga,des,obat))return;
                ob.setNama(nama);
                ob.setHarga(harga);
                ob.setDeskripsi(des);
                try {
                    uploadFirebase();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Belum ada image", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void uploadFirebase() {

        Uri path = Uri.parse(ob.getGambar());
        fs = FirebaseStorage.getInstance();
        sf = fs.getReference();

        if (path != null){
            load = ProgressDialog.show(context,null,"Wait...",true,false);
            String nameFile = "images/obat_"+ob.getNama();
            StorageReference ref = sf.child(nameFile);
            ref.putFile(path).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> uriimg = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriimg.isSuccessful());
                    Uri donwload = uriimg.getResult();
                    ob.setGambar(donwload.toString());

                    String id = db.push().getKey();
                    ob.setId(id);
                    db.child(id).setValue(ob);
                    load.dismiss();
                    Toast.makeText(AddObatHewan.this, "add Berhasil", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddObatHewan.this,ListAdminObat.class));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    load.dismiss();
                    Toast.makeText(AddObatHewan.this, "error", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            load.dismiss();
            Toast.makeText(this, "Data belum Lengkap", Toast.LENGTH_SHORT).show();
        }

    }
    public boolean isValidData(String nama,String harga,String des, String obat){
        Boolean valid = true;
        if (nama.isEmpty() || harga.isEmpty() || des.isEmpty()){
            Toast.makeText(this, "Data belum Lengkap", Toast.LENGTH_SHORT).show();
            return false;
        }
        return valid;
    }

    public void uploadImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), filePath);
                imgObat.setImageBitmap(bitmap);
                ob.setGambar(filePath.toString());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
