package com.example.kusnadi.myapplication.gcm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.nostra13.universalimageloader.core.ImageLoader;

import com.example.kusnadi.myapplication.data.AppConfig;
import com.example.kusnadi.myapplication.data.SharedPref;

public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {
    private static int VIBRATION_TIME = 500; // in millisecond
    private SharedPref sharedPref;
    private ImageLoader imgloader = ImageLoader.getInstance();

    @Override
    public void onReceive(Context context, Intent intent) {
        sharedPref = new SharedPref(context);

        sharedPref.setRefreshPlaces(true);
        if(imgloader.isInited() && AppConfig.REFRESH_IMG_NOTIF){
            imgloader.clearDiskCache();
            imgloader.clearMemoryCache();
        }

        if(sharedPref.getNotification()){
            playRingtoneVibrate(context);
            startWakefulService(context, (intent.setComponent(new ComponentName(context.getPackageName(), GcmIntentService.class.getName()))));
        }
    }


    private void playRingtoneVibrate(Context context){
        try {
            // play vibration
            if (sharedPref.getVibration()) {
                ((Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(VIBRATION_TIME);
            }
            RingtoneManager.getRingtone(context, Uri.parse(sharedPref.getRingtone())).play();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
