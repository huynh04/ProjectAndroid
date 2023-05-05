package com.example.supporthireapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.supporthireapp.Database.UserData;
import com.example.supporthireapp.R;
import com.example.supporthireapp.databinding.ActivityChangeEmailBinding;

public class ChangeEmail extends AppCompatActivity {
    String UID;
    String email,re_email;
    ActivityChangeEmailBinding binding;
    UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangeEmailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        UID = getIntent().getStringExtra("UID");
        userData = new UserData(this);

        pushButtonChange();
    }
    public void pushButtonChange(){
        binding.btnChangeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = binding.emailCurrent.getText().toString();
                re_email = binding.ReEmail.getText().toString();
                if (email.isEmpty() || re_email.isEmpty()){
                    Toast.makeText(ChangeEmail.this, "Required Field", Toast.LENGTH_SHORT).show();
                }
                else {
                    checkEmail(email,UID,re_email);
                }
            }
        });
    }
    public void checkEmail(String email, String uid,String re_email){
        Boolean result = userData.checkPassAndEmail(email,"",uid);
        if (result == true){
            updateEmail(re_email,uid);
        }
        else {
            Toast.makeText(this, "Email Incorrect", Toast.LENGTH_SHORT).show();
        }
    }
    public void updateEmail(String email_new, String uid){
        Boolean result = userData.updateEmail(email_new,uid);
        if (result == true){
            Toast.makeText(this, "Email Updated", Toast.LENGTH_SHORT).show();
            binding.emailCurrent.setText("");
            binding.ReEmail.setText("");
        }
        else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}