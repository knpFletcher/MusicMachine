package com.karenpownall.android.aca.musicmachine;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class DownloadHandler extends Handler {

    private static final String TAG = DownloadHandler.class.getSimpleName();
    private DownloadService mService;

    @Override
    public void handleMessage(Message msg) {
        downloadSong(msg.obj.toString());
        mService.stopSelf(msg.arg1);
            //only stop service if handled all startIds

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

    public void setService(DownloadService service) {
        mService = service;
    }
}
