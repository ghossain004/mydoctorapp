package com.andorid.mydoctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.andorid.mydoctorapp.database.Database;

import java.util.ArrayList;
import java.util.HashMap;

public class FindPatientActivity extends AppCompatActivity {

    Button btnHome;

    ArrayList list;

    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_patient);

        btnHome = findViewById(R.id.buttonBackToHome);

        Database db = new Database(getApplicationContext(), "healthcare", null, 1);
        list = new ArrayList<>();
        list = db.getPatients();

        System.out.println(list.size());;

        sa = new SimpleAdapter(this,
                list,
                R.layout.list_view_layout_2, new String[]{
                "id", "patientName", "age", "registrationNumber", "disease", "email", "mobile"
        }, new int[]{R.id.viewId, R.id.viewName, R.id.viewReg, R.id.viewAge, R.id.viewDisease}){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                TextView editBtn = v.findViewById(R.id.edit);
                TextView delBtn = v.findViewById(R.id.delete);

                editBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println(position);
                        HashMap<String, String> patient;

                        try {
                            patient = (HashMap<String, String>) list.get(position);
                            System.out.println(patient);

                            Intent intent = new Intent(FindPatientActivity.this, PatientRegistrationActivity.class);
                            intent.putExtra("id", Integer.parseInt(patient.get("id")));
                            intent.putExtra("patientName", patient.get("patientName"));
                            intent.putExtra("registrationNumber", patient.get("registrationNumber"));
                            intent.putExtra("disease", patient.get("disease"));
                            intent.putExtra("email", patient.get("email"));
                            intent.putExtra("mobile", patient.get("mobile"));
                            intent.putExtra("age", patient.get("age"));
                            startActivity(intent);
                        }catch (Exception e){
                            System.out.println(e);
                        }
                    }
                });

                delBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println(position);
                        HashMap<String, String> patient;

                        try {
                            patient = (HashMap<String, String>) list.get(position);
                            boolean deleted = db.deletePatient((Integer.parseInt(patient.get("id"))));
                            if (deleted){
                                list.remove((position));
                                notifyDataSetChanged();
                            }
                            String message = deleted ? "Successfully deleted" : "Failed to delete";
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            System.out.println(patient);
                        }catch (Exception e){
                            System.out.println(e);
                        }
                    }
                });
                return v;
            }
        };

        ListView lv = findViewById(R.id.patientsList);
        lv.setAdapter(sa);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FindPatientActivity.this, HomeActivity.class));
            }
        });
    }
}