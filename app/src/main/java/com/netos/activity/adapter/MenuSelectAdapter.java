package com.netos.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.netos.activity.R;
import com.notos.entity.MenuSelectEntity;

import java.util.List;

/**
 * Created by yangcaihui on 15/2/9.
 */
public class MenuSelectAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mInflater;
    private List<MenuSelectEntity> mMenuSelectEntity ;

    public MenuSelectAdapter(Context context,
           List<MenuSelectEntity> mMenuSelectEntity){
        this.context = context;
        this.mMenuSelectEntity = mMenuSelectEntity;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
   
        return convertView;
    }

    private static class HanderView{
        private ImageView imMenuPoint;
        private ImageView imMenuIcon;
        private TextView tvMenuName;
    }
}
