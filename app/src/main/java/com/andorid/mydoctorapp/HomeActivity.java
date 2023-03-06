package com.andorid.mydoctorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    TextView tvDoctorRegistration, tvLogout, tvFindDoctor, tvPatientRegistration, tvFindPatient;
    CardView cvAddDoctor, cvFindDoctor, cvAddPatient, cvFindPatient, cvLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvDoctorRegistration = findViewById(R.id.homeDoctorRegistration);
        tvFindDoctor = findViewById(R.id.homeFindDoctor);
        tvPatientRegistration = findViewById(R.id.homeAddPatient);
        tvFindPatient = findViewById(R.id.homeFindPatient);
        tvLogout = findViewById(R.id.homeLogout);
        cvAddDoctor = findViewById(R.id.cardAddDoctor);
        cvFindDoctor = findViewById(R.id.cardFindDoctor);
        cvAddPatient = findViewById(R.id.cardAddPatient);
        cvFindPatient = findViewById(R.id.cardFindPatient);
        cvLogout = findViewById(R.id.cardLogout);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString("userName","").toString();
//        Toast.makeText(getApplicationContext(),"Welcome "+userName+"!",Toast.LENGTH_SHORT).show();

//        tvDoctorRegistration.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(HomeActivity.this, DoctorRegistrationActivity.class));
//            }
//        });

        cvAddDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, DoctorRegistrationActivity.class));
            }
        });

//        tvFindDoctor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(HomeActivity.this, FindDoctorActivity.class));
//            }
//        });

        cvFindDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, FindDoctorActivity.class));
            }
        });

//        tvPatientRegistration.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(HomeActivity.this, PatientRegistrationActivity.class));
//            }
//        });

        cvAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PatientRegistrationActivity.class));
            }
        });

//        tvFindPatient.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(HomeActivity.this, FindPatientActivity.class));
//            }
//        });

        cvFindPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, FindPatientActivity.class));
            }
        });

//        tvLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.clear();
//                editor.apply();
//                startActivity(new Intent(HomeActivity.this, MainActivity.class));
//                Toast.makeText(getApplicationContext(),"Logout from "+userName+"!",Toast.LENGTH_SHORT).show();
//            }
//        });

        cvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                editor.commit();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                Toast.makeText(getApplicationContext(),"Logout from "+userName+"!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}