package com.example.easedine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    Button b1, b2, b3;
    DBLogin db;
    ImageView img1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize database helper
        db = new DBLogin(this);

        // Initialize views
        username = findViewById(R.id.editTextText4);
        password = findViewById(R.id.editTextTextPassword);
        b1 = findViewById(R.id.button4);
        b2 = findViewById(R.id.button5);
        b3 = findViewById(R.id.button);
        img1 = findViewById(R.id.imageView3);

        // Load animation
        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        img1.startAnimation(animFadeIn);

        // Check if the user is already logged in
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("username", null);
        String savedPassword = sharedPreferences.getString("password", null);

        if (savedUsername != null && savedPassword != null) {
            // Automatically log in the user
            username.setText(savedUsername);
            password.setText(savedPassword);
            login(savedUsername, savedPassword);
        }

        // Set click listener for login button
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = username.getText().toString();
                String pass = password.getText().toString();

                // Check if username or password fields are empty
                if (uname.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                } else {
                    // Check if the user is the manager
                    if (uname.equals("manager") && pass.equals("admin")) {
                        // Navigate to ManagerOrderActivity to display the order table
                        Toast.makeText(MainActivity.this, "Manager Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, ManagerOrderActivity.class);
                        startActivity(intent);
                    } else {
                        // Check if username and password are correct for normal users
                        Boolean checkuserpass = db.checkUsernameAndPassword(uname,pass);
                        if (checkuserpass) {
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                            // Save the username and password in SharedPreferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("username", uname);
                            editor.putString("password", pass);
                            editor.apply();

                            // Navigate to Page2
                            Intent intent = new Intent(MainActivity.this, Page2.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Enter valid username or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
    }

    private void login(String username, String password) {
        // Logic to handle login without user interaction, similar to the login logic in onClick for button
        if (username.equals("manager") && password.equals("admin")) {
            Toast.makeText(MainActivity.this, "Manager Login Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, ManagerOrderActivity.class);
            startActivity(intent);
        } else if (true) {
            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Page2.class);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
}
