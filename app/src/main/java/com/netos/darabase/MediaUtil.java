package com.netos.darabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by yangcaihui on 15/3/15.
 */
public class MediaUtil extends  OpenDB {
    public MediaUtil(Context context, String name, SQLiteDatabase.CursorFactory factory, int version,boolean isModel) {
        super(context, name, factory, version,isModel);
    }
}
