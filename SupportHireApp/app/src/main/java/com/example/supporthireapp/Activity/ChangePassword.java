package com.example.supporthireapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.supporthireapp.Database.UserData;
import com.example.supporthireapp.R;
import com.example.supporthireapp.databinding.ActivityChangePasswordBinding;

public class ChangePassword extends AppCompatActivity {

    ActivityChangePasswordBinding binding;
    String UID;
    String password, new_password;
    UserData userData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        UID = getIntent().getStringExtra("UID");
        userData = new UserData(this);
        pushButtonChange();

    }
    public void pushButtonChange(){
        binding.btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = binding.passwordCurrent.getText().toString().trim();
                new_password = binding.RePassword.getText().toString().trim();

                if (password.isEmpty() || new_password.isEmpty()){
                    Toast.makeText(ChangePassword.this, "Required Field", Toast.LENGTH_SHORT).show();
                }
                else {
                    checkPassword(password,new_password,UID);
                }

            }
        });
    }
    public void checkPassword(String password,String new_password,String UID){
        Boolean result = userData.checkPassAndEmail("",password,UID);
        if (result==true){
            updatePassword(new_password,UID);
        }
        else {
            Toast.makeText(this, "Password Incorrect", Toast.LENGTH_SHORT).show();
        }
    }
    public void updatePassword(String newPassword, String UID){
        Boolean result = userData.updatePass(newPassword,UID);
        if (result==true){
            Toast.makeText(this, "Update Completed", Toast.LENGTH_SHORT).show();
            binding.passwordCurrent.setText("");
            binding.RePassword.setText("");
        }
        else {
            Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show();
        }
    }
}