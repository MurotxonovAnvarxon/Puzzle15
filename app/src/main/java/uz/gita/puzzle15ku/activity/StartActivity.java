package uz.gita.puzzle15ku.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import uz.gita.puzzle15ku.MainActivity;
//import uz.gita.puzzle15ku.MediaplayerAs;
import uz.gita.puzzle15ku.MyShare;
import uz.gita.puzzle15ku.R;
import uz.gita.puzzle15ku.activity.AboutActivity;

public class StartActivity extends AppCompatActivity {


  public  SharedPreferences sharedPreferences;

    ImageView musicOn;
//    MediaplayerAs musicplayer;
//    MediaplayerAs musicPlayerBtn;
    MediaPlayer musicBtn;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        sharedPreferences = this.getSharedPreferences("PUZZLE", MODE_PRIVATE);
        musicOn = findViewById(R.id.musicon);


        sharedPreferences.edit().putBoolean("music", true).apply();
//        musicplayer = MediaplayerAs.getInstance(this);
//        musicPlayerBtn = MediaplayerAs.getInstance(this);


        musicBtn = MediaPlayer.create(this, R.raw.musicbtn);
//        musicOn.setOnClickListener(view -> {
//            if (!musicplayer.isPlay()) {
//                musicplayer.startMusic();
//                sharedPreferences.edit().putBoolean("music", false).apply();
//                musicOn.setImageResource(R.drawable.music1);
//            } else {
//                musicplayer.stopMusic();
//                sharedPreferences.edit().putBoolean("music", true).apply();
//                musicOn.setImageResource(R.drawable.music);
//            }
//        });
//
//        if (sharedPreferences.getBoolean("music", false)) {
//            musicOn.setImageResource(R.drawable.music1);
//            musicplayer.startMusic();
//        } else {
//            musicOn.setImageResource(R.drawable.music);
//            musicplayer.startMusic();
//        }


        findViewById(R.id.start).setOnClickListener(v -> {
            musicBtn.start();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        });
        findViewById(R.id.about).setOnClickListener(v -> {
            musicBtn.start();
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        });

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        findViewById(R.id.exit).setOnClickListener(v -> {
            musicBtn.start();
            new AlertDialog.Builder(this)
                    .setTitle("Really Exit?")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, (arg0, arg1) -> {
                        System.exit(0);
                        finishAffinity();
                    }).create().show();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
//        musicplayer.stopMusic();
        sharedPreferences.edit().putBoolean("music",false).apply();
    }

    @Override
    public void onBackPressed() {
//        musicplayer.stopMusic();
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, (arg0, arg1) -> {
                    System.exit(0);
                    finishAffinity();
                }).create().show();
    }
}
