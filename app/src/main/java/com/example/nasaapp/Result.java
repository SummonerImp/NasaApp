package com.example.nasaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Result extends AppCompatActivity {
    TextView lblTitle, lblDate, lblContent, lblAutor;
    ImageView imgNasa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent it = getIntent();
        String apiUrl = it.getStringExtra("url");
        lblContent = (TextView)findViewById(R.id.lblContent);
        lblDate = (TextView)findViewById(R.id.lblDate);
        lblTitle = (TextView)findViewById(R.id.lblTitle);
        lblAutor = (TextView)findViewById(R.id.lblAutor);
        imgNasa = (ImageView)findViewById(R.id.imgNasa);
        jsonLoader(apiUrl);
    }

    public void jsonLoader(String apiUrl){
        JSONObject jsonObj = new JSONParse().doInBackground(apiUrl);
        String desc = "Sem descrição", url = "Imagem não encontrada", title = "Titulo não encontrado", autor = "Desconhecido", date="Data não encontrada";
        try {
            desc = jsonObj.get("explanation").toString();
            url = jsonObj.get("url").toString();
            title = jsonObj.get("title").toString();
            date = jsonObj.get("date").toString();
            autor = jsonObj.get("copyright").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        lblTitle.setText(title);
        lblDate.setText(date);
        lblAutor.setText(autor);
        imgNasa.setImageBitmap(getBitmapFromURL(url));
        lblContent.setText(desc);
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }

}