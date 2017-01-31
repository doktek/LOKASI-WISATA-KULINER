package com.example.kusnadi.myapplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kusnadi on 12/10/2016.
 */

public class ApiClient implements Serializable {
    public List<Place> places = new ArrayList<>();
    public List<PlaceCategory> place_category = new ArrayList<>();
    public List<Images> images = new ArrayList<>();

    public ApiClient() {

    }

    public ApiClient(List<Place> places, List<PlaceCategory> place_category, List<Images> images) {
        this.places = places;
        this.place_category = place_category;
        this.images = images;
    }
}
