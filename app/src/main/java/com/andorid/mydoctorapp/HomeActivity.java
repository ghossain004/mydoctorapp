package com.andorid.mydoctorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    Button btnDoctorRegistration, btnLogout, btnFindDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnDoctorRegistration = findViewById(R.id.homeDoctorRegistration);
        btnFindDoctor = findViewById(R.id.homeFindDoctor);
        btnLogout = findViewById(R.id.homeLogout);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString("userName","").toString();
//        Toast.makeText(getApplicationContext(),"Welcome "+userName+"!",Toast.LENGTH_SHORT).show();

        btnDoctorRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, DoctorRegistrationActivity.class));
            }
        });

        btnFindDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, FindDoctorActivity.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                Toast.makeText(getApplicationContext(),"Logout from "+userName+"!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}