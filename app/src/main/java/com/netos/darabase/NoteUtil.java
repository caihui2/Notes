package com.netos.darabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.notos.entity.NoteEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangcaihui on 15/3/15.
 */
public class NoteUtil extends TypeUtil {
    private static final String DB_NAME = "notes.db";
    private static final int DB_VERSION = 1;

    public NoteUtil(Context context, boolean isModel) {
        super(context, DB_NAME, null, DB_VERSION, isModel);
    }

    public List<NoteEntity> queryNoteAll() {
        List<NoteEntity> mList = new ArrayList<NoteEntity>();
        Cursor mCursor = db.query(TABLE_NAME_NOTES, null, null, null, null, null, null);
        if (mCursor != null) {
            while (mCursor.moveToNext()) {
                int _id = mCursor.getInt(mCursor.getColumnIndex(FIELD_NOTES__ID));
                String title = mCursor.getString(mCursor.getColumnIndex(FIELD_NOTES_TITLE));
                String content = mCursor.getString(mCursor.getColumnIndex(FIELD_NOTES_CONTENT));
                String time = mCursor.getString(mCursor.getColumnIndex(FIELD_NOTES_CONTENT));
                int  collection = mCursor.getInt(mCursor.getColumnIndex(FIELD_NOTES_COLLECTION));
                String typeName = mCursor.getString(mCursor.getColumnIndex(FIELD_NOTES_TYPENAME));
                int typeId = mCursor.getInt(mCursor.getColumnIndex(FIELD_NOTES_TYPE_ID));
                int mediaId = mCursor.getInt(mCursor.getColumnIndex(FIELD_NOTES_MEDIA_ID));
                mList.add(new NoteEntity(_id,title,content,time,collection,typeName,typeId,mediaId));
            }
        }
        return mList;
    }

    public List<NoteEntity> queryNoteType(String type){
        List<NoteEntity> mList = new ArrayList<NoteEntity>();
        Cursor mCursor = db.query(TABLE_NAME_NOTES,null,FIELD_NOTES_TYPENAME+"=?",
                new String[]{type},null,null,null);
        if(mCursor != null){
            while(mCursor.moveToNext()){
                int _id = mCursor.getInt(mCursor.getColumnIndex(FIELD_NOTES__ID));
                String title = mCursor.getString(mCursor.getColumnIndex(FIELD_NOTES_TITLE));
                String content = mCursor.getString(mCursor.getColumnIndex(FIELD_NOTES_CONTENT));
                String time = mCursor.getString(mCursor.getColumnIndex(FIELD_NOTES_CONTENT));
                int  collection = mCursor.getInt(mCursor.getColumnIndex(FIELD_NOTES_COLLECTION));
                String typeName = mCursor.getString(mCursor.getColumnIndex(FIELD_NOTES_TYPENAME));
                int typeId = mCursor.getInt(mCursor.getColumnIndex(FIELD_NOTES_TYPE_ID));
                int mediaId = mCursor.getInt(mCursor.getColumnIndex(FIELD_NOTES_MEDIA_ID));
                mList.add(new NoteEntity(_id,title,content,time,collection,typeName,typeId,mediaId));
            }
        }
        return mList;
    }

    public int deleteTypeNote(String type){
        int result = 0;
       result = db.delete(TABLE_NAME_NOTES,FIELD_NOTES_TYPENAME+"=?",new String[]{type});
       return result;
    }

}
