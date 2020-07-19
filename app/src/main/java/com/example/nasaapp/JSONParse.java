package com.example.nasaapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class JSONParse extends AsyncTask<String, String, JSONObject> {
    static InputStream is = null;
    static JSONObject jsonObject = null;
    static String output = null;

    @Override
    protected JSONObject doInBackground(String... strings) {
        URL url;
        HttpURLConnection urlConnection;

        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
        }
        catch (MalformedURLException e) {
            Log.e("JSON Parser", "URL Errada " + e.toString());
            return null;
        }
        catch (IOException e) {
            Log.e("JSON Parser", "IO error " + e.toString());
            return null;
        }

        try {
            is = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder total = new StringBuilder(is.available());
            String line;
            while ((line = reader.readLine()) != null) {
                total.append(line).append('\n');
            }
            output = total.toString();

        }
        catch (IOException e) {
            Log.e("JSON Parser", "IO error " + e.toString());
            return null;
        }
        finally{
            urlConnection.disconnect();
        }

        try {
            jsonObject = new JSONObject(output);
        }
        catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return jsonObject;
    }

    protected void onPostExecute(JSONObject jsonObject){
        super.onPostExecute(jsonObject);
    }
}
