package com.athreshkiran.backgroundservicetest;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    public static final String CHANNEL_ID="RandNumNotifService";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();

    }
    private void createNotificationChannels(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID, "Random Number Notification from Service", NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(serviceChannel);
        }
    }
}
