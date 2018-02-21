package projects.in.projectstructure.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by prime on 16-12-2017.
 */

public class SharedPreferenceManager {
    private Context context;
    private SharedPreferences prefs;
    private Editor editor;
    private static final String PREFS_NAME = "tsmmrecommerce";

    private static SharedPreferenceManager sharedPreferenceManager;

    public SharedPreferenceManager() {
    }

    public SharedPreferenceManager(Context context) {
        this.context = context;

        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public static SharedPreferenceManager getInstance(Context context) {
        if (sharedPreferenceManager == null) {
            sharedPreferenceManager = new SharedPreferenceManager(context);
        }

        return sharedPreferenceManager;
    }

    public void clearDB() {
        editor.clear();
    }

    public void setInt(String key, int value) {
        editor.putInt(key, value).commit();
    }

    public int getInt(String key) {
        return prefs.getInt(key, 0);
    }

    public void setFloat(String key, float value) {
        editor.putFloat(key, value).commit();
    }

    public float getFloat(String key) {
        return prefs.getFloat(key, 0);
    }

    public void setBoolean(String key, boolean value) {
        editor.putBoolean(key, value).commit();
    }

    public boolean getBoolean(String key) {
        return prefs.getBoolean(key, false);
    }

    public void setString(String key, String value) {
        editor.putString(key, value).commit();
    }

    public String getString(String key) {
        return prefs.getString(key, "");
    }
}
