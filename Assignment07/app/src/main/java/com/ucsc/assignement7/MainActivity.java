package com.ucsc.assignement7;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btnRegister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        button = findViewById(R.id.btnadminlogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminLogin();
            }
        });

        button = findViewById(R.id.btnmemlogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberLogin();
            }
        });
    }

    public void signup(){
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }

    public  void memberLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public  void adminLogin(){
        Intent intent = new Intent(this, AdminLogin.class);
        startActivity(intent);
    }
}