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
    private static final String DB_TABLE_NAME_ONE = "notesType";

    private static final String DB_TABLE_NAME_TITLE ="title";
    private static final String DB_TABLE_NAME_CONTENT = "content";
    private static final String DB_TABLE_NAME_TIME = "time";
    private static final String DB_TABLE_NAME_COLLECTION = "collection";
    private static final String DB_TABLE_NAME_NOTE_TYPE = "noteType";
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
    public long add(NotesObjInfo mNotesObjInfo){
        ContentValues values = new ContentValues();
        values.put(DB_TABLE_NAME_TITLE,mNotesObjInfo.getTitle());
        values.put(DB_TABLE_NAME_CONTENT,mNotesObjInfo.getContent());
        values.put(DB_TABLE_NAME_TIME,mNotesObjInfo.getTime());
        values.put(DB_TABLE_NAME_COLLECTION,mNotesObjInfo.getCollection());
        values.put(DB_TABLE_NAME_NOTE_TYPE,mNotesObjInfo.getTypeName());
       long result =  mSqLiteDatabase.insert(DB_TABLE_NAME, null, values);
        mSqLiteDatabase.close();
        return result;
    }

    public long addType(String typeName){
        ContentValues values = new ContentValues();
        values.put(DB_TABLE_NAME_NOTE_TYPE,typeName);
      long result = mSqLiteDatabase.insert(DB_TABLE_NAME_ONE,null,values);
        return result;
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
             String title = mCursor.getString(mCursor.getColumnIndex(DB_TABLE_NAME_TITLE));
             String content = mCursor.getString(mCursor.getColumnIndex(DB_TABLE_NAME_CONTENT));
             String time = mCursor.getString(mCursor.getColumnIndex(DB_TABLE_NAME_TIME));
             String typeName = mCursor.getString(mCursor.getColumnIndex(DB_TABLE_NAME_NOTE_TYPE));
             int collection = mCursor.getInt(mCursor.getColumnIndex(DB_TABLE_NAME_COLLECTION));
             mNotesObjInfoList.add(new NotesObjInfo(title,content,time,typeName,collection));
            }
        }
        mCursor.close();
        return mNotesObjInfoList;
    }

    public String queryTypeItem(String typeName){
        String result = null;
        Cursor mCursor = mSqLiteDatabase.query(DB_TABLE_NAME_ONE,null,DB_TABLE_NAME_NOTE_TYPE+"=?",
                new String[]{typeName},null,null,null);
        if(mCursor != null){
            while(mCursor.moveToNext()){
               result = mCursor.getString(mCursor.getColumnIndex(DB_TABLE_NAME_NOTE_TYPE));
            }
        }

        return result;
    }

    /**
     *
     * @param typeName
     * @return
     */
    public List<NotesObjInfo> querysTypeObjCount(String typeName){
        List<NotesObjInfo> mNotesObjInfoList = new ArrayList<NotesObjInfo>();
        Cursor mCursor = mSqLiteDatabase.query(DB_TABLE_NAME,null,
                DB_TABLE_NAME_NOTE_TYPE+"=?",new String[]{typeName},null,null,null);
        if(mCursor != null){
            while(mCursor.moveToNext()){
                String title = mCursor.getString(mCursor.getColumnIndex(DB_TABLE_NAME_TITLE));
                String content = mCursor.getString(mCursor.getColumnIndex(DB_TABLE_NAME_CONTENT));
                String time = mCursor.getString(mCursor.getColumnIndex(DB_TABLE_NAME_TIME));
                String type = mCursor.getString(mCursor.getColumnIndex(DB_TABLE_NAME_NOTE_TYPE));
                int collection = mCursor.getInt(mCursor.getColumnIndex(DB_TABLE_NAME_COLLECTION));
                mNotesObjInfoList.add(new NotesObjInfo(title,content,time,type,collection));
            }
        }
        return mNotesObjInfoList;
    }

    /**
     *
     * @return note TYPE count
     */
    public List<String> querysType(){
        List<String> mStringList = new ArrayList<String>();
        Cursor mCursor = mSqLiteDatabase.query(DB_TABLE_NAME_ONE,new String[]{DB_TABLE_NAME_NOTE_TYPE},null,null,null,null,null);
        if(mCursor != null){
            while(mCursor.moveToNext()){
                String noteType =  mCursor.getString(mCursor.getColumnIndex(DB_TABLE_NAME_NOTE_TYPE));
                mStringList.add(noteType);
            }
        }
        return mStringList;
    }


    public int deleteTypeNote(String typeName){
        int result = 0;
        result = mSqLiteDatabase.delete(DB_TABLE_NAME,DB_TABLE_NAME_NOTE_TYPE+"=?",
                new String[]{typeName});
        return result;
    }

   public int deleteType(String typeName){
       int result = 0;
       result = mSqLiteDatabase.delete(DB_TABLE_NAME_ONE,DB_TABLE_NAME_NOTE_TYPE+"=?",
               new String[]{typeName});
       return result;
   }



}
