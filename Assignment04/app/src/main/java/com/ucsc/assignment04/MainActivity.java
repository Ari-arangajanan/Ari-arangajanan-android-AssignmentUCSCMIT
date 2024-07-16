package com.ucsc.assignment04;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText number1 = findViewById(R.id.number1);
        EditText number2 = findViewById(R.id.number2);
        Button add = findViewById(R.id.addButton);
        Button subtract = findViewById(R.id.subtractButton);
        answer = findViewById(R.id.answer);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNum(number1, number2);
            }
        });
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtract(number1, number2);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addNum(EditText number1EditText, EditText number2EditText) {
        String number1Str = number1EditText.getText().toString();
        String number2Str = number2EditText.getText().toString();

        if (TextUtils.isEmpty(number1Str) || TextUtils.isEmpty(number2Str)) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double number1 = Double.parseDouble(number1Str);
            double number2 = Double.parseDouble(number2Str);
            double result = number1 + number2;
            answer.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show();
        }
    }

    private void subtract(EditText number1EditText, EditText number2EditText){
        String number1Str = number1EditText.getText().toString();
        String number2Str = number2EditText.getText().toString();

        if (TextUtils.isEmpty(number1Str) || TextUtils.isEmpty(number2Str)) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double number1 = Double.parseDouble(number1Str);
            double number2 = Double.parseDouble(number2Str);
            double result = number1 - number2;
            answer.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show();
        }
    }
}
