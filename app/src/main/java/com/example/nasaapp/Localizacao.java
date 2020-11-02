package com.example.nasaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.String.valueOf;

public class Localizacao extends AppCompatActivity {
    Button btnGo, btnMyLocal;
    TextView txtLocal;
    double longi, lati;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacao);
        btnGo = findViewById(R.id.btnGo);
        btnMyLocal = findViewById(R.id.btnMyLocation);
        txtLocal = findViewById(R.id.txtLocal);
        Intent it = getIntent();
        Bundle params = it.getExtras();
        longi = params.getDouble("long");
        lati = params.getDouble("lati");
    }

    public void openMapMyLocation(View v) {
        String local = "geo:"+lati+","+longi+"?z=14";
        Uri location = Uri.parse(local);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
        startActivity(mapIntent);
    }

    public void localResulActivity(View v) {
        try {
            String local = "geo:0,0?q=" + txtLocal.getText().toString().replaceAll(" ","+");
            Log.i("string",local);
            Uri location = Uri.parse(local);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
            startActivity(mapIntent);
        }
        catch(Exception e){
            Log.i("erro", String.valueOf(e));
        }
    }
}