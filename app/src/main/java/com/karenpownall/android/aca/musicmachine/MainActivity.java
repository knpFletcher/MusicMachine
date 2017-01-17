package com.karenpownall.android.aca.musicmachine;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String KEY_SONG = "song";
    private boolean mBound = false;
    private PlayerService mPlayerService;
    private Button mDownloadBtn;
    private Button mPlayBtn;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            mBound = true;
            PlayerService.LocalBinder localBinder = (PlayerService.LocalBinder) binder;
            mPlayerService = localBinder.getService();
            if (mPlayerService.isPlaying()){
                mPlayBtn.setText("Pause");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDownloadBtn = (Button) findViewById(R.id.downloadBtn);
        mPlayBtn = (Button) findViewById(R.id.playBtn);

        mDownloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //start downloading playlist 1 song at a time
                Toast.makeText(MainActivity.this, "Downloading", Toast.LENGTH_SHORT)
                        .show();

                //Send Messages to Handler for processing
                for (String song : Playlist.songs){
                    Intent intent = new Intent(MainActivity.this, DownloadIntentService.class);
                    intent.putExtra(KEY_SONG, song);
                    startService(intent);
                }
            }
        });

        mPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBound){
                    //button to show if music is playing or not
                    if (mPlayerService.isPlaying()){
                        mPlayerService.pause();
                        mPlayBtn.setText("Play");
                    } else {
                        Intent intent = new Intent(MainActivity.this, PlayerService.class);
                        startService(intent);
                        mPlayerService.play();
                        mPlayBtn.setText("Pause");
                    }
                }

            }
        });
    } //end onCreate

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, PlayerService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
            //serviceConnect used when successfully connected/disconnected
            //service automatically created when bound
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mServiceConnection);
            mBound = false;
        }
    }


} //end MainActivity
