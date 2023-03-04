package com.andorid.mydoctorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.andorid.mydoctorapp.database.Database;
import com.andorid.mydoctorapp.entity.Doctor;

public class DoctorRegistrationActivity extends AppCompatActivity {

    EditText edDoctorName, edBmaRegistration, edSpeciality, edEmail, edMobile, edAge;
    Button btnSignup;
    TextView tvBackLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration);

        edDoctorName = findViewById(R.id.doctorRegistrationFullName);
        edBmaRegistration = findViewById(R.id.doctorRegistrationBMA);
        edSpeciality = findViewById(R.id.doctorRegistrationSpeciality);
        edEmail = findViewById(R.id.doctorRegistrationEmail);
        edMobile = findViewById(R.id.doctorRegistrationMobile);
        edAge = findViewById(R.id.doctorRegistrationAge);
        btnSignup = findViewById(R.id.buttonDoctorSignup);
        tvBackLogin = findViewById(R.id.doctorRegistrationHome);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String doctorName = edDoctorName.getText().toString();
                String bmaRegistration = edBmaRegistration.getText().toString();
                String speciality = edSpeciality.getText().toString();
                String email = edEmail.getText().toString();
                String mobile = edMobile.getText().toString();
                String age = edAge.getText().toString();

                Doctor dc = new Doctor();
                dc.setDoctorName(doctorName);
                dc.setRegistrationNumber(bmaRegistration);
                dc.setSpeciality(speciality);
                dc.setEmail(email);
                dc.setMobile(mobile);
                dc.setAge(age);

                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                if (doctorName.length()==0 || bmaRegistration.length()==0 || speciality.length()==0 || email.length()==0 || mobile.length()==0 || age.length()==0){
                    Toast.makeText(getApplicationContext(), "Please Fill All The Field", Toast.LENGTH_SHORT).show();
                }else {
                    db.addDoctor(dc);
                    startActivity(new Intent(DoctorRegistrationActivity.this, HomeActivity.class));
                    Toast.makeText(getApplicationContext(), "Doctor Created", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorRegistrationActivity.this, HomeActivity.class));
            }
        });
    }
}