package activity.netos.com.notes.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.netos.com.notes.R;
import activity.netos.com.notes.entity.MenuItemEnty;

/**
 * Created by yangcaihui on 15/3/30.
 */
public class MenuListAdapter extends BaseExpandableListAdapter {
    private LayoutInflater mInflater;
    private Context context;
    private List<MenuItemEnty> groupList;
    private List<List<MenuItemEnty>> childList;
    private int itmPosition;

    // point state value
    private int gPosition;
    private int cPosition;
    private boolean isChilked;

    public MenuListAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        groupList = new ArrayList<MenuItemEnty>();
        Resources resources = context.getResources();
        //group value
        MenuItemEnty m1 = new MenuItemEnty(resources.getDrawable(R.drawable.menu_all_note_icon), "全部笔记");
        MenuItemEnty m2 = new MenuItemEnty(resources.getDrawable(R.drawable.menu_note_book_icon), "笔记本");
        MenuItemEnty m3 = new MenuItemEnty(resources.getDrawable(R.drawable.menu_group_icon), "云协作");
        MenuItemEnty m4 = new MenuItemEnty(resources.getDrawable(R.drawable.menu_more_icon), "更多");
        groupList.add(m1);
        groupList.add(m2);
        groupList.add(m3);
        groupList.add(m4);
        // child value
        List<MenuItemEnty> chlid1 = new ArrayList<MenuItemEnty>();
        List<MenuItemEnty> chlid2 = new ArrayList<MenuItemEnty>();
        List<MenuItemEnty> chlid3 = new ArrayList<MenuItemEnty>();
        List<MenuItemEnty> chlid4 = new ArrayList<MenuItemEnty>();
        childList = new ArrayList<List<MenuItemEnty>>();
        MenuItemEnty c1 = new MenuItemEnty(resources.getDrawable(R.drawable.menu_favor_icon), "我的收藏");
        MenuItemEnty c2 = new MenuItemEnty(resources.getDrawable(R.drawable.menu_img_transit_icon), "图片中转站");
        chlid4.add(c1);
        chlid4.add(c2);
        childList.add(chlid1);
        childList.add(chlid2);
        childList.add(chlid3);
        childList.add(chlid4);
    }

    public void setGPosition(int gPosition){
        this.gPosition = gPosition;
    }

    public void setcPosition(int cPosition){
        this.cPosition = cPosition;
    }

    public void setIsChilked(boolean isChilked){
        this.isChilked =isChilked;
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
        return true;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHandler groupViewHandler = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.menu_group_list_item, null);
            groupViewHandler = new GroupViewHandler();
            groupViewHandler.init(convertView);
            convertView.setTag(groupViewHandler);
        } else {
            groupViewHandler = (GroupViewHandler) convertView.getTag();
        }
        MenuItemEnty groupEnty = (MenuItemEnty) getGroup(groupPosition);
        groupViewHandler.getImIcon().setImageDrawable(groupEnty.getDrawable());
        groupViewHandler.getTeItName().setText(groupEnty.getItemName());

        // state icon visible
        if (groupPosition == 3) {
            groupViewHandler.getImClick().setVisibility(View.VISIBLE);
        }
        if (isExpanded) {
            Drawable collapse = context.getResources().getDrawable(R.drawable.menu_more_collapse);
            groupViewHandler.getImClick().setImageDrawable(collapse);
        } else {
            Drawable expand = context.getResources().getDrawable(R.drawable.menu_more_expand);
            groupViewHandler.getImClick().setImageDrawable(expand);
        }
        // point state
        if(gPosition == groupPosition && isChilked){
            groupViewHandler.getImPoint().setVisibility(View.VISIBLE);
        } else {
            groupViewHandler.getImPoint().setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHandler childViewHandler = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.menu_child_list_item, null);
            childViewHandler = new ChildViewHandler();
            childViewHandler.init(convertView);
            convertView.setTag(childViewHandler);
        } else {
            childViewHandler = (ChildViewHandler) convertView.getTag();
        }
        MenuItemEnty menuItemEnty = (MenuItemEnty) getChild(groupPosition, childPosition);
        childViewHandler.getImIcons().setImageDrawable(menuItemEnty.getDrawable());
        childViewHandler.getTeItNames().setText(menuItemEnty.getItemName());

        //point state
        if(cPosition == childPosition && !isChilked){
            childViewHandler.getImPoints().setVisibility(View.VISIBLE);
        } else {
            childViewHandler.getImPoints().setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    private class GroupViewHandler {
        ImageView imPoint;
        ImageView imIcon;
        ImageView imClick;
        TextView teItName;

        public void init(View view) {
            imPoint = (ImageView) view.findViewById(R.id.im_point);
            imIcon = (ImageView) view.findViewById(R.id.im_icon);
            imClick = (ImageView) view.findViewById(R.id.im_click);
            teItName = (TextView) view.findViewById(R.id.te_itName);
        }

        public ImageView getImPoint() {
            return imPoint;
        }

        public ImageView getImIcon() {
            return imIcon;
        }

        public ImageView getImClick() {
            return imClick;
        }

        public TextView getTeItName() {
            return teItName;
        }
    }

    private class ChildViewHandler {
        ImageView imPoints;
        ImageView imIcons;
        TextView teItNames;

        public void init(View view) {
            imPoints = (ImageView) view.findViewById(R.id.im_points);
            imIcons = (ImageView) view.findViewById(R.id.im_icons);
            teItNames = (TextView) view.findViewById(R.id.te_itNames);
        }

        public ImageView getImPoints() {
            return imPoints;
        }

        public ImageView getImIcons() {
            return imIcons;
        }

        public TextView getTeItNames() {
            return teItNames;
        }
    }
}
