package com.karenpownall.android.aca.musicmachine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

        mDownloadBtn = (Button) findViewById(R.id.downloadBtn);

        mDownloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Downloading", Toast.LENGTH_SHORT)
                        .show();

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        downloadSong();
                    }
                };

                Thread thread = new Thread(runnable);
                thread.setName("DownloadThread");
                thread.start();
            }
        });
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
