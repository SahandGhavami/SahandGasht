package ir.sahand.tour;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import ir.sahand.tour.model.SignupResponse;

public class AppPreferenceTools {
    public String STRING_PREF_UNAVAILABLE = " String Preferences Unavailable";

    private SharedPreferences mPreferences;

    private static AppPreferenceTools instance;

    public static AppPreferenceTools getInstance() {
        return getInstance(null);
    }

    public static AppPreferenceTools getInstance(Context context) {
        if (instance == null) {
            instance = new AppPreferenceTools(context);
        }

        return instance;
    }

    private AppPreferenceTools(Context context) {
        this.mPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
    }

    public void saveUserAuthenticationInfo(SignupResponse signupResponse) {
        mPreferences.edit()
                .putString("Token", signupResponse.getToken())
                .apply();
    }

    public String getToken() {
        return mPreferences.getString("Token", STRING_PREF_UNAVAILABLE);
    }

    public Boolean isAuthorized() {
        return !getToken().equals(STRING_PREF_UNAVAILABLE);
    }

    public void removeAllPrefs() {
        mPreferences.edit().clear().apply();
    }
}
