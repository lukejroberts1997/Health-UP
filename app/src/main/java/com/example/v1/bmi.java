package com.example.v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DecimalFormat;

public class bmi extends AppCompatActivity {
    public Button btncal;
    FirebaseAuth mAuth;
    public Button btnsteps;
    public EditText heighttxt, weighttxt;
    public Button btnset;
    public Button gobtn;
    public boolean isCm=true;
    public boolean isKg=true;
    public RadioButton inches,kgs,cm,pounds;
    public TextView bmiDisplay, txtclass,getusertxt;

    public void init(){
        btncal= (Button)findViewById(R.id.btncal5);
        btncal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calclick = new Intent(bmi.this, MainActivity.class);
                startActivity(calclick);
            }
        });
    }
    public void init2(){
        btnsteps= (Button)findViewById(R.id.btnsteps3);
        btnsteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stepclick = new Intent(bmi.this, steps.class);
                startActivity(stepclick);
                finish();
            }
        });
    }
    public void init3(){
        btnset= (Button)findViewById(R.id.btnset3);
        btnset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setclick = new Intent(bmi.this, settings.class);
                startActivity(setclick);
                finish();
            }
        });
    }
    public void init4(){
        inches=(RadioButton)findViewById(R.id.inches);
        inches.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
            isCm=false;
        }
    });}
    public void init5(){
        kgs=(RadioButton)findViewById(R.id.kgs);
        kgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isKg=true;
            }
        });}
    public void init6(){
        cm=(RadioButton)findViewById(R.id.cm);
        cm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCm=true;
            }
        });}
    public void init7(){
        pounds=(RadioButton)findViewById(R.id.pounds);
        pounds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isKg=false;
            }
        });}
    public void init8(){
        gobtn=(Button)findViewById(R.id.gobtn);
        gobtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
            canBmi();
            }
        });
    }

    public void canBmi(){
        DecimalFormat df = new DecimalFormat( "#.##");

        txtclass = (TextView)findViewById(R.id.txtclass);
        bmiDisplay=(TextView)findViewById(R.id.bmidisplay);
        bmiDisplay.setText("");
        if (isCm == true && isKg == true){
            bmiDisplay.setText(df.format(new Double(calculatebmi())).toString());
            txtclass.setText(classBounds(new Double(calculatebmi())));
        }
        else if(isCm ==false && isKg == false){
            bmiDisplay.setText(df.format(new Double(Imperial())).toString());
            txtclass.setText(classBounds(new Double(Imperial())));

        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),"Please choose either kg/cm or lbs/inches",Toast.LENGTH_SHORT);
            toast.show();
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_bmi);
        init();
        init2();
        init3();
        init4();
        init5();
        init6();
        init7();
        init8();
        user();
       // canBmi();
    }

    public void user(){
        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        getusertxt=findViewById(R.id.getusertxt);
        if(user!=null){
            String Email = user.getEmail();
            getusertxt.setText("Signed in as: "+Email);}

        else{


        }
    }

    public double calculatebmi(){
        DecimalFormat nD = new DecimalFormat("#");
        float height;
        float weight;
        heighttxt=(EditText)findViewById(R.id.heighttxt);
        weighttxt=(EditText)findViewById(R.id.weighttxt);
        height = Float.parseFloat (heighttxt.getText().toString());
        weight = Float.parseFloat (weighttxt.getText().toString());
        double bmi = (weight /(Math.pow(height,2))*10000);
        return bmi;
    }
    public double Imperial(){
        float height;
        float weight;
        heighttxt=(EditText)findViewById(R.id.heighttxt);
        weighttxt=(EditText)findViewById(R.id.weighttxt);
        height = Float.parseFloat (heighttxt.getText().toString());
        weight = Float.parseFloat (weighttxt.getText().toString());
        double bmi = (weight /(Math.pow(height,2))*703);
        return bmi;
    }


    public String classBounds (double bmi){
        String bound= "";
        if(bmi <18.5) bound="Under Weight";
        if(bmi >=18.5 && bmi < 24.9) bound="Healthy Weight";
        if(bmi >=30 && bmi < 34.9) bound="~Over Weight";
        if(bmi >=34.9) bound="Obese";
        return bound;
    }
}


