package com.netos.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.netos.activity.R;
import com.netos.darabase.DBUrils;

import java.util.List;

/**
 * Created by chyang on 15-2-28.
 */
public class SelectNoteTypeAdapter extends BaseAdapter {

    private Context context;
    private List<String> mStringList;
    private LayoutInflater mInflater;
    private int sPosition;

    public SelectNoteTypeAdapter(Context context , List<String> mStringList){
        this.context = context;
        this.mStringList = mStringList;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void setSPosition(int sPosition){
        this.sPosition = sPosition;
    }

    @Override
    public int getCount() {

        return mStringList.size();
    }

    @Override
    public Object getItem(int position) {

        return mStringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHandler mViewHandler = null;
        if(convertView == null){
            mViewHandler = new ViewHandler();
            convertView = mInflater.inflate(R.layout.snti_select_note_type_item,null);
            mViewHandler.teType = (TextView)convertView.findViewById(R.id.te_type);
            mViewHandler.imSelectType = (ImageView)convertView.findViewById(R.id.im_select_type);
            convertView.setTag(mViewHandler);
        }else{
            mViewHandler = (ViewHandler)convertView.getTag();
        }
        String str = (String)getItem(position);
        mViewHandler.teType.setText(str);
        if(sPosition == position){
            mViewHandler.imSelectType.setVisibility(View.VISIBLE);
        }else{
            mViewHandler.imSelectType.setVisibility(View.GONE);
        }
        return convertView;
    }

   private class ViewHandler{
       TextView  teType;
       ImageView imSelectType;
   }
}
