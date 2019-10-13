package com.example.v1;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.NullPointerException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    public Button btnbmi3;
    public Button btnset2;
    public Button btnsteps3;
    public Button addbtn;
    public Button resetbtn;
    public EditText Caltxt;
    public boolean malepush=true;
    int total;
    String fromicon;
    private int dailymale = 2500;
    private int dailyfemale = 2000;
    public RadioButton female,male;
    public TextView texttochange,dailytext,nametxt;


    public void user(){
        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        nametxt=findViewById(R.id.nametxt);
        if(user!=null){
            String Email = user.getEmail();
            nametxt.setText("Signed in as: "+Email);}

            else{


            }
        }

    public void malefalse(){
        male=(RadioButton)findViewById(R.id.male);
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                malepush=true;
                dailytext.setText(Integer.toString(dailymale));
                total=0;
                texttochange.setText(Integer.toString(total));
            }
        });}
    public void maletrue(){
        female=(RadioButton)findViewById(R.id.female);
        female.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                malepush=false;
                dailytext.setText(Integer.toString(dailyfemale));
                total=0;
                texttochange.setText(Integer.toString(total));
            }
        });
    }


    public void daily(){
        dailytext = (TextView) findViewById(R.id.dailytext);

        if(malepush){
            dailytext.setText(Integer.toString(dailymale));
        }



    }





    public void init() {
        btnset2 = (Button) findViewById(R.id.btnset3);
        btnset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calclick = new Intent(MainActivity.this, settings.class);
                startActivity(calclick);
                String stateSaved;

            }
        });
    }

    public void init2() {
        btnsteps3 = (Button) findViewById(R.id.btnsteps3);
        btnsteps3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stepclick = new Intent(MainActivity.this, steps.class);
                startActivity(stepclick);
                String stateSaved;
            }
        });
    }

    public void init3() {
        btnbmi3 = (Button) findViewById(R.id.btnbmi3);
        btnbmi3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bmiclick = new Intent(MainActivity.this, bmi.class);
                startActivity(bmiclick);
                String stateSaved;
            }
        });
    }

    public void init4() {
        addbtn = (Button) findViewById(R.id.addbtn);
        Caltxt = (EditText) findViewById(R.id.Caltxt);
        texttochange = (TextView) findViewById(R.id.texttochange);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Caltxt.getText().toString().isEmpty()) {
                    int cal = Integer.parseInt(Caltxt.getText().toString());
                    total = cal + total;
                    texttochange.setText(Integer.toString(total));
                    init6();
                }
            }

        });
    }
    public void init6(){
        int take = dailymale-total;
        dailytext.setText(Integer.toString(take));
    }

    public void reset() {
        resetbtn = (Button) findViewById(R.id.resetbtn);

        texttochange = (TextView) findViewById(R.id.texttochange);
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = 0;
                texttochange.setText(Integer.toString(total));
                init7();
            }

        });
    }
    public void init7(){
        dailytext.setText(Integer.toString(dailymale));
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_main);

        init();
        init2();
        init3();
        init4();
        reset();
        user();
        maletrue();
        malefalse();
        daily();

        addbtn = (Button) findViewById(R.id.addbtn);
/*      databaseReference = FirebaseDatabase.getInstance().getReference();
        dailytext =(TextView) findViewById(R.id.dailytext);
        texttochange =(TextView) findViewById(R.id.texttochange);
        addbtn.setOnClickListener(this);*/
/*
        String stateSaved
                = savedInstanceState.getString("");
        if (stateSaved==null) {
            Toast toast1 = Toast.makeText(MainActivity.this, "onRestoreInstanceState: No state saved!", Toast.LENGTH_LONG);
            toast1.show();
        } else{
            Toast toast2=Toast.makeText(MainActivity.this,"onRestoreInstanceState: saved state"+stateSaved,Toast.LENGTH_LONG);
            toast2.show();
            dailytext.setText(stateSaved);
            texttochange.setText(stateSaved);
        }
*/

    }
    private void saveUserInfo(){

    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String stateToSave = dailytext.getText().toString();
        outState.putString("saved_state",stateToSave);

        Toast toast3=Toast.makeText(MainActivity.this,"onSaveInstanceState: saved state"+stateToSave,Toast.LENGTH_LONG);
        toast3.show();

        String stateToSave2 = texttochange.getText().toString();
        outState.putString("saved_state",stateToSave2);

        Toast toast4=Toast.makeText(MainActivity.this,"onSaveInstanceState: saved state"+stateToSave2,Toast.LENGTH_LONG);
        toast4.show();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
            super.onRestoreInstanceState(savedInstanceState);

        String stateSaved = savedInstanceState.getString("save_state");
        if (stateSaved==null) {
            Toast toast1 = Toast.makeText(MainActivity.this, "onRestoreInstanceState: No state saved!", Toast.LENGTH_LONG);
            toast1.show();
        } else{
            Toast toast2=Toast.makeText(MainActivity.this,"onRestoreInstanceState: saved state"+stateSaved,Toast.LENGTH_LONG);
            toast2.show();
            dailytext.setText(stateSaved);
            texttochange.setText(stateSaved);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        String stateToSave = dailytext.getText().toString();
        String fuckoff = texttochange.getText().toString();
        getIntent().putExtra("saved_state",stateToSave);
        getIntent().putExtra("saved_state2",fuckoff);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Bundle a=intent.getExtras();
        String daily = intent.getStringExtra("saved_state");
        String daily2 = intent.getStringExtra("saved_state2");
        dailytext.setText(daily);
        texttochange.setText(daily2);

    }


    @Override
    public void onClick(View v) {

    }
}


