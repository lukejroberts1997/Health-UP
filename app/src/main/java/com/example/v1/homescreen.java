package com.example.v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class homescreen extends AppCompatActivity {
public Button Loginbtn;
public Button registerbtn;


public void init(){
    Loginbtn= (Button)findViewById(R.id.Loginbtn);
    Loginbtn.setOnClickListener(new View.OnClickListener() {
        @Override
      public void onClick(View v) {
            Intent loginclick = new Intent(homescreen.this, login.class);
            startActivity(loginclick);
        }
    });
}

    public void init2(){
        registerbtn= (Button)findViewById(R.id.registerbtn);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Regclick = new Intent(homescreen.this, Register.class);
                startActivity(Regclick);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_homescreen);
        init();
        init2();

    }
}
