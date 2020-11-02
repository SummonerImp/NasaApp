package com.example.nasaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NasaApodDAO {
    private Conexao conexao;
    private SQLiteDatabase db;

    public NasaApodDAO(Context context) {
        conexao = new Conexao(context);
        db = conexao.getWritableDatabase();
    }

    public long insert(NasaApod data){
        ContentValues values = new ContentValues();
        values.put("title", data.getTitle());
        values.put("author", data.getAuthor());
        values.put("content", data.getContent());
        values.put("urlImage", data.getUrlImg());
        values.put("data", data.getDate());
        return db.insert("nasaData",null, values);
    }

    public List<NasaApod> selectAll() {
        List<NasaApod> nasaData  = new ArrayList<>();
        Cursor cursor = db.query("nasaData", new String[]{"id", "title", "author", "content", "urlImage", "data"}, null, null, null, null, null);
        while(cursor.moveToNext()){
            NasaApod na = new NasaApod();
            na.setId(cursor.getInt(0));
            na.setTitle(cursor.getString(1));
            na.setAuthor(cursor.getString(2));
            na.setContent(cursor.getString(3));
            na.setUrlImg(cursor.getString(4));
            na.setDate(cursor.getString(5));
            nasaData.add(na);
        }
        db.close();
        return nasaData;
    }

    public void delete(NasaApod na){
        db.delete("nasaData", "id = ?", new String[]{na.getId().toString()});
    }

    public List<NasaApod> selectOne(String i){
        List<NasaApod> naList = new ArrayList<>();
        Cursor cursor = db.query("nasaData", new String[]{"id", "title", "author", "content", "urlImage", "data"}, "id = ?", new String[]{i}, null, null, null);
        if(cursor.moveToFirst()){
            NasaApod na = new NasaApod();
            na.setId(cursor.getInt(0));
            na.setTitle(cursor.getString(1));
            na.setAuthor(cursor.getString(2));
            na.setContent(cursor.getString(3));
            na.setUrlImg(cursor.getString(4));
            na.setDate(cursor.getString(5));
            naList.add(na);
        }
        db.close();
        return naList;
    }
}
