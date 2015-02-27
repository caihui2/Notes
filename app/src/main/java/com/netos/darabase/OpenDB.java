package com.netos.darabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chyang on 15-2-25.
 */
public class OpenDB  extends SQLiteOpenHelper {
    public OpenDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table notes(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "title TEXT,content TEXT," +
                "time VARCHAR(20)," +
                "collection INTEGER," +
                "noteType VARCHAR(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
