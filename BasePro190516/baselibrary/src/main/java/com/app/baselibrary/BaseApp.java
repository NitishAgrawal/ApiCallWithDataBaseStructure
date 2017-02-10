package com.app.baselibrary;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.app.baselibrary.dbhelperBase.DatabaseHandler;
import com.app.baselibrary.utils.PreferenceManager;

public abstract class BaseApp extends Application {

    private static Context context;
    public static String packageName;// = context.getPackageName();

    public void onCreate(){
        super.onCreate();
        BaseApp.context = getApplicationContext();
        init();
    }

    private void init() {
        initDatabaseSchema();
        PreferenceManager.initPreferences(BaseApp.getAppContext());
        initDatabase();
    }

    protected abstract void initDatabaseSchema();

    private void initDatabase() {
        if(BaseStru.getDbSchema() != null)
            DatabaseHandler.initDB();
    }



    public static Context getAppContext() {
        return BaseApp.context;
    }

    public static void alertToast(String massage){
        alertToast(massage,false);
    }

    public static void alertToast(String massage,boolean isLong){
        Toast.makeText(BaseApp.getAppContext(),massage,(isLong?Toast.LENGTH_LONG:Toast.LENGTH_SHORT)).show();
    }
    public abstract String getPackName();
}