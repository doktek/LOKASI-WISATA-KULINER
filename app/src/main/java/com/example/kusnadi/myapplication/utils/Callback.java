package com.example.kusnadi.myapplication.utils;

/**
 * Created by Kusnadi on 12/10/2016.
 */
public interface Callback<T> {
    void onSuccess(T result);

    void onError(String result);

}
