package com.karenpownall.android.aca.musicmachine;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button mDownloadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //prepare thread for use before button click,
        // avoid null-pointer exception while download work is happening on thread
        //need to use 1 thread with message queue
        final DownloadThread thread = new DownloadThread();
        thread.setName("DownloadThread");
        thread.start();

        mDownloadBtn = (Button) findViewById(R.id.downloadBtn);

        mDownloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //start downloading playlist 1 song at a time
                Toast.makeText(MainActivity.this, "Downloading", Toast.LENGTH_SHORT)
                        .show();

                //Send Messages to Handler for processing
                for (String song : Playlist.songs){
                    Message message = Message.obtain();
                    message.obj = song;
                        //add any object to message
                    thread.mHandler.sendMessage(message);
                }
            }
        });
    }

}
