package com.netos.activity.adapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.netos.activity.R;
import com.netos.activity.SelectNoteTypeActivity;
import com.netos.darabase.DBUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangcaihui on 15/3/8.
 */
public class SpinnerDataAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> mList;
    public SpinnerDataAdapter(Context mContext, DBUtils mDbUtils){
        this.mContext = mContext;
         mInflater = LayoutInflater.from(mContext);
         mList = new ArrayList<String>();
        mList.add("全部笔记");
        setReceive();
        List<String> oldL = mDbUtils.querysType();
        if(mList != null){
            for(String str : oldL){
                 mList.add(str);
            }
        }
    }

    public void setReceive(){
        IntentFilter dtFilter = new IntentFilter(SelectNoteTypeActivity.ACTION_DT);
        mContext.registerReceiver(new SignalData(),dtFilter);
        IntentFilter adFilter = new IntentFilter(SelectNoteTypeActivity.ACTION_AD);
        mContext.registerReceiver(new SignalData(),adFilter);
    }

    public boolean listState(){
        if(mList != null){
            return true;
        }else{
            return false;
        }
    }
    public List<String> getList(){
        return mList;
    }
    public void addList(String type){
        mList.add(type);
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
        ViewHandler mViewHandler ;
        if(convertView == null){
            mViewHandler = new ViewHandler();
            convertView = mInflater.inflate(R.layout.sdi_spnnier_data_item,null);
            mViewHandler.teSp = (TextView)convertView.findViewById(R.id.te_sp);
            convertView.setTag(mViewHandler);
        }else{
            mViewHandler = (ViewHandler)convertView.getTag();
        }
        mViewHandler.teSp.setText((String)getItem(position));
        return convertView;
    }
    private class ViewHandler{
        private TextView teSp;
    }

    class SignalData extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(SelectNoteTypeActivity.ACTION_DT)){
               String type = intent.getStringExtra(SelectNoteTypeActivity.DT_KEY_TYPE);
                mList.remove(type);
            }
            else if(action.equals(SelectNoteTypeActivity.ACTION_AD)){
                String type = intent.getStringExtra(SelectNoteTypeActivity.AD_KEY_TYPE);
                mList.add(type);
            }
        }
    }
}
