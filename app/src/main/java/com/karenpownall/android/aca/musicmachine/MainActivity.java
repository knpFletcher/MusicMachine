package com.karenpownall.android.aca.musicmachine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Button mDownloadBtn;

    public static final String KEY_SONG = "song";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDownloadBtn = (Button) findViewById(R.id.downloadBtn);

        mDownloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //start downloading playlist 1 song at a time
                Toast.makeText(MainActivity.this, "Downloading", Toast.LENGTH_SHORT)
                        .show();

                //Send Messages to Handler for processing
                for (String song : Playlist.songs){
                    Intent intent = new Intent(MainActivity.this, DownloadService.class);
                    intent.putExtra(KEY_SONG, song);
                    startService(intent);
                }
            }
        });
    }
}
