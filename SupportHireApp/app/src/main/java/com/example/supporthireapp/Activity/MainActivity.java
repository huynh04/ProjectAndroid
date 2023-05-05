package com.example.supporthireapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.supporthireapp.Database.CandidateData;
import com.example.supporthireapp.Database.UserData;
import com.example.supporthireapp.Model.User;
import com.example.supporthireapp.R;
import com.example.supporthireapp.databinding.ActivityMainBinding;
import com.google.android.material.color.utilities.ColorUtils;
import com.google.android.material.navigation.*;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    ActivityMainBinding binding;
    CandidateData candidateData;
    String name,date,skill,address,experience,field,uid;
    ActionBarDrawerToggle drawerToggle;
    UserData userData;
    ArrayList<User>userArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initDatePicker();
        binding.date.setText(getTodayDate());
        candidateData = new CandidateData(this);
        uid = getIntent().getStringExtra("username");
        userArrayList= new ArrayList<>();

        setData();
        list_item_menu();


    }


    public String getTodayDate(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        month = month+1;
        int year = calendar.get(Calendar.YEAR);

        return makeDataString(day,month,year);
    }
    public void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month +1;
                String date = makeDataString(day,month,year);
                binding.date.setText(date);
            }
        };

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog =new DatePickerDialog(this,style,dateSetListener,year,month,day);

    }
    private String makeDataString(int day, int month, int year) {
        return year+"-"+getMonthFormat(month)+"-"+day;
    }

    public String getMonthFormat(int month){
        if (month==1){
            return "01";
        }
        else if(month==2){
            return "02";
        }
        else if(month==3){
            return "03";
        }
        else if(month==4){
            return "04";
        }
        else if(month==5){
            return "05";
        }
        else if(month==6){
            return "06";
        }else if(month==7){
            return "07";
        }else if(month==8){
            return "08";
        }
        else if(month==9){
            return "09";
        }
        else if(month==10){
            return "10";
        }
        else if(month==11){
            return "11";
        }
        else{
            return "12";
        }

    }

    public void opendatePicker(View view) {
        datePickerDialog.show();
    }
    public void setData(){

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = binding.fullname.getText().toString();
                date = binding.date.getText().toString();
                address = binding.address.getText().toString();
                skill = binding.skills.getText().toString();
                experience = binding.Experience.getText().toString();
                field = binding.field.getText().toString();

                Boolean result = candidateData.insertData(name,date,address,skill,experience,field,uid);
                if (name.isEmpty()||date.isEmpty()||address.isEmpty()){
                    Toast.makeText(MainActivity.this, "All required", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (result == true){
                        Toast.makeText(MainActivity.this, "Save Completed", Toast.LENGTH_SHORT).show();
                        binding.fullname.setText("");
                        binding.date.setText(getTodayDate());
                        binding.address.setText("");
                        binding.skills.setText("");
                        binding.Experience.setText("");
                        binding.field.setText("");
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Save Failed", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    public void list_item_menu(){
        drawerToggle = new ActionBarDrawerToggle(this, binding.drawerlayout, R.string.open,R.string.close);
        binding.drawerlayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();
        binding.navItem.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.Home){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                if (id == R.id.contact) {
                    Toast.makeText(MainActivity.this, "Contact", Toast.LENGTH_SHORT).show();
                }
                if(id == R.id.list_candidate){
                    startActivity(new Intent(MainActivity.this, ListCandidate.class)
                            .putExtra("uid", uid));
                }
                if (id == R.id.find_candidate) {
                    startActivity(new Intent(MainActivity.this, FindCandidate.class)
                            .putExtra("uid", uid));
                }
                if (id == R.id.personal_data) {
                    startActivity(new Intent(MainActivity.this, PersonalSecurity.class)
                            .putExtra("uid", uid));
                }
                if (id == R.id.logout){
                    startActivity(new Intent(getApplicationContext(), LoginAndSignUp.class));
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerlayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerlayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
}