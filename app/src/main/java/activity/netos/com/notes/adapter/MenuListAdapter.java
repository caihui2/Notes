package activity.netos.com.notes.adapter;

import android.content.Context;
import android.content.res.Resources;
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
    private List<MenuItemEnty> groupList;
    private List<List<MenuItemEnty>> childList;
    private int itmPosition;
    public MenuListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        groupList = new ArrayList<MenuItemEnty>();
        Resources resources = context.getResources();
        //group value
        MenuItemEnty m1 = new MenuItemEnty(resources.getDrawable(R.drawable.menu_all_note_icon),"全部笔记");
        MenuItemEnty m2 = new MenuItemEnty(resources.getDrawable(R.drawable.menu_note_book_icon),"笔记本");
        MenuItemEnty m3 = new MenuItemEnty(resources.getDrawable(R.drawable.menu_group_icon),"云协作");
        MenuItemEnty m4 = new MenuItemEnty(resources.getDrawable(R.drawable.menu_more_icon),"更多");
        groupList.add(m1);groupList.add(m2);groupList.add(m3); groupList.add(m4);
        // child value
        List<MenuItemEnty> chlid = new ArrayList<MenuItemEnty>();
        childList = new ArrayList<List<MenuItemEnty>>();
        MenuItemEnty c1 = new MenuItemEnty(resources.getDrawable(R.drawable.menu_favor_icon),"我的收藏");
        MenuItemEnty c2 = new MenuItemEnty(resources.getDrawable(R.drawable.menu_img_transit_icon),"图片中转站");
        chlid.add(c1);chlid.add(c2);
        childList.add(3,chlid);
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHandler groupViewHandler = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.menu_group_list_item,null);
            groupViewHandler = new GroupViewHandler();
            groupViewHandler.init(convertView);
            convertView.setTag(groupViewHandler);
        }else{
            groupViewHandler = (GroupViewHandler)convertView.getTag();
        }
            MenuItemEnty groupEnty = (MenuItemEnty)getGroup(groupPosition);
            groupViewHandler.getImIcon().setImageDrawable(groupEnty.getDrawable());
            groupViewHandler.getTeItName().setText(groupEnty.getItemName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    private class GroupViewHandler{
        ImageView imPoint;
        ImageView imIcon;
        TextView teItName;

        public void init(View view){
            imPoint  = (ImageView)view.findViewById(R.id.im_point);
            imIcon   = (ImageView)view.findViewById(R.id.im_icon);
            teItName = (TextView)view.findViewById(R.id.te_itName);
        }

        public ImageView getImPoint() {
            return imPoint;
        }

        public ImageView getImIcon() {
            return imIcon;
        }

        public TextView getTeItName() {
            return teItName;
        }
    }
}
