package uz.gita.puzzle15ku;

import android.content.Context;
import android.media.MediaPlayer;
//
//public class MediaplayerAs {
//
//    private static MediaplayerAs obj;
//    private Context context;
//
//    private MediaPlayer mediaPlayer;
//    private MediaPlayer mediaPlayerbtn;
//
//
//    private MediaplayerAs(Context context) {
//        this.context = context;
//        mediaPlayer = MediaPlayer.create(context, R.raw.gulbadan);
//        mediaPlayerbtn = MediaPlayer.create(context, R.raw.musicbtn);
//    }
//
//    public static MediaplayerAs getInstance(Context context) {
//        if (obj == null) obj = new MediaplayerAs(context);
//        return obj;
//    }
//
//    public void startMusic() {
//        mediaPlayer.start();
//        mediaPlayer.setOnCompletionListener(v -> startMusic());
//    }
//
//    public void startmusicbtn() {
//        mediaPlayerbtn.start();
//    }
//
//    public void stopMusic() {
//        mediaPlayer.pause();
//    }
//
//    public boolean isPlay() {
//        return mediaPlayer.isPlaying();
//    }
//
//}
