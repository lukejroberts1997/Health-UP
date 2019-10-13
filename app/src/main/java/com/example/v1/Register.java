package com.example.v1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;


public class Register extends AppCompatActivity implements View.OnClickListener {
    EditText user, name2, Weight, Height;
    Button regbtn, loginbutton;
    EditText emailtext, passwordtext;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_register);
        emailtext = (EditText) findViewById(R.id.emailtext);
        name2 = findViewById(R.id.name2);
        user = findViewById(R.id.user);
        passwordtext = (EditText) findViewById(R.id.passwordtext);
        Weight = findViewById(R.id.Weight);
        Height = findViewById(R.id.Height);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.regbtn).setOnClickListener(this);
        loginbutton = findViewById(R.id.regbtn);

    }

    private void registerUser() {
        String username = user.getText().toString().trim();
        String yourname = name2.getText().toString().trim();
        String youremail = emailtext.getText().toString().trim();
        String password = passwordtext.getText().toString().trim();
        String weight = Weight.getText().toString().trim();
        String height = Height.getText().toString().trim();

        if (youremail.isEmpty()) {
            emailtext.setError("Email is required");
            emailtext.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(youremail).matches()) {
            emailtext.setError("Please enter a valid Email Address");
        }

        if (password.isEmpty()) {
            passwordtext.setError("Password is required");
            passwordtext.requestFocus();
            return;
        }
        if (password.length() < 6) {
            passwordtext.setError("Password must be longer than 6 characters.");
            passwordtext.requestFocus();
            return;
        }

        if (username.isEmpty()) {
            user.setError("Username is required");
            user.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(youremail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(Register.this, MainActivity.class));
                } else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.regbtn:
                registerUser();
                break;


        }
    }
}

