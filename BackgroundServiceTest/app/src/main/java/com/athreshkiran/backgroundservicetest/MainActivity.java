package com.athreshkiran.backgroundservicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {
    private RandomNumService mBoundedService;
    TextView message;
    boolean mIsBound = false;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBoundedService = ((RandomNumService.ServiceBinder) iBinder).getService();
            //we can also separately create that temporary service class separately ad then get the service instance.
            //to do that we go :
            //RandomNumService.ServiceBinder tempservice=(RandomNumService.ServiceBinder)iBinder;
            //and then
            //mBoundedService=tempservice.getService();
            //but its pretty pointless.
            mIsBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBoundedService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        message = (TextView) findViewById(R.id.servermessage);
    }

    public void bindTheService() {

        Intent intent = new Intent(this, RandomNumService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }

    public void unbindTheService() {

        Intent serviceIntent = new Intent(this, RandomNumService.class);
        if (mIsBound) {
            unbindService(mConnection);
            mIsBound = false;
        }

    }

    public void startService(View view) {
        Intent serviceIntent = new Intent(this, RandomNumService.class);

        new Handler().postDelayed(()->{
            //starting service
            startService(serviceIntent);
            //binding service
            bindTheService();
            //so the reason why started the service first and then bounded it is cause by doing so, the service wont be destroyed even if the binding is removed
            //or even if the app is destroyed,the service will keep on running as it has not started cause of the bind, its independent.
            //but if we want to start only when we bind the we just remove the startService line, the "BIND_AUTO_CREATE" will take check if exist or not and start a
            // "Bounded service " if needed.


            // For some reason i noticed that the binding takes time and we cant access the service instance immediately as it will return null
            //so i set a delay of a second and then tried accessing it.
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                double randomNumberReceived = mBoundedService.getRandomNumber();
                runOnUiThread(() -> message.setText(Double.toString(randomNumberReceived)));
            }, 1000);

            //so we get the random value inside the service and update the textview .
        }, 5000);
    }

    public void stopService(View view) {
        unbindTheService();
        Intent serviceIntent = new Intent(this, RandomNumService.class);
        stopService(serviceIntent);

    }

    public void updateMessage(View view) {
        if (mBoundedService != null) {
            double randomNumberReceived = mBoundedService.getRandomNumber();
            message.setText(Double.toString(randomNumberReceived));
        } else {
            message.setText("The service has not been started or binded yet");
        }
    }

    public void bindButton(View view) {
        bindTheService();
    }

    public void unbindButton(View view) {
        unbindTheService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbindTheService();
    }
}