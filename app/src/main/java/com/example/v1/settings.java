package com.example.v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class settings extends AppCompatActivity {
public Button btncal2;
    public Button btnbmi;
    public Button btnsteps2;
    public Button Logout,resetPass;
    TextView nametxt4;
    FirebaseAuth mAuth;

    public void init(){
        btncal2= (Button)findViewById(R.id.btncal5);
        btncal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calclick = new Intent(settings.this, MainActivity.class);
                startActivity(calclick);
            }
        });
    }
    public void init2(){
        btnsteps2= (Button)findViewById(R.id.btnsteps3);
        btnsteps2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stepclick = new Intent(settings.this, steps.class);
                startActivity(stepclick);
                finish();
            }
        });
    }
    public void init3(){
        btnbmi= (Button)findViewById(R.id.btnbmi3);
        btnbmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bmiclick = new Intent(settings.this, bmi.class);
                startActivity(bmiclick);
                finish();
            }
        });
    }
    public void user(){
        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        nametxt4=findViewById(R.id.nametxt4);
        if(user!=null){
            String Email = user.getEmail();
            nametxt4.setText("Signed in as: "+Email);}

        else{


        }
    }
    public void init4(){
        Logout= (Button)findViewById(R.id.Logout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(settings.this,homescreen.class));

            }
        });
    }

    public void resetpass(FirebaseUser user) {
        {

            EditText passIn = findViewById(R.id.resetpassin);
            String newPass = passIn.getText().toString().trim();
            if (newPass.isEmpty()) {
                passIn.setError("Password is required");
                passIn.requestFocus();
                return;
            }
            if (passIn.length() < 6) {
                passIn.setError("Password must be longer than 6 characters.");
                passIn.requestFocus();
                return;
            }

            user.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Password Changed", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Password Error", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });
        }
    }
        public void init5() {
            resetPass = (Button) findViewById(R.id.resetPass);
            resetPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseUser user = mAuth.getInstance().getCurrentUser();
                    resetpass(user);

                }
            });
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_settings);
        init();
        init2();
        init3();
        init4();
        init5();
        user();


    }


}