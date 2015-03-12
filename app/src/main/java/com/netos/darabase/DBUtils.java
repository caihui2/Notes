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
public class DBUtils {
    private OpenDB mOpenDB ;
    private SQLiteDatabase mSqLiteDatabase;
    private static final String DB_NAME = "db_notes";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE_NOTES = "notes";
    private static final String DB_TABLE_NOTES_TYPE = "notesType";

    private static final String DB_FD_NAME_TITLE ="title";
    private static final String DB_FD_NAME_CONTENT = "content";
    private static final String DB_FD_NAME_TIME = "time";
    private static final String DB_FD_NAME_COLLECTION = "collection";
    private static final String DB_FD_NAME_NOTE_TYPE = "noteType";
    public DBUtils(Context context, boolean DBMode){
        mOpenDB = new OpenDB(context,DB_NAME,null,DB_VERSION);
        if(DBMode){
            mSqLiteDatabase = mOpenDB.getWritableDatabase();
        }else{
            mSqLiteDatabase = mOpenDB.getReadableDatabase();
        }
    }

    /**
     * @table notes
     * sql add
     */
    public long  addNote(NotesObjInfo mNotesObjInfo){
        ContentValues values = new ContentValues();
        values.put(DB_FD_NAME_TITLE,mNotesObjInfo.getTitle());
        values.put(DB_FD_NAME_CONTENT,mNotesObjInfo.getContent());
        values.put(DB_FD_NAME_TIME,mNotesObjInfo.getTime());
        values.put(DB_FD_NAME_COLLECTION,mNotesObjInfo.getCollection());
        values.put(DB_FD_NAME_NOTE_TYPE,mNotesObjInfo.getTypeName());
       long result =  mSqLiteDatabase.insert(DB_TABLE_NOTES, null, values);
        mSqLiteDatabase.close();
        return result;
    }



    public int wIdUpNote(int id ,NotesObjInfo mNotesObjInfo){
        int result = -1;
        ContentValues values = new ContentValues();
        values.put(DB_FD_NAME_TITLE,mNotesObjInfo.getTitle());
        values.put(DB_FD_NAME_CONTENT,mNotesObjInfo.getContent());
        values.put(DB_FD_NAME_TIME,mNotesObjInfo.getTime());
        values.put(DB_FD_NAME_COLLECTION,mNotesObjInfo.getCollection());
        values.put(DB_FD_NAME_NOTE_TYPE,mNotesObjInfo.getTypeName());
       result = mSqLiteDatabase.update(DB_TABLE_NOTES,values,"_id=?",new String[]{id+""});
        return result;
    }

    /**
     *@table notes
     * @return NotesObjInfo List
     */
    public List<NotesObjInfo> qyNote(){
        List<NotesObjInfo> mNotesObjInfoList = new ArrayList<NotesObjInfo>();
        Cursor mCursor = mSqLiteDatabase.query(DB_TABLE_NOTES, null, null, null, null, null, null);
        if(mCursor != null){
            while (mCursor.moveToNext()){
             String title = mCursor.getString(mCursor.getColumnIndex(DB_FD_NAME_TITLE));
             String content = mCursor.getString(mCursor.getColumnIndex(DB_FD_NAME_CONTENT));
             String time = mCursor.getString(mCursor.getColumnIndex(DB_FD_NAME_TIME));
             String typeName = mCursor.getString(mCursor.getColumnIndex(DB_FD_NAME_NOTE_TYPE));
             int collection = mCursor.getInt(mCursor.getColumnIndex(DB_FD_NAME_COLLECTION));
             mNotesObjInfoList.add(new NotesObjInfo(content,title,time,typeName,collection));
            }
        }
        mCursor.close();
        return mNotesObjInfoList;
    }

    public int qyId(String title){
       int result = -1;
       Cursor mCursor = mSqLiteDatabase.query(DB_TABLE_NOTES,new String[]{"_id"},
               DB_FD_NAME_TITLE+"=?",new String[]{title},null,null,null);
       if(mCursor != null){
          if(mCursor.moveToNext()){
              result = mCursor.getInt(mCursor.getColumnIndex("_id"));
          }
       }
        return result;
    }


    /**
     *@table notes
     * @param typeName
     * @return
     */
    public List<NotesObjInfo> qyTypeCount(String typeName){
        List<NotesObjInfo> mNotesObjInfoList = new ArrayList<NotesObjInfo>();
        Cursor mCursor = mSqLiteDatabase.query(DB_TABLE_NOTES,null,
                DB_FD_NAME_NOTE_TYPE+"=?",new String[]{typeName},null,null,null);
        if(mCursor != null){
            while(mCursor.moveToNext()){
                String title = mCursor.getString(mCursor.getColumnIndex(DB_FD_NAME_TITLE));
                String content = mCursor.getString(mCursor.getColumnIndex(DB_FD_NAME_CONTENT));
                String time = mCursor.getString(mCursor.getColumnIndex(DB_FD_NAME_TIME));
                String type = mCursor.getString(mCursor.getColumnIndex(DB_FD_NAME_NOTE_TYPE));
                int collection = mCursor.getInt(mCursor.getColumnIndex(DB_FD_NAME_COLLECTION));
                mNotesObjInfoList.add(new NotesObjInfo(title,content,time,type,collection));
            }
        }
        return mNotesObjInfoList;
    }


    /**
     * @table notes
     * @param typeName
     * @return
     */
    public int dtTpNote(String typeName){
        int result = 0;
        result = mSqLiteDatabase.delete(DB_TABLE_NOTES,DB_FD_NAME_NOTE_TYPE+"=?",
                new String[]{typeName});
        return result;
    }

    /**
     * @table notes_type
     * @param typeName
     * @return
     */
   public int dtType(String typeName){
       int result = 0;
       result = mSqLiteDatabase.delete(DB_TABLE_NOTES_TYPE,DB_FD_NAME_NOTE_TYPE+"=?",
               new String[]{typeName});
       return result;
   }

    /**
     * @table notes_type
     * @param typeName
     * @return
     */
    public long addType(String typeName){
        ContentValues values = new ContentValues();
        values.put(DB_FD_NAME_NOTE_TYPE,typeName);
        long result = mSqLiteDatabase.insert(DB_TABLE_NOTES_TYPE,null,values);

        return result;
    }


    /**
     * @table notes_type
     * @param typeName
     * @return
     */
    public String qyTypeItem(String typeName){
        String result = null;
        Cursor mCursor = mSqLiteDatabase.query(DB_TABLE_NOTES_TYPE,null,DB_FD_NAME_NOTE_TYPE+"=?",
                new String[]{typeName},null,null,null);
        if(mCursor != null){
            while(mCursor.moveToNext()){
                result = mCursor.getString(mCursor.getColumnIndex(DB_FD_NAME_NOTE_TYPE));
            }
        }
        return result;
    }

    /**
     *@table notes_type
     * @return note TYPE count
     */
    public List<String> querysType(){
        List<String> mStringList = new ArrayList<String>();
        Cursor mCursor = mSqLiteDatabase.query(DB_TABLE_NOTES_TYPE,new String[]{DB_FD_NAME_NOTE_TYPE},null,null,null,null,null);
        if(mCursor != null){
            while(mCursor.moveToNext()){
                String noteType =  mCursor.getString(mCursor.getColumnIndex(DB_FD_NAME_NOTE_TYPE));
                mStringList.add(noteType);
            }
        }
        return mStringList;
    }


}
