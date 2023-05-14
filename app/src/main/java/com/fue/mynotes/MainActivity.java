package com.fue.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button login,signup;
    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login  =  findViewById(R.id.LoginButton);
        signup = findViewById(R.id.SignupButton);
        email = findViewById(R.id.EmailAddress);
        password = findViewById(R.id.password);
        login.setOnClickListener(v -> {
            // Your onClick implementation here.
            Intent myIntent = new Intent(MainActivity.this, Signup.class);
            //myIntent.putExtra("key", value); //Optional parameters
            MainActivity.this.startActivity(myIntent);
        });
        signup.setOnClickListener(v -> {
            // Your onClick implementation here.
            Intent myIntent = new Intent(MainActivity.this, Signup.class);
            //myIntent.putExtra("key", value); //Optional parameters
            MainActivity.this.startActivity(myIntent);
        });
    }
}