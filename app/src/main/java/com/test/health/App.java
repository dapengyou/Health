package com.test.health;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lady_zhou on 2018/3/30.
 */

public class App extends Application {
    public static List<?> images = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        String[] urls = getResources().getStringArray(R.array.url);
        List list = Arrays.asList(urls);
        images = new ArrayList(list);
    }
}
