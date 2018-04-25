package com.manu.lumos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by manugupta on 23/02/17.
 */

public class DatabaseSequel extends SQLiteOpenHelper {

    public DatabaseSequel(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
  //   super(context, name, factory, version);

  // public DatabaseSequel(Context context) {
          super(context, "Databasedb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            String str="CREATE TABLE Credits( _id INTEGER PRIMARY KEY, Name TEXT, Phoneno NUMERIC, Username TEXT, Email TEXT, Password TEXT);";
            db.execSQL(str);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
