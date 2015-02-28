package com.netos.activity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.netos.darabase.DBUrils;

import java.util.List;

/**
 * Created by chyang on 15-2-28.
 */
public class SelectNoteTypeAdapter extends BaseAdapter {

    private Context context;
    private List<String> mStringList;

    public SelectNoteTypeAdapter(Context context){
        this.context = context;
        DBUrils mDbUrils = new DBUrils(context,false);

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
