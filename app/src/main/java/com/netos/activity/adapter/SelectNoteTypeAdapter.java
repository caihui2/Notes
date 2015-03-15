package com.netos.activity.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.netos.activity.R;
import com.netos.darabase.NoteUtil;
import com.notos.entity.NoteEntity;
import com.notos.entity.TypeEntity;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by chyang on 15-2-28.
 */
public class SelectNoteTypeAdapter extends BaseAdapter {

    private Context context;
    private List<TypeEntity> mList;
    private LayoutInflater mInflater;
    private NoteUtil noteUtil;
    private int sPosition;
    private boolean vState = false;


    public static final String ACTION_DT = "action_dt";
    public static final String DT_KEY_TYPE = "dt_key_type";

    public static final String ACTION_AD = "action_ad";
    public static final String AD_KEY_TYPE = "ad_key_type";

    public SelectNoteTypeAdapter(Context context) {
        this.context = context;
        noteUtil = new NoteUtil(context, true);
        mList = noteUtil.queryTypeAll();
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setSPosition(int sPosition) {
        this.sPosition = sPosition;
    }

    public void setVState(boolean vState) {
        this.vState = vState;
    }

    public boolean isEntity() {
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
            convertView = mInflater.inflate(R.layout.snti_select_note_type_item, null);
            mViewHandler.teType = (TextView) convertView.findViewById(R.id.te_type);
            mViewHandler.imSelectType = (ImageView) convertView.findViewById(R.id.im_select_type);
            convertView.setTag(mViewHandler);
        } else {
            mViewHandler = (ViewHandler) convertView.getTag();
        }
        String str = ((TypeEntity) getItem(position)).getName();
        mViewHandler.teType.setText(str);
        if (sPosition == position && vState) {
            mViewHandler.imSelectType.setVisibility(View.VISIBLE);
        } else {
            mViewHandler.imSelectType.setVisibility(View.GONE);
        }
        return convertView;
    }

    private class ViewHandler {
        TextView teType;
        ImageView imSelectType;
    }




    public void dtType(int position) {
        TypeEntity  typeEntity = (TypeEntity)getItem(position);
        List<NoteEntity> noteEntityList = noteUtil.queryNoteType(typeEntity.getName());
        if (noteEntityList.size() <= 0) {
           deleteMode(typeEntity);
        }
        showDtDialog(typeEntity.getName());
        deleteMode(typeEntity);
    }

    private void deleteMode(TypeEntity typeEntity){
        int result = noteUtil.deleteType(typeEntity.getName());
        if (result <= 0) {
            Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
        }
        sendData(ACTION_DT,DT_KEY_TYPE,typeEntity);
        mList.remove(typeEntity);
        notifyDataSetChanged();
    }

    public void showDtDialog(final String typeName) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        mBuilder.setTitle("提示");
        mBuilder.setMessage("是否删除此类型中的笔记？");
        mBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int result = noteUtil.deleteTypeNote(typeName);
                if (result <= 0) {
                    Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        mBuilder.show();

    }

    public void addTypeName(String type){
       boolean result = noteUtil.isExist(type);
        if(result){
          int count  = noteUtil.addType(type);
          if(count > 0){
              TypeEntity mEntity =  new TypeEntity(type);
              sendData(ACTION_AD,AD_KEY_TYPE,mEntity);
              mList.add(mEntity);
              notifyDataSetChanged();
          }
        }
        Toast.makeText(context, "类型已存在", Toast.LENGTH_SHORT)
                .show();
    }


    public void sendData(String action,String key,TypeEntity type){
        Intent mIntent = new Intent();
        mIntent.setAction(action);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(key,type);
        mIntent.putExtras(mBundle);
        context.sendBroadcast(mIntent);
    }

}
