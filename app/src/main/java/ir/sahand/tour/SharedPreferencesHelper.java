package ir.sahand.tour;

import android.util.Log;

import java.util.HashMap;

public class SharedPreferencesHelper {
    private static HashMap<String, String> memory = new HashMap<>();

    public static String get(String key) {
        return memory.get(key);
    }

    public static void set(String key, String value) {
        Log.d("Sahand", "Token is:" + value);
        memory.put(key, value);
    }
}
