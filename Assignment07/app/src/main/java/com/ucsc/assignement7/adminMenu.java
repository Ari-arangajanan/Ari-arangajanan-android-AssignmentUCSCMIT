package com.ucsc.assignement7;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.ucsc.assignement7.databinding.ActivityAdminMenuBinding;

public class adminMenu extends AppCompatActivity {
    private Button logoutBtn, addingBtn, managePubBtn, branchBtn, lendingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        logoutBtn = findViewById(R.id.btnlogout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity();
            }
        });

        addingBtn = findViewById(R.id.btnaddingB);
        addingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageBooks();
            }
        });

        managePubBtn = findViewById(R.id.btnmanagepub);
        managePubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managePublisher();
            }
        });

        branchBtn = findViewById(R.id.btnbranch);
        branchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageBranch();
            }
        });

        lendingBtn = findViewById(R.id.btnlending);
        lendingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lending();
            }
        });

    }

    public void manageBooks(){
        Intent intent = new Intent(this, manageBooks.class);
        startActivity(intent);
    }

    public void managePublisher(){
        Intent intent = new Intent(this, managePublisher.class);
        startActivity(intent);
    }

    public void manageBranch(){
        Intent intent = new Intent(this, mangeBranch.class);
        startActivity(intent);
    }

    public void lending(){
        Intent intent = new Intent(this, lending.class);
        startActivity(intent);
    }

    public void MainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}