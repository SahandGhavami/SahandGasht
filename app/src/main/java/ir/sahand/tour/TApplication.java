package ir.sahand.tour;

import android.app.Application;
import android.content.Context;

public class TApplication extends Application {
    public static volatile Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
    }
}
