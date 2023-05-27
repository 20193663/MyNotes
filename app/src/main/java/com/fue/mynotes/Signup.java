package com.fue.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Signup  extends AppCompatActivity {
    Button login,signup;
    EditText email,password,comfirm_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        login  =  findViewById(R.id.login_btn);
        signup = findViewById(R.id.signup_btn);
        email = findViewById(R.id.username_et);
        password = findViewById(R.id.password_et);
        comfirm_password = findViewById(R.id.comfirm_password_et);
        login.setOnClickListener(v -> {
            // Your onClick implementation here.
            Intent myIntent = new Intent(Signup.this, MainActivity.class);
            //myIntent.putExtra("key", value); //Optional parameters
            Signup.this.startActivity(myIntent);
        });
        signup.setOnClickListener(v -> {
            // Your onClick implementation here.
            Intent myIntent = new Intent(Signup.this, MainActivity.class);
            //myIntent.putExtra("key", value); //Optional parameters
            Signup.this.startActivity(myIntent);
        });
    }
}
