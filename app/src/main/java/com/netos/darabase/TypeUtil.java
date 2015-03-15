package com.netos.darabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.notos.entity.TypeEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangcaihui on 15/3/15.
 */
public class TypeUtil extends  MediaUtil{

    public TypeUtil(Context context, String name, SQLiteDatabase.CursorFactory factory, int version,boolean isModel) {
        super(context, name, factory, version,isModel);
    }

    public List<TypeEntity> queryTypeAll(){
        List<TypeEntity> mList = new ArrayList<TypeEntity>();
        Cursor mCursor = db.query(TABLE_NAME_TYPE,null,null,null,null,null,null);
        if(mCursor != null){
            while(mCursor.moveToNext()){
                int type_id = mCursor.getInt(mCursor.getColumnIndex(FIELD_TYPE_TYPE_ID));
                String name = mCursor.getString(mCursor.getColumnIndex(FIELD_TYPE_NAME));
                mList.add(new TypeEntity(type_id,name));
            }
        }
        return mList;
    }

    public int addType(String type){
        int result = 0;
        ContentValues values = new ContentValues();
        values.put(FIELD_TYPE_NAME,type);
        result = (int)db.insert(FIELD_TYPE_NAME,null,values);
        return result;
    }

    //TODO
    public boolean isExist(String type){
        String str = null;
       Cursor mCursor = db.query(TABLE_NAME_TYPE,null,FIELD_TYPE_NAME+"=?",new String[]{type},
                null,null,null);
       if(mCursor != null){
         while(mCursor.moveToNext()){
             str = mCursor.getString(mCursor.getColumnIndex(FIELD_TYPE_NAME));
         }
       }
        return str == null ? true :false ;
    }

    public int  deleteType(String type){
        int result = 0;
        result = db.delete(TABLE_NAME_TYPE,FIELD_TYPE_NAME+"=?",new String[]{type});
        return  result;
    }



}
