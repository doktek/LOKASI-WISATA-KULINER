package com.example.kusnadi.myapplication.connection.callbacks;

import com.example.kusnadi.myapplication.model.Place;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kusnadi on 12/10/2016.
 */

public class CallbackListPlace implements Serializable {

    public String status = "";
    public int count = -1;
    public int count_total = -1;
    public int pages = -1;
    public List<Place> places = new ArrayList<>();
}
