package com.netos.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.netos.activity.R;
import com.netos.darabase.DBUrils;
import com.notos.entity.NotesObjInfo;

import java.util.List;

/**
 * Created by chyang on 15-2-25.
 */
public class NotesDataAdapter extends BaseAdapter {

    private Context context;
    private List<NotesObjInfo> mList;
    private LayoutInflater mInflater;

    public NotesDataAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setList(List<NotesObjInfo> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHandler mViewHandler = null;
        if (convertView == null) {
            mViewHandler = new ViewHandler();
            convertView = mInflater.inflate(R.layout.nli_notes_list_item, null);
            mViewHandler.teTitle = (TextView) convertView.findViewById(R.id.te_title);
            mViewHandler.teTime = (TextView) convertView.findViewById(R.id.te_time);
            mViewHandler.teContent = (TextView) convertView.findViewById(R.id.te_content);

            convertView.setTag(mViewHandler);
        } else {
            mViewHandler = (ViewHandler) convertView.getTag();
        }
        NotesObjInfo mNotesObjInfo = (NotesObjInfo) getItem(position);
        mViewHandler.teTitle.setText(mNotesObjInfo.getTitle());
        mViewHandler.teTime.setText(mNotesObjInfo.getTime());
        mViewHandler.teContent.setText(mNotesObjInfo.getContent());
        return convertView;
    }

    private class ViewHandler {
        TextView teTitle;
        TextView teTime;
        TextView teContent;
    }

}
