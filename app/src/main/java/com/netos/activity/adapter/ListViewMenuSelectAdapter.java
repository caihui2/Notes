package com.netos.activity.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.netos.activity.R;
import com.notos.entity.MenuSelectEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangcaihui on 15/2/9.
 */
public class ListViewMenuSelectAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mInflater;
    private List<MenuSelectEntity> mMenuSelectEntity ;
    private int selectPosition;
    private boolean listPointState = true;
    public ListViewMenuSelectAdapter(Context context){
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listData();
    }

    public boolean isListPointState() {
        return listPointState;
    }

    public void setListPointState(boolean listPointState) {
        this.listPointState = listPointState;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void listData(){
        mMenuSelectEntity = new ArrayList<MenuSelectEntity>();
        MenuSelectEntity e1 = new MenuSelectEntity(context.getDrawable(R.drawable.menu_all_note_icon),"首页");
        MenuSelectEntity e2 = new MenuSelectEntity(context.getDrawable(R.drawable.menu_note_book_icon),"笔记本");
        MenuSelectEntity e3 = new MenuSelectEntity(context.getDrawable(R.drawable.menu_group_icon),"云协作");
        mMenuSelectEntity.add(e1);
        mMenuSelectEntity.add(e2);
        mMenuSelectEntity.add(e3);
    }

    public void setSelectPosition(int selectPosition){
        this.selectPosition = selectPosition;
    }

    @Override
    public int getCount() {

        return mMenuSelectEntity.size();
    }

    @Override
    public Object getItem(int position) {

        return mMenuSelectEntity.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HanderView mHanderView = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.msi_menu_select_item,null);
            mHanderView = new HanderView();
            mHanderView.imMenuPoint = (ImageView)convertView.findViewById(R.id.im_menu_point);
            mHanderView.imMenuIcon = (ImageView)convertView.findViewById(R.id.im_menu_icon);
            mHanderView.tvMenuName = (TextView) convertView.findViewById(R.id.tv_menu_item_name);
            convertView.setTag(mHanderView);
        }else{
            mHanderView = (HanderView)convertView.getTag();
        }
        MenuSelectEntity entity = mMenuSelectEntity.get(position);
        mHanderView.imMenuIcon.setImageDrawable(entity.getSelectIcn());
        mHanderView.tvMenuName.setText(entity.getSelectName());
        if(selectPosition == position && listPointState){
            mHanderView.imMenuPoint.setVisibility(View.VISIBLE);
        }else{
            mHanderView.imMenuPoint.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    private static class HanderView{
        private ImageView imMenuPoint;
        private ImageView imMenuIcon;
        private TextView tvMenuName;
    }

}
