package activity.netos.com.notes.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import activity.netos.com.notes.R;
import activity.netos.com.notes.adapter.MenuListAdapter;

/**
 * Created by yangcaihui on 15/3/23.
 */
public class MenuFragment extends Fragment implements ExpandableListView.OnGroupClickListener,ExpandableListView.OnChildClickListener{
    private View menuLayout;
    private Context mContext;
    // view init
    private ExpandableListView lvMenu;

    //adapter
    private MenuListAdapter menuListAdapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView(inflater,container);
        initManage();
        return menuLayout;
    }

    public void initView(LayoutInflater inflater,ViewGroup container){
      mContext = getActivity();
      menuLayout = (View)inflater.inflate(R.layout.fragment_menu,null);
      lvMenu = (ExpandableListView)menuLayout.findViewById(R.id.lv_menu);
    }

    public void initManage(){
      menuListAdapter = new MenuListAdapter(mContext);
      lvMenu.setGroupIndicator(null);
      lvMenu.setAdapter(menuListAdapter);
      menuListAdapter.setIsChilked(true);
      lvMenu.setOnGroupClickListener(this);
      lvMenu.setOnChildClickListener(this);
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        if(menuListAdapter != null && groupPosition != 3){
           menuListAdapter.setGPosition(groupPosition);
           menuListAdapter.setIsChilked(true);
           menuListAdapter.notifyDataSetChanged();
        }
        return false;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        if(menuListAdapter != null){
            menuListAdapter.setcPosition(childPosition);
            menuListAdapter.setIsChilked(false);
            menuListAdapter.notifyDataSetChanged();
        }
        return false;
    }
}
