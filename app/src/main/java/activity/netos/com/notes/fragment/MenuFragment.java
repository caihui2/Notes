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
public class MenuFragment extends Fragment {
    private View menuLayout;
    private Context mContext;
    // view init
    private ExpandableListView lvMenu;

    //adapter
    private MenuListAdapter menuListAdapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView(inflater,container);
        initAdapter();
        return menuLayout;
    }

    public void initView(LayoutInflater inflater,ViewGroup container){
      mContext = getActivity();
      menuLayout = (View)inflater.inflate(R.layout.fragment_menu,null);
      lvMenu = (ExpandableListView)menuLayout.findViewById(R.id.lv_menu);
    }

    public void initAdapter(){
        menuListAdapter = new MenuListAdapter(mContext);
        lvMenu.setAdapter(menuListAdapter);
    }


}
