package uz.gita.puzzle15ku.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

//import uz.gita.puzzle15ku.MediaplayerAs;
import uz.gita.puzzle15ku.R;

public class AboutActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
//    private MediaplayerAs musicplayer3;
    MediaPlayer musicBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
//        musicplayer3 = MediaplayerAs.getInstance(this);
//        sharedPreferences = this.getSharedPreferences("PUZZLE", MODE_PRIVATE);
        musicBtn = MediaPlayer.create(this, R.raw.musicbtn);
        findViewById(R.id.finish1).setOnClickListener(v -> {
            musicBtn.start();
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
            finishAffinity();
        });

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

//        if (sharedPreferences.getBoolean("music", true)) {
//            musicplayer3.startMusic();
//        }
    }

}