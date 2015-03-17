package com.netos.activity.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.Image;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.netos.activity.R;
import com.notos.entity.MenuSelectEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangcaihui on 15/2/10.
 */
public class ExpListViewMenuSelectAdapter extends BaseExpandableListAdapter {
    private List<MenuSelectEntity> groupList;
    private List<List<MenuSelectEntity>> childList;
    private Context context;
    private LayoutInflater mLayoutInflater;
    private int cchildPosition ;
    private boolean childPointState = false;

    public ExpListViewMenuSelectAdapter(Context context) {
        this.context = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listData();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void listData() {
        groupList = new ArrayList<MenuSelectEntity>();
        MenuSelectEntity groupEntity1 = new MenuSelectEntity
                (context.getDrawable(R.drawable.menu_more_icon), "更多");
        groupList.add(groupEntity1);

        childList = new ArrayList<List<MenuSelectEntity>>();
        List<MenuSelectEntity> mlist1 = new ArrayList<MenuSelectEntity>();
        MenuSelectEntity g1child1 = new MenuSelectEntity
                (context.getDrawable(R.drawable.menu_favor_icon), "我的收藏");
        MenuSelectEntity g1child2 = new MenuSelectEntity
                (context.getDrawable(R.drawable.menu_img_transit_icon), "图片中转站");
        mlist1.add(g1child1);
        mlist1.add(g1child2);
        childList.add(mlist1);
    }

    public void setChildPosition(int cchildPosition){
        this.cchildPosition = cchildPosition;
    }

    public boolean isChildPointState() {
        return childPointState;
    }

    public void setChildPointState(boolean childPointState) {
        this.childPointState = childPointState;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        GroupViewHandler mGroupViewHandler = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.egmsi_exp_group_menu_select_item, null);
            mGroupViewHandler = new GroupViewHandler();
            mGroupViewHandler.gImMenuIcon = (ImageView)
                    convertView.findViewById(R.id.gim_menu_icon);
            mGroupViewHandler.gTvMenuName = (TextView)
                    convertView.findViewById(R.id.gtv_menu_item_name);
            convertView.setTag(mGroupViewHandler);
        }else{
            mGroupViewHandler = (GroupViewHandler)convertView.getTag();
        }
        MenuSelectEntity gEntity = (MenuSelectEntity) getGroup(groupPosition);
        mGroupViewHandler.gImMenuIcon.setImageDrawable(gEntity.getSelectIcn());
        mGroupViewHandler.gTvMenuName.setText(gEntity.getSelectName());
        return convertView;
    }

    private class GroupViewHandler {
        private ImageView gImMenuIcon;
        private TextView gTvMenuName;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        ChildViewHandler mChildViewHandler = null;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.ecmsi_exp_child_menu_select_item,null);
            mChildViewHandler = new ChildViewHandler();
            mChildViewHandler.cIMenuPoint = (ImageView)
                    convertView.findViewById(R.id.cim_menu_point);
            mChildViewHandler.cImMenuIcon = (ImageView)
                    convertView.findViewById(R.id.cim_menu_icon);
            mChildViewHandler.cTvMenuName = (TextView)
                    convertView.findViewById(R.id.ctv_menu_item_name);
            convertView.setTag(mChildViewHandler);
        }else{
            mChildViewHandler = (ChildViewHandler)convertView.getTag();
        }
         MenuSelectEntity cEntity = (MenuSelectEntity)getChild(groupPosition,childPosition);
         mChildViewHandler.cImMenuIcon.setImageDrawable(cEntity.getSelectIcn());
         mChildViewHandler.cTvMenuName.setText(cEntity.getSelectName());
        if(cchildPosition == childPosition  && childPointState){
            mChildViewHandler.cIMenuPoint.setVisibility(View.VISIBLE);
        }else{
            mChildViewHandler.cIMenuPoint.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    private class ChildViewHandler{
        private ImageView cImMenuIcon;
        private TextView cTvMenuName;
        private ImageView cIMenuPoint;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
