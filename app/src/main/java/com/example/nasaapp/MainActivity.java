package com.example.nasaapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btnPesq, btnExplicita, btnLocal;
    EditText txtDate;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPesq = findViewById(R.id.btnBusca);
        btnExplicita = findViewById(R.id.btnExplicita);
        txtDate = findViewById(R.id.txtDate);
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                DatePickerDialog picker = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                txtDate.setText(dayOfMonth + "-" + (monthOfYear+1) + "-" + year);
                                data = year + "-" + (monthOfYear+1) + "-" + dayOfMonth;
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        btnLocal = findViewById(R.id.idLocal);
    }

    public void Explicita(View v){
        Uri uri = Uri.parse("https://www.nasa.gov/multimedia/imagegallery/iotd.html");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void Api(View v){
        try {
            String apiUrl = "https://api.nasa.gov/planetary/apod?api_key=uudbjb6RRUtZRgd2lrkdjynY9L1RKai7W09VxomI&date=" + data;
            Intent it = new Intent(this, Result.class);
            it.putExtra("url", apiUrl);
            startActivity(it);
            Log.i("alerta", apiUrl);
        }
        catch(Exception e){
            Log.e("erro", String.valueOf(e));
        }
    }

    public void itLocal(View v){
        Intent it = new Intent(this, Localizacao.class);
        startActivity(it);
    }
}