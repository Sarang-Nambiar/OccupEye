package com.example.occupeye;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    Button block55;
    Button block57;
    Button block59;


    boolean block55_sel;
    boolean block57_sel;
    boolean block59_sel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        block55_sel=false;
        block59_sel=false;
        block57_sel=false;

        block57=findViewById(R.id.button57);
        block55=findViewById(R.id.button55);
        block59=findViewById(R.id.button59);

        block55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(block55_sel){
                    block55.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    block55.setTextColor(Color.parseColor("#E3655B"));
                    block55_sel=false;
                    return;
                }
                if(block59_sel || block57_sel){
                    return;
                }
                block55_sel=true;
                block55.setBackgroundColor(Color.parseColor("#E3655B"));
                block55.setTextColor(Color.parseColor("#FFFFFFFF"));
            }
        });
        block57.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(block57_sel){
                    block57.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    block57.setTextColor(Color.parseColor("#E3655B"));
                    block57_sel=false;
                    return;
                }
                if(block59_sel || block55_sel){
                    return;
                }
                block57_sel=true;
                block57.setBackgroundColor(Color.parseColor("#E3655B"));
                block57.setTextColor(Color.parseColor("#FFFFFFFF"));
            }
        });
        block59.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if(block59_sel){
                    block59.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    block59.setTextColor(Color.parseColor("#E3655B"));
                    block59_sel=false;
                    return;
                }
                if(block55_sel || block57_sel){
                    return;
                }
                block59_sel=true;
                block59.setBackgroundColor(Color.parseColor("#E3655B"));
                block59.setTextColor(Color.parseColor("#FFFFFFFF"));
            }
        });

        
    }
}
