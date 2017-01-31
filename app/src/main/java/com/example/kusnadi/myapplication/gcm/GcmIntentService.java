package com.example.kusnadi.myapplication.gcm;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.gson.Gson;

import com.example.kusnadi.myapplication.ActivityPlaceDetail;
import com.example.kusnadi.myapplication.ActivitySplash;
import com.example.kusnadi.myapplication.R;
import com.example.kusnadi.myapplication.model.GcmNotif;
import com.example.kusnadi.myapplication.model.Place;

public class GcmIntentService extends IntentService {
    private static final String TAG = GcmIntentService.class.getName();

    public GcmIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        showGcmNotif(intent);
    }

    private void showGcmNotif(Intent intent) {
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(intent);
        //Toast.makeText(this, "messageType : " + messageType, Toast.LENGTH_SHORT).show();
        if (!intent.getExtras().isEmpty()) {
            if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                GcmNotif gcmNotif = new GcmNotif();
                gcmNotif.setTitle(intent.getStringExtra("title"));
                gcmNotif.setContent(intent.getStringExtra("content"));

                // load data place if exist
                String place_str = intent.getStringExtra("place");
                Place place = place_str != null ? new Gson().fromJson(place_str, Place.class) : null;
                gcmNotif.setPlace(place);
                displayNotificationIntent(gcmNotif);
            }
        }
    }

    private void displayNotificationIntent(GcmNotif gcmNotif) {
        Intent intent = new Intent(this, ActivitySplash.class);
        // handle notification for open Place Details
        if(gcmNotif.getPlace() != null){
            intent = new Intent(this, ActivityPlaceDetail.class);
            intent.putExtra(ActivityPlaceDetail.EXTRA_OBJ, gcmNotif.getPlace());
            intent.putExtra(ActivityPlaceDetail.EXTRA_NOTIF_FLAG, true);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle(gcmNotif.getTitle());
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(gcmNotif.getContent()));
        builder.setContentText(gcmNotif.getContent());
        builder.setSmallIcon(R.drawable.ic_notification);
        builder.setDefaults(Notification.DEFAULT_LIGHTS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            builder.setPriority(Notification.PRIORITY_HIGH);
        }
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        int unique_id = (int) System.currentTimeMillis();
        notificationManager.notify(unique_id, builder.build());

    }
}
