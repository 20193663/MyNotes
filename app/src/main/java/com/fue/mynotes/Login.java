package com.fue.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login  extends AppCompatActivity {
    Button LoginBTN, SignupBTN;
    EditText emailEditText, passwordEditText;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginBTN = findViewById(R.id.login_btn);
        SignupBTN = findViewById(R.id.signup_btn);
        emailEditText = findViewById(R.id.username_et);
        passwordEditText = findViewById(R.id.password_et);
        progressBar = findViewById(R.id.progress_bar);
        LoginBTN.setOnClickListener(v -> login());
        SignupBTN.setOnClickListener(v -> startActivity(new Intent(Login.this, Signup.class)));
    }
    void login(){
            String email  = emailEditText.getText().toString();
            String password  = passwordEditText.getText().toString();

            boolean isValidated = validateData(email,password);
            if(!isValidated){
                return;
            }
        loginAccountInFirebase(email,password);
        }
    void loginAccountInFirebase(String email,String password){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        changeInProgress(true);
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            changeInProgress(false);
            if(task.isSuccessful()){
                //login is success
                    //go to mainactivity
                    startActivity(new Intent(Login.this,MainActivity.class));
                    finish();
            }else{
                //login failed
                Toast.makeText(Login.this,task.getException().getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    void changeInProgress(boolean inProgress){
        if(inProgress){
            progressBar.setVisibility(View.VISIBLE);
            SignupBTN.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            SignupBTN.setVisibility(View.VISIBLE);
        }
    }

    boolean validateData(String email,String password){
        //validate the data that are input by user.
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Email is invalid");
            return false;
        }
        if(password.length()<6){
            passwordEditText.setError("Password length is invalid");
            return false;
        }
        return true;
    }

}
