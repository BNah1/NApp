package com.example.napp.data.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class SqliteDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "my_database.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "my_table";

    private  static SqliteDatabaseHelper instance;

    public static synchronized SqliteDatabaseHelper getInstance(Context context) {
        if( instance == null){
            instance = new SqliteDatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    public SqliteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "id TEXT PRIMARY KEY, " +
                "image INTEGER, " +
                "name TEXT, " +
                "description TEXT)";
        db.execSQL(createTable);
        Log.d("Database", "Creating table: " + TABLE_NAME);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS my_table");
        onCreate(db);
    }
}
