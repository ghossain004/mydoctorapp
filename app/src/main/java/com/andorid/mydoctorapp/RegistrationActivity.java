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

public class RegistrationActivity extends AppCompatActivity {

    EditText edfullName, eduserName, edemail, edmobile, edpassword, edconfirmPassword;
    Button btnSignup;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        edfullName = findViewById(R.id.userRegistrationFullName);
        eduserName = findViewById(R.id.userRegistrationUserName);
        edemail = findViewById(R.id.userRegistrationEmail);
        edmobile = findViewById(R.id.userRegistrationMobile);
        edpassword = findViewById(R.id.userRegistrationPassword);
        edconfirmPassword = findViewById(R.id.userRegistrationConPassword);
        btnSignup = findViewById(R.id.RegistrationSignup);
        tvLogin = findViewById(R.id.RegistrationLogin);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = edfullName.getText().toString();
                String userName = eduserName.getText().toString();
                String email = edemail.getText().toString();
                String mobile = edmobile.getText().toString();
                String password = edpassword.getText().toString();
                String confirmPassword = edconfirmPassword.getText().toString();
                System.out.println(fullName + " " + userName + " " + email + " " + mobile + " " + password + " " + confirmPassword);

                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                if (fullName.length()==0 || userName.length()==0 || mobile.length()==0 || password.length()==0 || confirmPassword.length()==0 || email.length()==0){
                    Toast.makeText(getApplicationContext(), "Please Fill All The Field", Toast.LENGTH_SHORT).show();
                }else {
                    if (password.compareTo(confirmPassword)==0){
                        if (password.length()>6){
                            db.addNewUser(fullName, userName, email, mobile, password);
                            Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                        }else {
                            Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Password didn't match", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });
    }
}