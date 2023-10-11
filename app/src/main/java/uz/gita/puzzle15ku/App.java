package uz.gita.puzzle15ku;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MyShare.init(this);
    }
}
