package com.example.bradperkins.project1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by bradperkins on 1/8/16.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection, MediaController.MediaPlayerControl, CompoundButton.OnCheckedChangeListener  {

    MusicService mService = new MusicService();
    boolean mBound;

    static Button pauseBtn;

    public static String artistName;
    public static String songName;
    public static Bitmap albumCover;


    public static TextView titleLabel;
    public static TextView artistLabel;
    public static ImageView albumImage;

    public static SeekBar seekBar;
    public int seekBarPos;

    ToggleButton loopToggle;

    public static boolean looping;

    public static int songIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pauseBtn = (Button)findViewById(R.id.pauseBtn);

        findViewById(R.id.playBtn).setOnClickListener(this);
        findViewById(R.id.stopBtn).setOnClickListener(this);
        findViewById(R.id.pauseBtn).setOnClickListener(this);
        findViewById(R.id.prevBtn).setOnClickListener(this);
        findViewById(R.id.nextBtn).setOnClickListener(this);

        titleLabel = (TextView) findViewById(R.id.titleLabel);
        artistLabel = (TextView) findViewById(R.id.artistLabel);
        albumImage = (ImageView)findViewById(R.id.albumCover);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        loopToggle = (ToggleButton)findViewById(R.id.loopToggle);

        loopToggle.setOnCheckedChangeListener(this);

        mService.seekBar();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("ARTIST", artistName);
        outState.putString("SONG", songName);
        outState.putParcelable("ALBUM", albumCover);
        //outState.putDouble("SEEK", mService.curr);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        artistLabel.setText(savedInstanceState.getString("ARTIST"));
        titleLabel.setText(savedInstanceState.getString("SONG"));
        albumImage.setImageBitmap((Bitmap) savedInstanceState.getParcelable("ALBUM"));
        //seekBar.setProgress(Integer.parseInt("SEEK"));

    }

    //Looping Toggle Button
    @Override
    public void onCheckedChanged(CompoundButton loopToggle, boolean isChecked) {
        if(isChecked){
            looping = true;
        }
        else {
            looping = false;
        }
    }


    public void onClick(View view){
        Intent intent = new Intent(this, MusicService.class);
        if(view.getId() == R.id.playBtn){
            pauseBtn.setEnabled(true);
            startService(intent);
            bindService(intent, this, Context.BIND_AUTO_CREATE);
            mBound = true;
            mService.play(songIndex);
        }
        else if(view.getId() == R.id.stopBtn) {
            mService.stop();
            pauseBtn.setEnabled(false);
            stopService(intent);
        }
        else if (view.getId() == R.id.pauseBtn){
            pause();
        }
        else if (view.getId() == R.id.prevBtn){
            prev();
        }
        else if (view.getId() == R.id.nextBtn){
            next();
        }
    }

    public void next() {
        if (mBound = true){
            mService.next();
        }
    }

    public void prev() {
        if (mBound = true){
            mService.prev();
        }
    }

    @Override
    public void start() {

    }

    public void pause(){
        if (mBound = true) {
            mService.pause();
        }
    }

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public int getCurrentPosition() {
        return 0;
    }

    @Override
    public void seekTo(int pos) {

    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return false;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MusicService.class);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mService.seekUpdate.run();
        unbindService(this);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MusicService.MusicServiceBinder binder = (MusicService.MusicServiceBinder)service;
        mService = binder.getService();
        mBound = true;
        Log.i("MainActivity", "onServiceConnected");
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
    }


}
