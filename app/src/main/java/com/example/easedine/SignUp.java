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
                String copass = cpassword.getText().toString();

                // Check if password and confirm password match
                if (password.equals(copass)) {
                    // Insert data into the database
                    Boolean checkdata = db.insertuserdata(Username, password, copass);
                    if (checkdata) {
                        Toast.makeText(SignUp.this, "New Entry inserted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUp.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignUp.this, "New Entry not inserted", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Show toast message if passwords do not match
                    Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
