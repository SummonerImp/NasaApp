package com.example.nasaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Result extends AppCompatActivity implements AsyncResponse {
    TextView lblTitle, lblDate, lblContent, lblAutor;
    Button btnSave;
    ImageView imgNasa;
    JSONParse jsonparse = new JSONParse();
    bmParse btmParse = new bmParse();
    private NasaApodDAO dao;
    public NasaApod na = null;
    String urlImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_result);
            Intent it = getIntent();
            if (it.hasExtra("objNasa")) {
                na=(NasaApod)it.getSerializableExtra("objNasa");
                lblTitle.setText(na.getTitle());
                lblDate.setText(na.getDate());
                lblAutor.setText(na.getAuthor());
                //btmParse.execute(na.getUrlImg());
                lblContent.setText(na.getContent());
                return;
            }
            String apiUrl = it.getStringExtra("url");
            lblContent = (TextView) findViewById(R.id.lblContent);
            lblDate = (TextView) findViewById(R.id.lblDate);
            lblTitle = (TextView) findViewById(R.id.lblTitle);
            lblAutor = (TextView) findViewById(R.id.lblAutor);
            imgNasa = (ImageView) findViewById(R.id.imgNasa);
            btnSave = findViewById(R.id.btnSave);
            jsonparse.delegate = this;
            btmParse.btm = this;
            dao = new NasaApodDAO(this);
            jsonparse.execute(apiUrl);
            lblContent.setMovementMethod(new ScrollingMovementMethod());
            btnSave.setVisibility(View.VISIBLE);
        //}
        //catch (Exception e){
        //    Log.e("erro", e.toString());
        //}
    }

    @Override
    public void processFinished(JSONObject output) {
        String desc = "Sem descrição", url = "Imagem não encontrada", title = "Titulo não encontrado", autor = "Desconhecido", date="Data não encontrada";
        try {
            desc = output.get("explanation").toString();
            url = output.get("url").toString();
            title = output.get("title").toString();
            date = output.get("date").toString();
            autor = output.get("copyright").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        lblTitle.setText(title);
        lblDate.setText(date);
        lblAutor.setText(autor);
        urlImg = url;
        btmParse.execute(url);
        lblContent.setText(desc);
    }

    @Override
    public void bitmapProcess(Bitmap bm) {
        imgNasa.setImageBitmap(bm);
    }

    public void save(View v){
        NasaApod nasaApod = new NasaApod(lblTitle.getText().toString(), lblAutor.getText().toString(), lblContent.getText().toString(), urlImg, lblDate.getText().toString());
        long id = dao.insert(nasaApod);
        Toast.makeText(this, "Inserido com o id: " + id, Toast.LENGTH_SHORT).show();
    }
}