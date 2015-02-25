package com.netos.activity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.netos.darabase.DBUrils;
import com.notos.entity.NotesObjInfo;

import java.util.List;

/**
 * Created by chyang on 15-2-25.
 */
public class NotesDataAdapter extends BaseAdapter {

    private Context Context;
    private List<NotesObjInfo> mList;
    public NotesDataAdapter (Context context){
        DBUrils db = new DBUrils(context,false);
        mList = db.querys();

    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
