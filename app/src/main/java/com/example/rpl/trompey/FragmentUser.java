package com.example.rpl.trompey;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;

import javax.microedition.khronos.opengles.GL;

import static android.app.Activity.RESULT_OK;


public class FragmentUser extends Fragment implements View.OnClickListener {
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;
    DatabaseReference db;

    EditText mName,mEmail,mphone,maddress;
    Button msignOut,btnEdit;
    CircleImageView profile;

    public  String image="";

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;

    private  View v;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        v =  lf.inflate(R.layout.fragment_user, container, false);

        mName = v.findViewById(R.id.editText);
        mEmail = v.findViewById(R.id.editText2);
        mphone = v.findViewById(R.id.editText3);
        maddress = v.findViewById(R.id.editText4);
        profile = v.findViewById(R.id.imageView2);
        btnEdit = v.findViewById(R.id.button2);
        msignOut = v.findViewById(R.id.button3);


        profile.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        msignOut.setOnClickListener(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();



        db = FirebaseDatabase.getInstance().getReference("Users");
        Query query = db.orderByChild("uid").equalTo(mFirebaseUser.getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String imageProfile ="";
                for (DataSnapshot ds : dataSnapshot.getChildren()){

                    String name = ds.child("name").getValue().toString();
                    String email = ds.child("email").getValue().toString();
                    String phone = ds.child("phone").getValue().toString();
                    String address = ds.child("address").getValue().toString();
                    imageProfile = ds.child("imageProfile").getValue().toString();
                    mName.setText(name);
                    mEmail.setText(email);
                    mphone.setText(phone);
                    maddress.setText(address);
                }
                if (imageProfile.equals("")){
                    Glide.with(getActivity()).load(R.drawable.cowo).into(profile);
                }else {
                    setImage(imageProfile);
                    Glide.with(getActivity()).load(imageProfile).into(profile);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return v;
    }

    @Override
    public void onClick(View v) {

       switch (v.getId()){
           case R.id.button3:
               signOut();
               break;
           case  R.id.button2:

               editProfile();
               break;
           case R.id.imageView2:
               getPhoto();
               break;
       }
    }

    private void editProfile() {

        if (getImage().equals("")){
            setImage("");
        }
        HashMap<Object, String> hashMap = new HashMap<>();

        hashMap.put("email",mEmail.getText().toString());
        hashMap.put("uid", mFirebaseUser.getUid());
        hashMap.put("name",mName.getText().toString());
        hashMap.put("phone",mphone.getText().toString());
        hashMap.put("address",maddress.getText().toString());
        hashMap.put("imageProfile",getImage());

        db.child(mFirebaseUser.getUid()).setValue(hashMap);

        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
    }

    private void getPhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                profile.setImageBitmap(bitmap);
                setPhoto(filePath);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void setPhoto(Uri uri) {
        //Firebase
        Uri filepath = uri;
        FirebaseStorage storage;
        StorageReference storageReference;

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle(null);
            progressDialog.setMessage("Wait...");
            progressDialog.show();
            String filename = "images/profile_"+mFirebaseUser.getEmail();
            StorageReference ref = storageReference.child(filename);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Task<Uri> uriimg = taskSnapshot.getStorage().getDownloadUrl();
                            while (!uriimg.isSuccessful());
                            Uri donwload = uriimg.getResult();
                            setImage(donwload.toString());

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
    private void signOut() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getActivity().getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
        mGoogleSignInClient.signOut();

        mFirebaseAuth.getInstance().signOut();

        SharedPreferences datalogin = getActivity().getSharedPreferences("UserLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor clearData = datalogin.edit();
        clearData.remove("email");
        clearData.remove("uid");
        clearData.remove("token");
        clearData.apply();

        Intent login = new Intent(getActivity(),LoginActivity.class);
        startActivity(login);
        Toast.makeText(getActivity(),"Logout",Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

}