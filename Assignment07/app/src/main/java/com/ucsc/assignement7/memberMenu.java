package com.ucsc.assignement7;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class memberMenu extends AppCompatActivity {

    Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_menu);

        button = findViewById(R.id.btnmemberlogout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity();
            }
        });

        button = findViewById(R.id.btnserachbook);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBooks();
            }
        });

    }

    public void MainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void searchBooks(){
        Intent intent = new Intent(this, SearchBooks.class);
        startActivity(intent);
    }
}