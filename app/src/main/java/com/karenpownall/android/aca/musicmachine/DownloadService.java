package com.karenpownall.android.aca.musicmachine;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

//service performs long running operations in background without UI
//need to add service to manifest
//needs to run on separate thread
public class DownloadService extends Service {

    private DownloadHandler mHandler;

    @Override
    public void onCreate() {
        DownloadThread thread = new DownloadThread();
        thread.setName("DownloadThread");
        thread.start();

        while (thread.mHandler == null){
            //not best practice, makes thread wait until not null
        }
        mHandler = thread.mHandler;
        mHandler.setService(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //used for single operation, service stops itself or will run as long as device has power
        String song = intent.getStringExtra(MainActivity.KEY_SONG);
        Message message = Message.obtain();
        message.obj = song;
        message.arg1 = startId;
        mHandler.sendMessage(message);

        return Service.START_REDELIVER_INTENT;
            //started if method returns value
            //stopped only when explicitly stopped
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //use service as Server in client/server model
        //communicate with service after starting
        return null;
    }


}
