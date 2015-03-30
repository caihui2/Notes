package activity.netos.com.notes.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import activity.netos.com.notes.R;
import activity.netos.com.notes.entity.MenuItemEnty;

/**
 * Created by yangcaihui on 15/3/30.
 */
public class MenuListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<MenuItemEnty> menuList;
    public MenuListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        menuList = new ArrayList<MenuItemEnty>();
        Resources resources = context.getResources();
        MenuItemEnty m1 = new MenuItemEnty(resources.getDrawable(R.drawable.menu_all_note_icon),"全部笔记");
        MenuItemEnty m2 = new MenuItemEnty(resources.getDrawable(R.drawable.menu_note_book_icon),"笔记本");
        MenuItemEnty m3 = new MenuItemEnty(resources.getDrawable(R.drawable.menu_group_icon),"云协作");
        menuList.add(m1);menuList.add(m2);menuList.add(m3);
    }
    @Override
    public int getCount() {
        return menuList.size();
    }

    @Override
    public Object getItem(int position) {
        return menuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHandler mViewHandler = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.menu_list_item,null);
            mViewHandler = new ViewHandler();
            mViewHandler.init(convertView);
            convertView.setTag(mViewHandler);
        }else{
            mViewHandler = (ViewHandler)convertView.getTag();
        }
        MenuItemEnty itemEnty = (MenuItemEnty)getItem(position);
        mViewHandler.getImIcon().setImageDrawable(itemEnty.getDrawable());
        mViewHandler.getTeItName().setText(itemEnty.getItemName());
        return convertView;
    }

    private class ViewHandler{
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
