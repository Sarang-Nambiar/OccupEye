package com.example.occupeye;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    Button login;
    Button signin;
    EditText username;
    EditText password;
    boolean password_status;
    boolean username_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //INITIALLIZING THE VARIABLES
        login=findViewById(R.id.loggin);
        signin=findViewById(R.id.signup);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        password_status=false;
        username_status=false;


        //
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {
                String username_tag=username.getText().toString();
                if(!b&&username_tag.length()<6){
                    Toast.makeText(Login.this,"Invalid Username",Toast.LENGTH_SHORT).show();
                    username_status=false;
                } else if (!b&&username_tag.contains(" ")) {

                    Toast.makeText(Login.this,"Username Cannot Have Space",Toast.LENGTH_SHORT).show();
                    username_status=false;
                }else if(!b){
                    username_status=true;
                }
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {
                String password_tag=password.getText().toString();
                if(!b && password_tag.length()<6){
                    Toast.makeText(Login.this,"Invalid Password",Toast.LENGTH_SHORT).show();
                    password_status=false;
                } else if (!b&& password_tag.contains(" ")) {
                    Toast.makeText(Login.this,"Password Cannot Have Space",Toast.LENGTH_SHORT).show();
                    password_status=false;
                } else if (!b) {
                    password_status=true;
                }
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(username_status && password_status){
                    Intent intent=new Intent(Login.this, Home.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(Login.this,"Invalid Entries",Toast.LENGTH_SHORT).show();
                }
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO intent for the register page
            }
        });
    }
}