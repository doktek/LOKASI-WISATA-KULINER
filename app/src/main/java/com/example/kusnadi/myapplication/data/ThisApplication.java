package com.example.kusnadi.myapplication.data;

import android.app.Application;
import android.util.Log;

import com.example.kusnadi.myapplication.R;
import com.example.kusnadi.myapplication.utils.Tools;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by Kusnadi on 12/10/2016.
 */

public class ThisApplication extends Application {

    private static ThisApplication mInstance;
    private Tracker tracker;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Constant.LOG_TAG, "onCreate : ThisApplication");
        mInstance = this;

        //init image loader
        Tools.initImageLoader(getApplicationContext());

        //activate analytics tracker
        getGoogleAnalyticsTracker();
    }

    public static synchronized ThisApplication getInstance() {
        return mInstance;
    }

    /**
     * --------------------------------------------------------------------------------------------
     * For Google Analytics
     */
    public synchronized Tracker getGoogleAnalyticsTracker() {
        if (tracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            analytics.setDryRun(!AppConfig.ENABLE_ANALYTICS);
            tracker = analytics.newTracker(R.xml.app_tracker);
        }
        return tracker;
    }

    public void trackScreenView(String screenName) {
        Tracker t = getGoogleAnalyticsTracker();
        // Set screen name
        t.setScreenName(screenName);
        // Send a screen view
        t.send(new HitBuilders.ScreenViewBuilder().build());
        GoogleAnalytics.getInstance(this).dispatchLocalHits();
    }

    public void trackException(Exception e) {
        if (e != null) {
            Tracker t = getGoogleAnalyticsTracker();
            t.send(new HitBuilders.ExceptionBuilder()
            .setDescription(new StandardExceptionParser(this, null).getDescription(Thread.currentThread().getName(), e))
            .setFatal(false)
                    .build()
            );
        }
    }

    public void trackEvent(String category, String action, String label) {
        Tracker t = getGoogleAnalyticsTracker();
        // build and send an event
        t.send(new HitBuilders.EventBuilder().setCategory(category).setAction(action).setLabel(label).build());
    }
}
