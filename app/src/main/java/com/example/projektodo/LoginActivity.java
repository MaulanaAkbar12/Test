package com.example.projektodo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    EditText Username, Password;
    Button Login, moveRegister;
    DBHelper DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        Login = findViewById(R.id.Login);
        moveRegister = findViewById(R.id.moveRegister);
        DBHelper = new DBHelper(this);


        moveRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User = Username.getText().toString().trim();
                String Pass = Password.getText().toString().trim();
                if (User.isEmpty() || Pass.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Please Enter the Username and Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    String LoggedInUsername = DBHelper.checkLogin(User, Pass);
                    if (LoggedInUsername != null)
                    {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("Username", LoggedInUsername);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Invalid Username", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}