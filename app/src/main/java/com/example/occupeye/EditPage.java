package com.example.occupeye;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.occupeye.Fragments.UserFragment;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class EditPage extends AppCompatActivity {
    FirebaseFirestore db;
    StorageReference storageReference, profileRef;
    ImageView editpfp;
    Uri pfpUri;
    MaterialButton backbtn, savebtn;
    EditText username;
    AutoCompleteTextView autoCompleteterm, autoCompletepillar, autoCompleteblock, autoCompleteresident;
    ArrayAdapter<String> adapterItems1, adapterItems2, adapterItems3, adapterItems4;
    StorageTask uploadTask;
    String term = "";
    String pillar = "";
    String block = "";
    String resident = "";
    String[] terms = {"Term 1, Freshmore", "Term 2, Freshmore", "Term 3, Freshmore",
    "Term 4, Sophomore", "Term 5, Junior", "Term 6, Junior", "Term 7, Senior", "Term 8, Senior"};
    String[] pillars = {"ASD", "CSD", "DAI", "EPD", "ESD"};
    String[] blocks = {"55", "57", "59"};
    String[] residents = {"Yes", "No"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_page);

        Intent data = getIntent();
        String name = data.getStringExtra("username");
        db = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        profileRef = storageReference.child("Users/"+"admin"+"/profile.jpg");

        backbtn = findViewById(R.id.backbtn);
        editpfp = findViewById(R.id.edit_profile_image);
        savebtn = findViewById(R.id.savebtn);
        username = findViewById(R.id.editnametxt);
        autoCompleteterm = findViewById(R.id.autocompleteterm);
        autoCompletepillar = findViewById(R.id.autocompletepillar);
        autoCompleteblock = findViewById(R.id.autocompleteblock);
        autoCompleteresident = findViewById(R.id.autocompleteresident);

        username.setText(name);

        adapterItems1 = new ArrayAdapter<String>(this,R.layout.list_item, terms);
        autoCompleteterm.setAdapter(adapterItems1);

        adapterItems2 = new ArrayAdapter<String>(this, R.layout.list_item, pillars);
        autoCompletepillar.setAdapter(adapterItems2);

        adapterItems3 = new ArrayAdapter<String>(this, R.layout.list_item, blocks);
        autoCompleteblock.setAdapter(adapterItems3);

        adapterItems4 = new ArrayAdapter<String>(this, R.layout.list_item, residents);
        autoCompleteresident.setAdapter(adapterItems4);

        autoCompleteblock.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                block = item;
            }
        });
        autoCompleteresident.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                resident = item;
            }
        });
        autoCompletepillar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                pillar = item;
            }
        });
        autoCompleteterm.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                term = item;
            }
        });
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
                Map<String, Object> user = new HashMap<>();
                user.put("Name", Username);
                user.put("Hostel Resident", resident);
                user.put("Pillar", pillar);
                user.put("Term", term);
                user.put("Hostel Block", block);

                if(Username.isEmpty() || term.isEmpty() || pillar.isEmpty() || block.isEmpty() || resident.isEmpty()){
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
        progressDialog.setTitle("Uploading....");
        progressDialog.setMessage("Please wait while we are updating your data");
        progressDialog.show();
        // uploading image to firebase storage
        StorageReference fileRef = storageReference.child("Users/"+"admin"+"/profile.jpg");
        fileRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    Toast.makeText(EditPage.this, "Image uploaded", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(EditPage.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }
}