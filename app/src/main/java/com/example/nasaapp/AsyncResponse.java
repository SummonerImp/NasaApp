package com.example.nasaapp;

import android.graphics.Bitmap;

import org.json.JSONObject;

public interface AsyncResponse {
    void processFinished(JSONObject jsonObject);
    void bitmapProcess(Bitmap bm);
}
