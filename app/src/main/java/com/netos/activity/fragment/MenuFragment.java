package com.netos.activity.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.netos.activity.R;
import com.netos.activity.adapter.ExpListViewMenuSelectAdapter;
import com.netos.activity.adapter.ListViewMenuSelectAdapter;

/**
 * Created by yangcaihui on 15/2/9.
 */
public class MenuFragment extends Fragment {
    private View mvRoot;
    private ListView lvMenuSelect;
    private ExpandableListView  elvMenuSelect;
    private Context mainActivity ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mvRoot = inflater.inflate(R.layout.mf_menu_fragment, null);
        init();
        return mvRoot;
    }

    public void init() {
        mainActivity = getActivity();
        lvMenuSelect = (ListView) mvRoot.findViewById(R.id.lv_meun_select);
        final ListViewMenuSelectAdapter adapter = new ListViewMenuSelectAdapter(mainActivity);
        lvMenuSelect.setAdapter(adapter);


        elvMenuSelect = (ExpandableListView)mvRoot.findViewById(R.id.elv_menu_select);
        ExpListViewMenuSelectAdapter eAdapter = new ExpListViewMenuSelectAdapter(mainActivity);
        elvMenuSelect.setAdapter(eAdapter);
        menuItemSelectPointVisible(adapter,eAdapter);


    }

    public void menuItemSelectPointVisible(final ListViewMenuSelectAdapter adapter,
                                           final ExpListViewMenuSelectAdapter eAdapter) {
        lvMenuSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 if(eAdapter.isChildPointState()){
                     eAdapter.setChildPointState(false);
                     eAdapter.notifyDataSetChanged();
                 }
                adapter.setListPointState(true);
                adapter.setSelectPosition(position);
                adapter.notifyDataSetChanged();
            }
        });
        elvMenuSelect.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if(adapter.isListPointState()){
                    adapter.setListPointState(false);
                    adapter.notifyDataSetChanged();
                }
                eAdapter.setChildPointState(true);
                eAdapter.setChildPosition(childPosition);
                eAdapter.notifyDataSetInvalidated();
                return true;
            }
        });

    }
}
