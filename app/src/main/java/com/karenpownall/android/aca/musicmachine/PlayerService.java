package com.karenpownall.android.aca.musicmachine;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

//use service as Server in client/server model
//communicate with service after starting
public class PlayerService extends Service {
    //play and pause single song, do work in background while activity is closed

    private static final String TAG = PlayerService.class.getSimpleName();
    private MediaPlayer mPlayer;
    private IBinder mBinder = new LocalBinder();

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        mPlayer = MediaPlayer.create(this,R.raw.jingle);
            //mediaPlayer only needs to be created once
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            //playback 1 song, then stop regardless of how many times started
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopSelf();
                    //stops service immediately regardless of if its working or not
            }
        });
        return Service.START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //IBinder enables service and activity to communicate
        Log.d(TAG, "onBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        mPlayer.release();
            //frees up system resources, best practices
    }

    public class LocalBinder extends Binder{
        public PlayerService getService(){
            return PlayerService.this;
        }
    }

    //Client Methods
    public void play(){
        mPlayer.start();
    }

    public void pause(){
        mPlayer.pause();
    }

    public boolean isPlaying(){
        return mPlayer.isPlaying();
    }

}
