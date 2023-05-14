package com.fue.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Signup  extends AppCompatActivity {
    Button login,signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login  =  findViewById(R.id.LoginButton);
        signup = findViewById(R.id.SignupButton);
        //TODO delete this
        TextView t = findViewById(R.id.loginpagetitel);
        t.setText("Signup");
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
