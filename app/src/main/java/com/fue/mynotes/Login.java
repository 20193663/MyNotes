package com.fue.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Login  extends AppCompatActivity {
    Button login,signup;
    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login  =  findViewById(R.id.login_btn);
        signup = findViewById(R.id.signup_btn);
        email = findViewById(R.id.username_et);
        password = findViewById(R.id.password_et);
        login.setOnClickListener(v -> {
            // Your onClick implementation here.
            Intent myIntent = new Intent(Login.this, note.class);
            //myIntent.putExtra("key", value); //Optional parameters
            startActivity(myIntent);
        });
        signup.setOnClickListener(v -> {
            // Your onClick implementation here.
            Intent myIntent = new Intent(Login.this, Signup.class);
            //myIntent.putExtra("key", value); //Optional parameters
            startActivity(myIntent);
        });
    }
}