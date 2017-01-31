package com.example.kusnadi.myapplication.model;

import java.io.Serializable;

public class GcmNotif implements Serializable {
    private String title, content;
    private Place place;

    public GcmNotif() {
    }

    public GcmNotif(String title, String content, Place place) {
        this.title = title;
        this.content = content;
        this.place = place;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
