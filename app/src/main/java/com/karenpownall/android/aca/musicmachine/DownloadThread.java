package com.karenpownall.android.aca.musicmachine;

import android.os.Looper;

public class DownloadThread extends Thread {

    private static final String TAG = DownloadThread.class.getSimpleName();
    public DownloadHandler mHandler;

    @Override
    public void run() {
        Looper.prepare();
            //creates looper for thread, and creates message queue
        mHandler = new DownloadHandler();
            //handler is associated with looper for current thread
        Looper.loop();
            //start looping over message queue
    }
}
