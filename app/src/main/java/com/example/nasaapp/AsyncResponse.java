package com.example.nasaapp;

import org.json.JSONObject;

public interface AsyncResponse {
    void processFinished(JSONObject jsonObject);
}
