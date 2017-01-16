package com.karenpownall.android.aca.musicmachine;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

//service performs long running operations in background without UI
//need to add service to manifest
public class DownloadService extends Service {
    private static final String TAG = DownloadService.class.getSimpleName();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //used for single operation, service stops itself or will run as long as device has power
        String song = intent.getStringExtra(MainActivity.KEY_SONG);
        downloadSong(song);
        return Service.START_REDELIVER_INTENT;
    }

    private void downloadSong(String song) {
        long endTime = System.currentTimeMillis() + 10*1000;
        while ( System.currentTimeMillis() < endTime){
            try {
                Thread.sleep(1000);
                //wait for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, song + " downloaded!");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //use service as Server in client/server model
        return null;
    }


}
