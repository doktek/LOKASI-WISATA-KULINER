package com.example.kusnadi.myapplication.data;

/**
 * Created by Kusnadi on 12/9/2016.
 */

public class Constant {

    public static String WEB_URL = "http://mytrips.16mb.com/admin_project/";

    // for map zoom
    public static final double city_lat = -6.917077;
    public static final double city_lng = 107.616032;

    public static final String PROJECT_API_NUMBER = "955017174548";

    public static String getURLimgPlace(String file_name) {
        return WEB_URL + "upload/place/" + file_name;
    }

    public static final int LIMIT_PLACE_REQUEST = 50;
    public static final int LIMIT_LOADMORE = 50;

    public static final String LOG_TAG = "CITY_LOG";

    public enum Event {
        FAVORITES,
        THEME,
        NOTIFICATION,
        REFRESH
    }
}
