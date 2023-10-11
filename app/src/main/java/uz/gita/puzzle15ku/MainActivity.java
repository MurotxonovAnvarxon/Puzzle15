package uz.gita.puzzle15ku;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import uz.gita.puzzle15ku.activity.StartActivity;

public class MainActivity extends AppCompatActivity {
    TextView[][] views = new TextView[4][4];
    RelativeLayout relativeLayout;
    ArrayList<Integer> numbers = new ArrayList<>(16);
    private Button moveCountText;
    int indexI = -1;
    ImageView musicBtnOn;
    MediaPlayer musicBtn;
    int indexJ = -1;
    private static int moveCount = 0;
    Chronometer chronometer;
    private boolean bool2 = true;


    @SuppressLint({"MissingInflatedId", "SetTextI18n", "CutPasteId"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musicBtn = MediaPlayer.create(this, R.raw.musicbtn);

        moveCount = 0;
        Button score = findViewById(R.id.move);
        moveCountText = findViewById(R.id.move);
        chronometer = findViewById(R.id.chronometer);
        musicBtnOn = findViewById(R.id.buttonmusic);


        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        findViewById(R.id.finish).setOnClickListener(v -> {
            musicBtn.start();
            finishAffinity();
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
        });


        if (savedInstanceState != null) {
            moveCount = savedInstanceState.getInt("moves");
            score.setText(String.valueOf(moveCount));
        }


        findViewById(R.id.restart).setOnClickListener(v -> {
            musicBtn.start();
            moveCount = 0;
            chronometer.setFormat(null);
            chronometer.start();
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.setFormat("%s");
            moveCountText.setText("" + moveCount);
            Collections.shuffle(numbers);
            describeNumber();
            views[indexI][indexJ].setVisibility(View.INVISIBLE);
            loadViews();
        });

        musicBtnOn.setOnClickListener(view -> {
            setImageRes();
//            if (bool2) {
//                musicBtnOn.setImageResource(R.drawable.music);
//                bool2=false;
//            } else {
//                musicBtnOn.setImageResource(R.drawable.music1);
//                bool2=true;
//            }
        });

        loadViews();
        loadRandomNumbers();
        describeNumber();
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("moves", moveCount);
        setImageRes();
        relativeLayout = findViewById(R.id.relative);
        outState.putLong("time", chronometer.getBase() - SystemClock.elapsedRealtime());
        outState.putBoolean("bool", bool2);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < relativeLayout.getChildCount(); i++) {
            TextView textView = (TextView) relativeLayout.getChildAt(i);
            list.add(textView.getText().toString());
        }
        outState.putStringArrayList("buttons", list);
        super.onSaveInstanceState(outState);
    }

    private void setImageRes() {
        if (bool2) {
            musicBtnOn.setImageResource(R.drawable.music);
            bool2 = false;
        } else {
            musicBtnOn.setImageResource(R.drawable.music1);
            bool2 = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setImageRes();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setImageRes();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setImageRes();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        bool2 = savedInstanceState.getBoolean("bool");
        bool2 = !bool2;
        setImageRes();
        long deltaTime = savedInstanceState.getLong("time", 0);
        chronometer.setBase(SystemClock.elapsedRealtime() + deltaTime);
        ArrayList<String> list = savedInstanceState.getStringArrayList("buttons");


        for (int i = 0; i < 16; i++) {
            views[i / 4][i % 4].setVisibility(View.INVISIBLE);
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("")) {
                views[i / 4][i % 4].setVisibility(View.INVISIBLE);
                views[i / 4][i % 4].setText("");
                indexI = i / 4;
                indexJ = i % 4;
            } else {
                views[i / 4][i % 4].setVisibility(View.VISIBLE);
                views[i / 4][i % 4].setText(list.get(i));
            }
        }
        views[indexI][indexJ].setVisibility(View.INVISIBLE);
    }

    private void loadViews() {

        relativeLayout = findViewById(R.id.relative);
        moveCountText = findViewById(R.id.move);
        for (int i = 0; i < relativeLayout.getChildCount(); i++) {
            views[i / 4][i % 4] = (TextView) relativeLayout.getChildAt(i);
            views[i / 4][i % 4].setTag(new MyPair(i / 4, i % 4));
            views[i / 4][i % 4].setOnClickListener(v -> {
                MyPair data = (MyPair) v.getTag();
                canPermit(data.i, data.j);
            });
        }
    }

    private void loadRandomNumbers() {
        for (int i = 0; i < 16; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        while (!isSolvable()) {
            Collections.shuffle(numbers);
        }
        Chronometer chronometr = findViewById(R.id.chronometer);
        chronometr.start();
    }

    private void describeNumber() {
        for (int i = 0; i < 16; i++) {
            views[i / 4][i % 4].setVisibility(View.INVISIBLE);
        }
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) == 0) {
                views[i / 4][i % 4].setVisibility(View.INVISIBLE);
                indexJ = i % 4;
                indexI = i / 4;
            } else {
                views[i / 4][i % 4].setText(String.valueOf(numbers.get(i)));
                views[i / 4][i % 4].setVisibility(View.VISIBLE);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void canPermit(int i, int j) {
        boolean bool = (Math.abs(indexI - i) == 1 && indexJ == j) || (Math.abs(indexJ - j) == 1 && indexI == i);
        if (bool) {
            if (bool2) {
                musicBtn.start();
            }
            views[indexI][indexJ].setVisibility(View.VISIBLE);
            views[indexI][indexJ].setText(views[i][j].getText());
            views[i][j].setVisibility(View.INVISIBLE);
            views[i][j].setText("");
            indexI = i;
            indexJ = j;
            isWin();
            moveCount++;
            moveCountText.setText("" + moveCount);
            Chronometer chronometer = findViewById(R.id.chronometer);
            chronometer.start();
        }
    }

    private void isWin() {
        int count = 0;
        for (int i = 0; i < 15; i++) {
            if (views[i / 4][i % 4].getText().equals(String.valueOf(i + 1))) {
                count++;
            }
        }
        if (count == 15) {
            Intent intent = new Intent(this, AnimationActivity.class);
            startActivity(intent);
            finishAffinity();
        }
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, (arg0, arg1) -> {
                    MainActivity.super.onBackPressed();
                }).create().show();
    }

    public boolean isSolvable() {
        int parity = 0;

        int[] arr = new int[16];
        for (int i = 0; i < 16; i++) {
            arr[i] = numbers.get(i);
        }
        int gridWidth = (int) Math.sqrt(arr.length);
        int row = 0;
        int blankRow = 0;

        for (int i = 0; i < arr.length; i++) {
            if (i % gridWidth == 0) {
                row++;
            }
            if (arr[i] == 0) {
                blankRow = row;
                continue;
            }
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j] && arr[j] != 0) {
                    parity++;
                }
            }
        }

        if (gridWidth % 2 == 0) {
            if (blankRow % 2 == 0) {
                return parity % 2 == 0;
            } else {
                return parity % 2 != 0;
            }
        } else {
            return parity % 2 == 0;
        }
    }


}
