package com.test.health;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyService extends Service {
    private final String TAG = "MyService";
    private MediaPlayer mediaPlayer;
    private int startId;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");
        mediaPlayer = MediaPlayer.create(this, R.raw.test);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.startId = startId;
        Log.e(TAG, "onStartCommand---startId: " + startId + "ThreadId：" + Thread.currentThread().getId());
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String control = (String) bundle.getSerializable("Key");
            if (control.equals("play")) {
                start();
            }
            if (control.equals("stop")) {
                stop();
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void start() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    private void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        stopSelf(startId);//自己把相应的服务停了

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

    }
}
