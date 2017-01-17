package com.karenpownall.android.aca.musicmachine;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

//handle intents one at a time on separate thread
public class DownloadIntentService extends IntentService {

    private static final String TAG = DownloadIntentService.class.getSimpleName();

    public DownloadIntentService(){
        super("DownloadIntentService");
            //name of thread where intent service does work
        setIntentRedelivery(true);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        String song = intent.getStringExtra(MainActivity.KEY_SONG);
        downloadSong(song);
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
}
