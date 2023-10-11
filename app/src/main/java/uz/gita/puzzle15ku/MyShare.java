package uz.gita.puzzle15ku;

import android.content.Context;
import android.content.SharedPreferences;

public class MyShare {

    private static MyShare obj;
    private Context context;


    private MyShare(Context context) {
        this.context = context;
    }


    public static MyShare init(Context context) {
        if (obj != null) obj = new MyShare(context);
        return obj;
    }

    public void isbool() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PUZZLE", Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("music", true);
    }
}
