package com.andorid.mydoctorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andorid.mydoctorapp.database.Database;

public class MainActivity extends AppCompatActivity {

    EditText userName, password;
    Button btnLogin;
    TextView tvSignup;
    ImageView imgShowPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.loginUserName);
        password = findViewById(R.id.loginUserPass);
        btnLogin = findViewById(R.id.loginButton);
        tvSignup = findViewById(R.id.loginCreate);
        Database dt = new Database(getApplicationContext(), "healthcare", null,1);

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent it = new Intent(MainActivity.this, RegistrationActivity.class);
//                it.putExtra("username", userName.getText());
//                it.putExtra("password", password.getText());
//                startActivity(it);

                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _userName = userName.getText().toString();
                String _password = password.getText().toString();

//                System.out.println(_password + "" + _userName);

                if (_userName.length()==0 || _password.length()==0){
                    Toast.makeText(getApplicationContext(), "Please fillup all the field", Toast.LENGTH_SHORT).show();
                }else {
                    if (dt.login(_userName, _password)==1){
                        Toast.makeText(getApplicationContext(), "User login success", Toast.LENGTH_SHORT).show();
                        SharedPreferences preferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("userName", _userName);
                        editor.apply();
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                        Toast.makeText(getApplicationContext(),"Welcome "+_userName+"!",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "User name or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}