package com.andorid.mydoctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.andorid.mydoctorapp.database.Database;
import com.andorid.mydoctorapp.entity.Doctor;
import com.andorid.mydoctorapp.entity.Patient;

public class PatientRegistrationActivity extends AppCompatActivity {

    EditText edPatientName, edPatientRegistration, edSpeciality, edEmail, edMobile, edAge;
    Button btnSignup;
    TextView tvBackLogin;

    private Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration);

        edPatientName = findViewById(R.id.PatientRegistrationFullName);
        edPatientRegistration = findViewById(R.id.PatientRegistrationNumber);
        edSpeciality = findViewById(R.id.PatientRegistrationSpeciality);
        edEmail = findViewById(R.id.PatientRegistrationEmail);
        edMobile = findViewById(R.id.PatientRegistrationMobile);
        edAge = findViewById(R.id.PatientRegistrationAge);
        btnSignup = findViewById(R.id.buttonPatientSignup);
        tvBackLogin = findViewById(R.id.PatientRegistrationHome);

        Bundle data = getIntent().getExtras();

        if (data != null){
            id = data.getInt("id");
            String patientName = data.getString("patientName");
            String registrationNumber = data.getString("registrationNumber");
            String disease = data.getString("disease");
            String email = data.getString("email");
            String mobile = data.getString("mobile");
            String age = data.getString("age");

            edPatientName.setText(patientName);
            edPatientRegistration.setText(registrationNumber);
            edSpeciality.setText(disease);
            edEmail.setText(email);
            edMobile.setText(mobile);
            edAge.setText(age);
            btnSignup.setText("Update");
        }

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String patientName = edPatientName.getText().toString();
                String registrationNumber = edPatientRegistration.getText().toString();
                String disease = edSpeciality.getText().toString();
                String email = edEmail.getText().toString();
                String mobile = edMobile.getText().toString();
                String age = edAge.getText().toString();

                Patient pt = new Patient();
                pt.setPatientName(patientName);
                pt.setRegistrationNumber(registrationNumber);
                pt.setDisease(disease);
                pt.setEmail(email);
                pt.setMobile(mobile);
                pt.setAge(age);

                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                if (patientName.length()==0 || registrationNumber.length()==0 || disease.length()==0 || email.length()==0 || mobile.length()==0 || age.length()==0){
                    Toast.makeText(getApplicationContext(), "Please Fill All The Field", Toast.LENGTH_SHORT).show();
                }else {
                    if (id!=null){
                        System.out.println("update");
                        pt.setId(id);
                        db.updatePatient(pt);
                        startActivity(new Intent(PatientRegistrationActivity.this, FindPatientActivity.class));
                    }else {
                        System.out.println("create");
                        db.addPatient(pt);
                        startActivity(new Intent(PatientRegistrationActivity.this, HomeActivity.class));
                        Toast.makeText(getApplicationContext(), "Patient Created", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        tvBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PatientRegistrationActivity.this, HomeActivity.class));
            }
        });
    }
}