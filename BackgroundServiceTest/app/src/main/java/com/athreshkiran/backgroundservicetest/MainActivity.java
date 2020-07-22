package com.athreshkiran.backgroundservicetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManagerCompat= NotificationManagerCompat.from(this);
    }


//    public void sendOnChannel1(View view) {
//        String title="Test notification launch 1";
//        Notification notification=new NotificationCompat.Builder(this,CHANNEL_1_ID)
//                .setSmallIcon(R.drawable.ic_baseline_call_made_24)
//                .setContentText(title)
//                .setPriority(NotificationManager.IMPORTANCE_HIGH)
//                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
//                .build();
//        notificationManagerCompat.notify(1,notification);
//    }
//
//
//    public void sendOnChannel2(View view) {
//        String title="Test notification launch 2";
//        Notification notification=new NotificationCompat.Builder(this,CHANNEL_2_ID)
//                .setSmallIcon(R.drawable.ic_baseline_call_made_24)
//                .setContentText(title)
//                .setPriority(NotificationManager.IMPORTANCE_LOW)
//                .build();
//        notificationManagerCompat.notify(2,notification);
//    }

    public void startService(View view) {
        Intent serviceIntent = new Intent(this,RandomNumService.class);
        startService(serviceIntent);
    }

    public void stopService(View view) {
        Intent serviceIntent = new Intent(this,RandomNumService.class);
        stopService(serviceIntent);
    }
}