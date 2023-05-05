package com.example.supporthireapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.supporthireapp.Database.UserData;
import com.example.supporthireapp.R;
import com.example.supporthireapp.databinding.ActivityPersonalSecurityBinding;

public class PersonalSecurity extends AppCompatActivity {
    ActivityPersonalSecurityBinding binding;
    String uid,username;
    UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonalSecurityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        username = getIntent().getStringExtra("uid");
        userData = new UserData(this);
        changeEmail();
        changePassword();
        getUID();
    }

    public void getUID(){
        Cursor cursor = userData.checkUID(username);
        if (cursor.getCount()==1){
            while (cursor.moveToNext()){
                uid = cursor.getString(0);
            }
        }
        binding.UID.setText(uid);

    }
    public void changePassword(){
        binding.changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChangePassword.class)
                        .putExtra("UID", uid));
            }
        });
    }
    public void changeEmail(){
        binding.changeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChangeEmail.class)
                        .putExtra("UID",uid));
            }
        });
    }
}