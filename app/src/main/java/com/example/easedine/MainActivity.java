package com.example.easedine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.animation.*;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    Button b1,b2,b3;
    DBHelper db;
    ImageView img1,img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize database helper
        db = new DBHelper(this);

        // Initialize views
        username = findViewById(R.id.editTextText4);
        password = findViewById(R.id.editTextTextPassword);
        b1 = findViewById(R.id.button4);
        b2 = findViewById(R.id.button5);
        b3 = findViewById(R.id.button);
        img1 = findViewById(R.id.imageView3);
        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
        img1.startAnimation(animFadeIn);

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
                    // Check if username and password are correct
                    Boolean checkuserpass = db.checkdetails(uname, pass);
                    if (checkuserpass) {
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        // Navigate to Page2
                        Intent intent = new Intent(MainActivity.this, Page2.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Enter valid username or password", Toast.LENGTH_SHORT).show();
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
}
