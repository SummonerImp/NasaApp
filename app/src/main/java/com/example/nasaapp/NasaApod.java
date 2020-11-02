package com.example.nasaapp;

import java.io.Serializable;

public class NasaApod implements Serializable {
    private Integer id;
    private String title;
    private String author;
    private String content;
    private String urlImg;
    private String date;

    public NasaApod(String title, String author, String content, String urlImg, String date) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.urlImg = urlImg;
        this.date = date;
    }

    public NasaApod() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return title;
    }
}
