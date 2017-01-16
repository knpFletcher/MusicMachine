package com.karenpownall.android.aca.musicmachine;

import android.util.Log;

public class DownloadThread extends Thread {

    private static final String TAG = DownloadThread.class.getSimpleName();

    @Override
    public void run() {
        downloadSong();
    }

    //simulate download
    //too much work on main UI, put on new thread
    private void downloadSong() {
        long endTime = System.currentTimeMillis() + 10*1000;
        while ( System.currentTimeMillis() < endTime){
            try {
                Thread.sleep(1000);
                //wait for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG, "Song downloaded!");
    }
}
