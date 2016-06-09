package com.app.baselibrary;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.app.baselibrary.dbhelperBase.DatabaseHandler;

public abstract class BaseApp extends Application {

    private static Context context;
    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;
    public static String packageName;// = context.getPackageName();

    public void onCreate(){
        super.onCreate();
        BaseApp.context = getApplicationContext();
        init();
    }

    private void init() {
        initDatabaseSchema();
        initShardPreferences();
        initPreferences();
        initDatabase();
    }

    protected abstract void initDatabaseSchema();
    protected abstract void initShardPreferences();

    private void initDatabase() {
        if(BaseStru.getDbSchema() != null)
            DatabaseHandler.initDB();
    }

    private void initPreferences() {
        pref = BaseApp.getAppContext().getSharedPreferences(BaseStru.getSharedPreferencesName(), MODE_PRIVATE);
        editor = pref.edit();
    }

    public static Context getAppContext() {
        return BaseApp.context;
    }

    public static SharedPreferences getPreferences() {
        return BaseApp.pref;
    }

    public static SharedPreferences.Editor getPreferencesEditor() {
        return editor;
    }

    public static void alertToast(String massage){
        alertToast(massage,false);
    }

    public static void alertToast(String massage,boolean isLong){
        Toast.makeText(BaseApp.getAppContext(),massage,(isLong?Toast.LENGTH_LONG:Toast.LENGTH_SHORT)).show();
    }
    public abstract String getPackName();
}