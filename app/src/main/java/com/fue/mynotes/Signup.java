package com.fue.mynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup  extends AppCompatActivity {
    Button LoginBTN,SignupBTN;
    EditText emailEditText,passwordEditText,confirmPasswordEditText;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        LoginBTN  =  findViewById(R.id.login_btn);
        SignupBTN = findViewById(R.id.signup_btn);
        emailEditText = findViewById(R.id.username_et);
        passwordEditText = findViewById(R.id.password_et);
        confirmPasswordEditText = findViewById(R.id.comfirm_password_et);
        progressBar = findViewById(R.id.progress_bar);
        SignupBTN.setOnClickListener(v -> createAccount());
        LoginBTN.setOnClickListener(v -> finish());
    }

    void createAccount(){
        String email  = emailEditText.getText().toString();
        String password  = passwordEditText.getText().toString();
        String confirmPassword  = confirmPasswordEditText.getText().toString();

        boolean isValidated = validateData(email,password,confirmPassword);
        if(!isValidated){
            return;
        }

        createAccountInFirebase(email,password);


    }

    void createAccountInFirebase(String email,String password){
        changeInProgress(true);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Signup.this,
                task -> {
                    changeInProgress(false);
                    if(task.isSuccessful()){
                        //creating acc is done
                        Toast.makeText(Signup.this,"Successfully create account,Check email to verify",Toast.LENGTH_LONG).show();
                        firebaseAuth.getCurrentUser().sendEmailVerification();
                        firebaseAuth.signOut();
                        finish();
                    }else{
                        //failure
                        Toast.makeText(Signup.this,task.getException().getLocalizedMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        );



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

    boolean validateData(String email,String password,String confirmPassword){
        //validate the data that are input by user.

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Email is invalid");
            return false;
        }
        if(password.length()<6){
            passwordEditText.setError("Password length is invalid");
            return false;
        }
        if(!password.equals(confirmPassword)){
            confirmPasswordEditText.setError("Password not matched");
            return false;
        }
        return true;
    }

}

