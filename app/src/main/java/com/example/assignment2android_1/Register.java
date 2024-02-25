package com.example.assignment2android_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private EditText editTextUserName, editTextPass, editTextPhone, editTextEmail;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPass = findViewById(R.id.editTextPassword);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
    }

    public void Login(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void B_Register(View view) {
        final String username = editTextUserName.getText().toString();
        final String password = editTextPass.getText().toString();
        final String phone = editTextPhone.getText().toString();
        final String email = editTextEmail.getText().toString();

        if (isValidUsername(username) && isValidPassword(password) && isValidPhone(phone) && isValidEmail(email)) {
            // Push user details to Firebase Realtime Database
            String userId = mDatabase.push().getKey();
            User user = new User(username, email, phone, password);
            mDatabase.child(userId).setValue(user);

            Toast.makeText(Register.this, "Registration successful", Toast.LENGTH_LONG).show();

            // Start Home activity or perform any other desired action
            Intent intent = new Intent(Register.this, Home.class);
            intent.putExtra("username", username);
            startActivity(intent);
        } else {
            // Validation failed, display a message to the user
            Toast.makeText(Register.this, "Please enter valid information for all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidUsername(String username) {
        return !TextUtils.isEmpty(username) && username.length() >= 3;
    }

    private boolean isValidPassword(String password) {
        return !TextUtils.isEmpty(password) && password.length() >= 6 && password.length() <= 14;
    }

    private boolean isValidPhone(String phone) {
        return !TextUtils.isEmpty(phone) && phone.length() == 10 && TextUtils.isDigitsOnly(phone);
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
