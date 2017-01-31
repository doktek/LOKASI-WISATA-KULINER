package com.example.kusnadi.myapplication.model;

import java.io.Serializable;

/**
 * Created by Kusnadi on 12/9/2016.
 */

public class Images implements Serializable{
    public int place_id;
    public String name;

    public Images() {

    }

    public Images(int place_id, String name) {
        this.place_id = place_id;
        this.name = name;
    }

    public String getImageUrl(){
        return name;
    }

}
