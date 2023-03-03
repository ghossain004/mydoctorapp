package com.andorid.mydoctorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DoctorRegistrationActivity extends AppCompatActivity {

    EditText edDoctorName, edEmail, edAddress;
    Button btnSubmit;
    TextView tvTitle, tvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration);

        edDoctorName = findViewById(R.id.editTextDoctorName);
        edEmail = findViewById(R.id.editTextDcotorEmail);
        edAddress = findViewById(R.id.editTextDoctorAddress);
        btnSubmit = findViewById(R.id.buttonDoctorSubmit);
        tvBack = findViewById(R.id.textViewBack);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String doctorName =  edDoctorName.getText().toString();
                String doctorEmail = edEmail.getText().toString();
                String doctorAddress = edAddress.getText().toString();

                Doctor dc = new Doctor();
                dc.setDoctorName(doctorName);
                dc.setEmail(doctorEmail);
                dc.setAddress(doctorAddress);

                DataTransfer dt = new DataTransfer(getApplicationContext(), "healthcare", null, 1);
                if (doctorName.length()==0 || doctorEmail.length()==0 || doctorAddress.length()==0){
                    Toast.makeText(getApplicationContext(), "Please Fill All The Field", Toast.LENGTH_SHORT).show();
                }else {
                    dt.addDoctor(dc);
                    startActivity(new Intent(DoctorDetailsActivity.this, HomeActivity.class));
                    Toast.makeText(getApplicationContext(), "Doctor Created", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, HomeActivity.class));
            }
        });
    }
}