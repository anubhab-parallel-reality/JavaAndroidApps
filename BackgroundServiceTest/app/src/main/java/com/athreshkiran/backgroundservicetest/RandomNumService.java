package com.athreshkiran.backgroundservicetest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.security.Provider;

import static com.athreshkiran.backgroundservicetest.App.CHANNEL_ID;

public class RandomNumService extends android.app.Service {
    private final IBinder mBinder = new ServiceBinder();

    public class ServiceBinder extends Binder {
        RandomNumService getService() {
            return RandomNumService.this;
        }
    }
    public static final double RandomNumber=Math.round(Math.random()*100000);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public double getRandomNumber() {
        return RandomNumber;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Intent fsIntent = new Intent(this, FullscreenActivity.class);
        PendingIntent fsPendingIntent = PendingIntent.getActivity(this, 0, fsIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification =new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentText(Double.toString(RandomNumber))
                .setContentTitle("Random Number Service")
                .setSmallIcon(R.drawable.ic_baseline_call_made_24)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_CALL)
                .setFullScreenIntent(fsPendingIntent, true)
                .build();
        startForeground(1,notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
