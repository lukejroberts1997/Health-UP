package com.example.v1;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class steps extends AppCompatActivity implements SensorEventListener {
   public Button btnbmi2;
    FirebaseAuth mAuth;
   public Button btnset2;
   public Button btncal3,resetsteps;
   public TextView stepdisplay, nametxt3;
   public SensorManager sensorManager;
   private float steps;
   private float stepsorig;
   private boolean reset;
   public Boolean running = false;
    public void init(){
        btncal3= (Button)findViewById(R.id.btncal5);
        btncal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calclick = new Intent(steps.this, MainActivity.class);
                startActivity(calclick);

            }
        });
    }
    public void init2(){
        btnset2= (Button)findViewById(R.id.btnset3);
        btnset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stepclick = new Intent(steps.this, settings.class);
                startActivity(stepclick);
                finish();
            }
        });
    }
    public void init3() {
        btnbmi2 = (Button) findViewById(R.id.btnbmi3);
        btnbmi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bmiclick = new Intent(steps.this, bmi.class);
                startActivity(bmiclick);
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_steps);
            init();
            init2();
            init3();
        user();
       // reset();
            stepdisplay = (TextView) findViewById(R.id.stepdisplay);
            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    public void user(){
        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        nametxt3=findViewById(R.id.nametxt3);
        if(user!=null){
            String Email = user.getEmail();
            nametxt3.setText("Signed in as: "+Email);}

        else{


        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor !=null){
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_FASTEST);


        } else {
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show();
        }
    }
    protected void onPause() {
        super.onPause();
        running = false;

    }
/*    public void reset() {
        resetsteps = (Button) findViewById(R.id.resetsteps);
        resetsteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            stepsorig = steps;
            steps=0;
            reset=true;

            }
        });
    }*/
    public void onSensorChanged(SensorEvent event){
        if(running){
                steps = event.values[0];
                stepdisplay.setText(String.valueOf(steps));
            }

        }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
