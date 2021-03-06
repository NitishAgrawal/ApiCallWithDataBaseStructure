package com.app.baselibrary.dbhelperBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.app.baselibrary.BaseApp;
import com.app.baselibrary.BaseStru;

import java.util.HashMap;
import java.util.Map;


public class DatabaseHandler extends SQLiteOpenHelper {


    public static DatabaseHandler dbHandlerObj;
    public DatabaseHandler(Context context) {
        super(context, BaseStru.getDatabaseName(), null, BaseStru.getDatabaseVersion());
    }

    public static void initDB(){
        //TODO check working proper or not
        dbHandlerObj = new DatabaseHandler(BaseApp.getAppContext());
        SQLiteDatabase db = dbHandlerObj.getReadableDatabase();
        db.close();
    }

    public static Map<String, HashMap<String,String>> dbSchema = BaseStru.getDbSchema();

    // Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
        Log.e("Database","working1");
        for (Map.Entry<String, HashMap<String,String>> entry : dbSchema.entrySet())
        {
            String tableName = entry.getKey();
            HashMap<String,String> tableFields = entry.getValue();
            String createTable = "CREATE TABLE " + tableName + "(";
            String separator = "";

            for (Map.Entry<String,String> entry1 : tableFields.entrySet()) {
                String key = entry1.getKey();
                Object value = entry1.getValue();
                createTable += (separator + key + " " + value);
                separator = " , ";
            }

            createTable +=  ")";
            db.execSQL(createTable);
        }
        Log.e("Database","working2");
	}


    // Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (Map.Entry<String, HashMap<String,String>> entry : dbSchema.entrySet())
        {
            String tableName = entry.getKey();
            db.execSQL("DROP TABLE IF EXISTS " + tableName);
        }
		// Create tables again
		onCreate(db);
        Log.e("Database", "working3");
	}

}
