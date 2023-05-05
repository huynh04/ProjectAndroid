package com.example.supporthireapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.supporthireapp.Database.UserData;
import com.example.supporthireapp.Model.User;
import com.example.supporthireapp.R;
import com.example.supporthireapp.databinding.ActivityLoginAndSignUpBinding;

import java.util.ArrayList;

public class LoginAndSignUp extends AppCompatActivity {
    ActivityLoginAndSignUpBinding binding;
    String username,password,email;
    UserData userData;
    ArrayList<User> userArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginAndSignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userData = new UserData(this);
        userArrayList = new ArrayList<>();

        OnClick();
        delete();

        binding.loginOrSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.loginOrSignup.getText().toString().equals("Login")){
                    binding.loginOrSignup.setText("SignUp");
                    binding.inputEmail.setVisibility(View.GONE);
                    binding.btnClick.setText("Login");
                    binding.inputUsername.setText("");
                    binding.inputPassword.setText("");
                    binding.inputEmail.setText("");
                }
                else {
                    binding.loginOrSignup.setText("Login");
                    binding.inputEmail.setVisibility(View.VISIBLE);
                    binding.btnClick.setText("SignUp");
                    binding.inputUsername.setText("");
                    binding.inputPassword.setText("");
                    binding.inputEmail.setText("");

                }
            }
        });
    }
    public void OnClick(){
        binding.btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.btnClick.getText().toString().equals("SignUp")){
                    SignUp();
                }
                else {
                    Login();
                }
            }
        });
    }
    public void delete(){
        userArrayList.clear();
        Cursor cursor = userData.AllAccount();
        while (cursor.moveToNext()){
            userArrayList.add(new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result;
                for (int i = 0; i<userArrayList.size();i++){
                    result = userData.deleteData(userArrayList.get(i).getId());
                    if (result == true){
                        Toast.makeText(LoginAndSignUp.this, "Delete Completed", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(LoginAndSignUp.this, "Delete Failed", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }

    public void SignUp(){
        username = binding.inputUsername.getText().toString().trim();
        password = binding.inputPassword.getText().toString().trim();
        email = binding.inputEmail.getText().toString().trim();
        if (username.isEmpty() || password.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show();
        }
        else {
            Boolean checkUsername = userData.checkUsername(username);
            if (checkUsername == true){
                Boolean result = userData.insertData(username,password,email);
                if (result == true){
                    Toast.makeText(this, "SignUp Completed", Toast.LENGTH_SHORT).show();
                    binding.loading.setVisibility(View.GONE);
                    binding.btnClick.setText("Login");
                    binding.inputEmail.setVisibility(View.GONE);
                    binding.inputUsername.setText("");
                    binding.inputPassword.setText("");
                    binding.inputEmail.setText("");
                    binding.loginOrSignup.setText("SignUp");
                }
                else {
                    Toast.makeText(this, "SignUp Failed", Toast.LENGTH_SHORT).show();
                }

            }
            else {
                Toast.makeText(this, "Username Available", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void Login(){
        username = binding.inputUsername.getText().toString().trim();
        password = binding.inputPassword.getText().toString().trim();
        if (username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show();
        }
        else {
            Boolean result = userData.checkUsernamePassword(username,password);
            if (result == true){
                Toast.makeText(this, "Login Completed", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class)
                        .putExtra("username", username));
                finish();
            }
            else {
                Toast.makeText(this, "Account Unavailable", Toast.LENGTH_SHORT).show();
            }
        }

    }

}