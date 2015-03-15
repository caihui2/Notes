package com.netos.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.netos.activity.R;
import com.netos.darabase.NoteUtil;
import com.notos.entity.NoteEntity;

import java.util.List;

/**
 * Created by chyang on 15-2-25.
 */
public class NotesDataAdapter extends BaseAdapter {

    private Context context;
    private List<NoteEntity> mList;
    private LayoutInflater mInflater;
    private NoteUtil noteUtil;

    public NotesDataAdapter(Context context, NoteUtil noteUtil) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.noteUtil = noteUtil;
    }

    public void dataChange(String stpName) {
        if (stpName.equals("全部笔记")) {
            mList = noteUtil.queryNoteAll();
        }
        mList = noteUtil.queryNoteType(stpName);
    }

    public boolean isDataEmpty() {
        return mList != null ? true : false;
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
        NoteEntity mNoteEntity = (NoteEntity) getItem(position);
        mViewHandler.teTitle.setText(mNoteEntity.getTitle());
        mViewHandler.teTime.setText(mNoteEntity.getTime());
        mViewHandler.teContent.setText(mNoteEntity.getContent());
        return convertView;
    }

    private class ViewHandler {
        TextView teTitle;
        TextView teTime;
        TextView teContent;
    }

}
