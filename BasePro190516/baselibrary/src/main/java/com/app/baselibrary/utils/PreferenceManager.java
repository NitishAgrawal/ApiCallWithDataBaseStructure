package com.app.baselibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.app.baselibrary.BaseStru;
import static android.content.Context.MODE_PRIVATE;

public class PreferenceManager {

    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    public static void initPreferences(Context mContext) {
        preferences = mContext.getSharedPreferences(BaseStru.getSharedPreferencesName(), MODE_PRIVATE);
        editor = preferences.edit();
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }

    public  SharedPreferences.Editor getPreferencesEditor() {
        return editor;
    }

}
