package com.mywebserviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvData = findViewById(R.id.tv_data);

        Intent i = getIntent();
        String firstName = i.getStringExtra("firstName");
        String lastName = i.getStringExtra("lastName");

        tvData.setText(firstName + " " + lastName);


    }
}