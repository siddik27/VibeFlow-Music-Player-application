//package com.example.vibeflow;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.icu.text.TimeZoneFormat;
//import android.icu.text.Transliterator;
//import android.media.MediaPlayer;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.SeekBar;
//import android.widget.TextView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import java.io.File;
//import java.util.ArrayList;
//
//public class PlaySong extends AppCompatActivity {
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mediaPlayer.stop();
//        mediaPlayer.release();
//        updateSeek.interrupt();
//    }
//
//    TextView textView;
//ImageView play,pause,next,previous;
//ArrayList <File> songs;
//MediaPlayer mediaPlayer;
//String textContent;
//int position;
//SeekBar seekBar;
//Thread updateSeek;
//TextView currentTimer,totalTimer;
//
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_play_song);
//        play =findViewById(R.id.play);
//        next =findViewById(R.id.next);
//        previous =findViewById(R.id.previous);
//        textView =findViewById(R.id.textView);
//        seekBar = findViewById(R.id.seekBar);
//        currentTimer = findViewById(R.id.currentTimer);
//        totalTimer = findViewById(R.id.totalTimer);
//
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        assert bundle != null;
//        songs = (ArrayList) bundle.getParcelableArrayList("SongList");
//        textContent = intent.getStringExtra("currentSong");
//        textView.setText(textContent);
//        textView.setSelected(true);
//        position = intent.getIntExtra("position",0);
//        Uri uri = Uri.parse(songs.get(position).toString());
//        mediaPlayer = MediaPlayer.create(this,uri);
//        mediaPlayer.start();
//        seekBar.setMax(mediaPlayer.getDuration());
//        String totTime = createTimerLebel(mediaPlayer.getDuration());
//        totalTimer.setText(totTime);
//
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                mediaPlayer.seekTo(seekBar.getProgress());
//            }
//        });
//
//        updateSeek = new Thread(){
//            @Override
//            public void run() {
//                int currentPosition = 0;
//                try{
//                    while(currentPosition<mediaPlayer.getDuration()){
//                        currentPosition = mediaPlayer.getCurrentPosition();
//                        seekBar.setProgress(currentPosition);
//                        sleep(800);
//                    }
//                }
//                catch(Exception e){
//                    e.printStackTrace();
//                }
//            }
//        };
//        updateSeek.start();
//
//        //create handler to set progress
//        @SuppressLint("HandlerLeak") Handler handler  = new Handler(){
//
//            public void handelMessege(Message msg){
//                currentTimer.setText(createTimerLebel(msg.what));//set timer
//                seekBar.setProgress(msg.what);
//            }
//
//        };
//
//        play.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mediaPlayer.isPlaying()){
//                    play.setImageResource(R.drawable.play);
//                    mediaPlayer.pause();
//                }
//                else{
//                    play.setImageResource(R.drawable.pause);
//                    mediaPlayer.start();
//                }
//            }
//        });
//
//        previous.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mediaPlayer.stop();
//                mediaPlayer.release();
//                if(position!=0){
//                    position = position -1;
//                }
//                else {
//                    position = songs.size()-1;
//                }
//                Uri uri = Uri.parse(songs.get(position).toString());
//                mediaPlayer = MediaPlayer.create(getApplicationContext(),uri);
//                mediaPlayer.start();
//                play.setImageResource(R.drawable.pause);
//                seekBar.setMax(mediaPlayer.getDuration());
//                textContent = songs.get(position).getName().toString();
//                textView.setText(textContent);
//
//            }
//        });
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mediaPlayer.stop();
//                mediaPlayer.release();
//                if(position != songs.size()-1){
//                    position = position + 1;
//                }
//                else {
//                    position = 0;
//                }
//                Uri uri = Uri.parse(songs.get(position).toString());
//                mediaPlayer = MediaPlayer.create(getApplicationContext(),uri);
//                mediaPlayer.start();
//                play.setImageResource(R.drawable.pause);
//                seekBar.setMax(mediaPlayer.getDuration());
//                textContent = songs.get(position).getName().toString();
//                textView.setText(textContent);
//
//            }
//        });
//
//
//
//
//
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//    }
//    public String createTimerLebel( int duration){
//        String timerLebel = "";
//        int min = duration/1000/60;
//        int sec = duration/1000%60;
//
//        timerLebel += min + ":";
//        if(sec<10) timerLebel += "0";
//        timerLebel+=sec;
//        return timerLebel;
//    }
//}

package com.example.vibeflow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.util.ArrayList;

public class PlaySong extends AppCompatActivity {
    TextView textView;
    ImageView play, next, previous,music_image;
    ArrayList<File> songs;
    MediaPlayer mediaPlayer;
    String textContent;
    int position;
    SeekBar seekBar;
    TextView currentTimer, totalTimer;
    private Switch Mode_switch;
    TextView mode_Status;


    Handler handler = new Handler();
    Runnable updateSeekRunnable;

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Stop the Runnable and release mediaPlayer safely
        if (updateSeekRunnable != null) {
            handler.removeCallbacks(updateSeekRunnable);
        }

        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_play_song);

        play = findViewById(R.id.play);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);
        music_image = findViewById(R.id.music_image);
        textView = findViewById(R.id.textView);
        seekBar = findViewById(R.id.seekBar);
        currentTimer = findViewById(R.id.currentTimer);
        totalTimer = findViewById(R.id.totalTimer);
        Mode_switch = findViewById(R.id.Mode_Switch);
        mode_Status = findViewById(R.id.mode_Status);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        assert bundle != null;
        Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        songs = (ArrayList) bundle.getParcelableArrayList("SongList");
        textContent = intent.getStringExtra("currentSong");
        textView.setText(textContent);
        textView.setSelected(true);
        position = intent.getIntExtra("position", 0);
        Uri uri = Uri.parse(songs.get(position).toString());
        mediaPlayer = MediaPlayer.create(this, uri);
        mediaPlayer.start();
        music_image.startAnimation(rotate);//start the animation


        seekBar.setMax(mediaPlayer.getDuration());
        totalTimer.setText(createTimerLabel(mediaPlayer.getDuration()));

        //checking if the mode is already dark or light
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            mode_Status.setText("Dark Mode");
        }
        else{
            mode_Status.setText("Light Mode");

        }


        // To add the DARK MODE and LIGHT MODE  in your application.
        Mode_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });



        // Define the Runnable for updating the seek bar and timer
        updateSeekRunnable = new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    try {
                        int currentPosition = mediaPlayer.getCurrentPosition();
                        currentTimer.setText(createTimerLabel(currentPosition));
                        seekBar.setProgress(currentPosition);
                        handler.postDelayed(this, 1000); // Repeat every second
                    } catch (IllegalStateException e) {
                        e.printStackTrace();  // Catch any potential state issues with mediaPlayer
                    }
                }
            }
        };
        handler.postDelayed(updateSeekRunnable, 1000); // Initial delay

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && mediaPlayer != null) {
                    mediaPlayer.seekTo(progress);
                    currentTimer.setText(createTimerLabel(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mediaPlayer != null) {
                    mediaPlayer.seekTo(seekBar.getProgress());
                }
            }
        });

        play.setOnClickListener(v -> {


            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying()) {
                    play.setImageResource(R.drawable.play);
                    mediaPlayer.pause();
                    music_image.clearAnimation();//stop the animation
                } else {
                    play.setImageResource(R.drawable.pause);
                    mediaPlayer.start();
                    music_image.startAnimation(rotate);//start the animation
                }
            }
        });

        previous.setOnClickListener(v -> playPreviousSong());
        next.setOnClickListener(v -> playNextSong());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void playPreviousSong() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        position = (position != 0) ? position - 1 : songs.size() - 1;
        playSongAtPosition();
    }

    private void playNextSong() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        position = (position != songs.size() - 1) ? position + 1 : 0;
        playSongAtPosition();
    }

    private void playSongAtPosition() {
        Uri uri = Uri.parse(songs.get(position).toString());
        mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
        mediaPlayer.start();
        play.setImageResource(R.drawable.pause);
        seekBar.setMax(mediaPlayer.getDuration());
        textContent = songs.get(position).getName();
        textView.setText(textContent);
        totalTimer.setText(createTimerLabel(mediaPlayer.getDuration()));
    }

    private String createTimerLabel(int duration) {
        int min = duration / 1000 / 60;
        int sec = duration / 1000 % 60;
        return min + ":" + (sec < 10 ? "0" : "") + sec;
    }
}


