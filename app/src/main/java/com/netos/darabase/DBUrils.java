package com.netos.darabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.notos.entity.NotesObjInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chyang on 15-2-25.
 */
public class DBUrils {
    private OpenDB mOpenDB ;
    private SQLiteDatabase mSqLiteDatabase;
    private static final String DB_NAME = "db_notes";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE_NAME = "notes";
    public DBUrils(Context context,boolean DBMode){
        mOpenDB = new OpenDB(context,DB_NAME,null,DB_VERSION);
        if(DBMode){
            mSqLiteDatabase = mOpenDB.getWritableDatabase();
        }else{
            mSqLiteDatabase = mOpenDB.getReadableDatabase();
        }
    }

    /**
     * sql add
     */
    public void add(NotesObjInfo mNotesObjInfo){
        ContentValues values = new ContentValues();
        values.put("title",mNotesObjInfo.getTitle());
        values.put("content",mNotesObjInfo.getContent());
        values.put("time",mNotesObjInfo.getTime());
        values.put("collection",mNotesObjInfo.getCollection());
        mSqLiteDatabase.insert(DB_TABLE_NAME, null, values);
        mSqLiteDatabase.close();
    }

    /**
     *
     * @return NotesObjInfo List
     */
    public List<NotesObjInfo> querys(){
        List<NotesObjInfo> mNotesObjInfoList = new ArrayList<NotesObjInfo>();
        Cursor mCursor = mSqLiteDatabase.query(DB_TABLE_NAME, null, null, null, null, null, null);
        if(mCursor != null){
            while (mCursor.moveToNext()){
             String title = mCursor.getString(mCursor.getColumnIndex("title"));
             String content = mCursor.getString(mCursor.getColumnIndex("content"));
             String time = mCursor.getString(mCursor.getColumnIndex("time"));
             int collection = mCursor.getInt(mCursor.getColumnIndex("collection"));
             mNotesObjInfoList.add(new NotesObjInfo(title,content,time,collection));
            }
        }
        mCursor.close();
        return mNotesObjInfoList;
    }




}
