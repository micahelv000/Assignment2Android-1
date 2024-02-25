package com.example.assignment2android_1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class Home extends AppCompatActivity {


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");
        TextView TextMSGHllo = findViewById(R.id.userDetailMSG);
        TextMSGHllo.setText("username: " + username +" password: "+password);
    }
}