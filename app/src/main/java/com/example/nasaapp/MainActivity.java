package com.example.nasaapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btnPesq, btnExplicita, btnLocal;
    EditText txtDate;
    String data;
    protected LocationManager locationManager;


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
                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                data = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        btnLocal = findViewById(R.id.idLocal);
        Log.i("diretorio", getApplicationInfo().dataDir);
    }

    public void Explicita(View v) {
        Uri uri = Uri.parse("https://www.nasa.gov/multimedia/imagegallery/iotd.html");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void Api(View v) {
        try {
            if(data == null){
                data = " ";
                String apiUrl = "https://api.nasa.gov/planetary/apod?api_key=uudbjb6RRUtZRgd2lrkdjynY9L1RKai7W09VxomI&date=" + data;
                Intent it = new Intent(this, Result.class);
                it.putExtra("url", apiUrl);
                startActivity(it);
                Log.i("alerta", apiUrl);
            }
            else if(data != null){
                String apiUrl = "https://api.nasa.gov/planetary/apod?api_key=uudbjb6RRUtZRgd2lrkdjynY9L1RKai7W09VxomI&date=" + data;
                Intent it = new Intent(this, Result.class);
                it.putExtra("url", apiUrl);
                startActivity(it);
                Log.i("alerta", apiUrl);
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            Log.i("erro", String.valueOf(e));
        }
    }

    /*public void itLocal(View v) {
        try{
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            else{
                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();
                Bundle params = new Bundle();
                params.putDouble("long", longitude);
                params.putDouble("lati", latitude);
                Intent it = new Intent(this, Localizacao.class);
                it.putExtras(params);
                startActivity(it);
            }
        }
        catch (Exception e){
            Log.i("erro", String.valueOf(e));
        }
    }*/

    public void savedContents(View v){
        Intent it = new Intent(this, SavedContents.class);
        startActivity(it);
    }
}