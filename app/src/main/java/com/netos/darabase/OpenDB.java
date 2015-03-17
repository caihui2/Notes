package com.netos.darabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chyang on 15-2-25.
 */
public class OpenDB  extends SQLiteOpenHelper {
    protected static final String TABLE_NAME_NOTES = "notes";
    protected static final String FIELD_NOTES__ID = "_id";
    protected static final String FIELD_NOTES_TITLE = "title";
    protected static final String FIELD_NOTES_CONTENT = "content";
    protected static final String FIELD_NOTES_TIME = "time";
    protected static final String FIELD_NOTES_COLLECTION = "collection";
    protected static final String FIELD_NOTES_TYPENAME = "typeName";
    protected static final String FIELD_NOTES_TYPE_ID = "type_id";
    protected static final String FIELD_NOTES_MEDIA_ID = "media_id";

    protected static final String TABLE_NAME_TYPE = "type";
    protected static final String FIELD_TYPE_TYPE_ID = "type_id";
    protected static final String FIELD_TYPE_NAME = "name";

    protected static final String TABLE_NAM_MEDIA = "media";
    protected static final String FIELD_MEDIA_MEDIA_ID = "media_id";
    protected static final String FIELD_MEDIA_PATH = "path";

    protected SQLiteDatabase db ;

    public OpenDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version,boolean isModel) {
        super(context, name, factory, version);
        if(isModel){
            db = getWritableDatabase();
        }
        if(!isModel){
            db = getReadableDatabase();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME_NOTES+"(" +
                FIELD_NOTES__ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                FIELD_NOTES_TITLE+" TEXT," +
                FIELD_NOTES_CONTENT+" TEXT," +
                FIELD_NOTES_TIME+" VARCHAR(20)," +
                FIELD_NOTES_COLLECTION+" INTEGER," +
                FIELD_NOTES_TYPENAME+" VARCHAR(20)"+
                FIELD_NOTES_TYPE_ID+"INTEGER" +
                FIELD_NOTES_MEDIA_ID+"INTEGER)");
        db.execSQL("create table "+TABLE_NAME_TYPE+"(" +
                FIELD_TYPE_TYPE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT" +
                FIELD_TYPE_NAME+"VARCHAR(20))");
        db.execSQL("create table "+TABLE_NAM_MEDIA+"(" +
                FIELD_MEDIA_MEDIA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT" +
                FIELD_MEDIA_PATH+"VARCHAR(60))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
