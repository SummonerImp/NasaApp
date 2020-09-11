package com.example.nasaapp;

import java.util.TimeZone;

public class GeoLocation {
    public float lat;
    public float longi;
    public String time;

    public void setLat(float lat){
        this.lat = lat;
    }

    public float getLat(){
        return lat;
    }

    public void setLongi(float longi){
        this.longi = longi;
    }

    public float getLongi(){
        return longi;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getTime(){
        return time;
    }
}
