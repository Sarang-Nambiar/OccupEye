package com.example.occupeye;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.occupeye.Fragments.UserFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class EditPage extends AppCompatActivity {
    FirebaseFirestore db;
    StorageReference storageReference;
    StorageReference profileRef;
    ImageView editpfp;
    Uri pfpUri;
    MaterialButton backbtn;
    MaterialButton savebtn;
    EditText username;
    EditText term;
    EditText pillar;
    EditText hostelBlock;
    EditText hostelResident;


    private static int getPowerOfTwoForSampleRatio(double ratio){
        int k = Integer.highestOneBit((int)Math.floor(ratio));
        if(k==0) return 1;
        else return k;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_page);

        Intent data = getIntent();
        String name = data.getStringExtra("username");
        String Pillar = data.getStringExtra("pillar");
        String Term = data.getStringExtra("term");
        String HostelBlock = data.getStringExtra("hostel block");
        String HostelResident = data.getStringExtra("hostel resident");
        db = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        profileRef = storageReference.child("Users/"+"admin"+"/profile.jpg");

        backbtn = findViewById(R.id.backbtn);
        editpfp = findViewById(R.id.edit_profile_image);
        savebtn = findViewById(R.id.savebtn);
        username = findViewById(R.id.editnametxt);
        pillar = findViewById(R.id.editpillartxt);
        hostelBlock = findViewById(R.id.editblocktxt);
        hostelResident = findViewById(R.id.editresidenttxt);
        term = findViewById(R.id.edittermtxt);

        username.setText(name);
        pillar.setText(Pillar);
        hostelBlock.setText(HostelBlock);
        hostelResident.setText(HostelResident);
        term.setText(Term);

        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(editpfp);
            }
        });
        final ActivityResultLauncher<Intent> launcherGallery = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if( result.getResultCode() == Activity.RESULT_OK
                                && result.getData() != null){
                            Uri photoUri = result.getData().getData();
                            editpfp.setImageURI(photoUri);
                            pfpUri = photoUri;
                        }
                    }
                }
        );
        editpfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                launcherGallery.launch(intent);
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username = username.getText().toString();
                String Pillar = pillar.getText().toString();
                String Term = term.getText().toString();
                String HostelBlock = hostelBlock.getText().toString();
                String HostelResident = hostelResident.getText().toString();
                Map<String, Object> user = new HashMap<>();
                user.put("Name", Username);
                user.put("Pillar", Pillar);
                user.put("Term", Term);
                user.put("Hostel Block", HostelBlock);
                user.put("Hostel Resident", HostelResident);

                if(Term.isEmpty() || Pillar.isEmpty() ||Username.isEmpty() ||HostelBlock.isEmpty() ||HostelResident.isEmpty()){
                    Toast.makeText(EditPage.this, "One or many of these fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                //firestore storage part
                DocumentReference docRef = db.collection("Users").document("admin");
                docRef.update(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        if(pfpUri != null){
                            uploadImageToFirebase(pfpUri);
                        }
                        Toast.makeText(EditPage.this, "Profile updated", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                        finish();
                    }
                });


            }
        });


    }

    private void uploadImageToFirebase(Uri imageUri) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Set your profile");
        progressDialog.setMessage("Please wait while we are updating your data");
        progressDialog.show();
        // uploading image to firebase storage
        StorageReference fileRef = storageReference.child("Users/"+"admin"+"/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(EditPage.this, "Failed to upload the image", Toast.LENGTH_SHORT).show();
            }
        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                progressDialog.dismiss();
                Toast.makeText(EditPage.this, "Image has been updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}