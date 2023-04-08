package com.example.occupeye;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
import com.google.android.material.button.MaterialButton;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class EditPage extends AppCompatActivity {

    ImageView editpfp;

    MaterialButton backbtn;
    MaterialButton savebtn;
    EditText username;
    EditText term;
    EditText pillar;
    EditText hostelBlock;
    EditText hostelResident;

    public Bitmap getBitMap(Uri uri) throws FileNotFoundException, IOException {


        InputStream input = this.getContentResolver().openInputStream(uri);

        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither=true;//optional
        onlyBoundsOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();

        if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1)) {
            return null;
        }

        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;

        double ratio = (originalSize > 256) ? (originalSize / 256) : 1.0;

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inDither = true; //optional
        bitmapOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//
        input = this.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();
        return bitmap;
    }

    private static int getPowerOfTwoForSampleRatio(double ratio){
        int k = Integer.highestOneBit((int)Math.floor(ratio));
        if(k==0) return 1;
        else return k;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_page);

        backbtn = findViewById(R.id.backbtn);
        editpfp = findViewById(R.id.edit_profile_image);
        savebtn = findViewById(R.id.savebtn);
        username = findViewById(R.id.editnametxt);
        pillar = findViewById(R.id.editpillartxt);
        hostelBlock = findViewById(R.id.editblocktxt);
        hostelResident = findViewById(R.id.editresidenttxt);
        term = findViewById(R.id.edittermtxt);

        final ActivityResultLauncher<Intent> launcherGallery = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if( result.getResultCode() == Activity.RESULT_OK
                                && result.getData() != null){
                            Uri photoUri = result.getData().getData();
                            editpfp.setImageURI(photoUri);
                            try {
                                ImageStorage.saveInternalStorage(EditPage.this, getBitMap(photoUri), "pfp");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }


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
                if(Term.isEmpty() || Pillar.isEmpty() ||Username.isEmpty() ||HostelBlock.isEmpty() ||HostelResident.isEmpty()){
                    Toast.makeText(EditPage.this, "One or many of these fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                onBackPressed();
                finish();
            }
        });


    }
}