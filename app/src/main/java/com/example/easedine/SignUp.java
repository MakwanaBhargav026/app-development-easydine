package com.example.easedine;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUp extends AppCompatActivity {
    EditText username, fpassword, cpassword;
    Button b1;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views and database helper
        username = findViewById(R.id.editTextText);
        fpassword = findViewById(R.id.editTextTextPassword2);
        cpassword = findViewById(R.id.editTextTextPassword3);
        b1 = findViewById(R.id.button3);
        db = new DBHelper(this);

        // Set click listener for sign up button
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username = username.getText().toString();
                String password = fpassword.getText().toString();
                String copassword = cpassword.getText().toString();

                // Check if password and confirm password match
                if (password.equals(copassword)) {
                    // Clear the entire database before proceeding with user operations;

                    // Check if username already exists in the database
                    Boolean userExists = db.checkUsername(Username);
                    if (userExists) {
                        // Update the existing user's password
                        Boolean updatePassword = db.updateUserPassword(Username, password);
                        if (updatePassword) {
                            Toast.makeText(SignUp.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignUp.this, "Password update failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Insert new user data into the database
                        Boolean insertData = db.insertuserdata(Username, password, copassword);
                        if (insertData) {
                            Toast.makeText(SignUp.this, "New Entry inserted", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignUp.this, "New Entry not inserted", Toast.LENGTH_SHORT).show();
                        }
                    }

                    // Navigate to MainActivity
                    Intent intent = new Intent(SignUp.this, Page2.class);
                    startActivity(intent);
                } else {
                    // Show toast message if passwords do not match
                    Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
