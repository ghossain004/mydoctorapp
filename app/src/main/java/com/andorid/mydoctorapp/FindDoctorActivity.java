package com.andorid.mydoctorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.andorid.mydoctorapp.database.Database;

import java.util.ArrayList;
import java.util.HashMap;

public class FindDoctorActivity extends AppCompatActivity {

    Button btnHome;

    ArrayList list;

    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        btnHome = findViewById(R.id.buttonBackToHome);

        Database db = new Database(getApplicationContext(), "healthcare", null, 1);
        list = new ArrayList<>();
        list = db.getDoctors();

        System.out.println(list.size());;

        sa = new SimpleAdapter(this,
                list,
                R.layout.list_view_layout, new String[]{
                "id", "doctorName", "email", "address"
        }, new int[]{R.id.viewId, R.id.viewName, R.id.viewEmail, R.id.viewAddress}){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                TextView editBtn = v.findViewById(R.id.edit);
                TextView delBtn = v.findViewById(R.id.delete);

                editBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println(position);
                        HashMap<String, String> user;

                        try {
                            user = (HashMap<String, String>) list.get(position);
                            System.out.println(user);

                            Intent intent = new Intent(DcotorListActivity.this, DoctorDetailsActivity.class);
                            intent.putExtra("id", Integer.parseInt(user.get("id")));
                            intent.putExtra("doctorName", user.get("doctorName"));
                            intent.putExtra("email", user.get("email"));
                            intent.putExtra("address", user.get("address"));
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
                        HashMap<String, String> user;

                        try {
                            user = (HashMap<String, String>) list.get(position);
                            boolean deleted = dt.deleteDoctor((Integer.parseInt(user.get("id"))));
                            if (deleted){
                                list.remove((position));
                                notifyDataSetChanged();
                            }
                            String message = deleted ? "Successfully deleted" : "Failed to delete";
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            System.out.println(user);
                        }catch (Exception e){
                            System.out.println(e);
                        }
                    }
                });
                return v;
            }
        };

        ListView lv = findViewById(R.id.doctorList);
        lv.setAdapter(sa);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FindDoctorActivity.this, HomeActivity.class));
            }
        });
    }
}