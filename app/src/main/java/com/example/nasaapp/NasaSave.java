package com.example.nasaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NasaSave extends AppCompatActivity {
    TextView txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasa_save);
        txtContent = findViewById(R.id.txtContent);
        Intent it = getIntent();
        txtContent.setText(it.getStringExtra("save"));
    }
}