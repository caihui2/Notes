package com.netos.activity.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

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
    private ExpandableListView elvMenuSelect;
    private Context mainActivity;

    public static final int MEUN_ID_HOME = 1;
    public static final int MENU_ID_NETOS = 2;
    public static final int MENU_ID_CLOUD = 3;
    public static final int MENU_ID_MYCOLLECTION = 4;
    public static final int MENU_ID_PICTURE = 5;
    public static int mCurrentScreen = MEUN_ID_HOME;

    private static final int SELECT_POSITION0 = 0;
    private static final int SELECT_POSITION1 = 1;
    private static final int SELECT_POSITION2 = 2;

    private NextScreenNumder mNextScreenNumder;

    public interface NextScreenNumder {
        public void setNextFragment(int mCurrentScteen);
    }

    public void setNextScreenNumder(NextScreenNumder mNextScreenNumder) {
        this.mNextScreenNumder = mNextScreenNumder;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mvRoot = inflater.inflate(R.layout.mf_menu_fragment, null);
        init();
        return mvRoot;
    }

    void init() {
        mainActivity = getActivity();
        lvMenuSelect = (ListView) mvRoot.findViewById(R.id.lv_meun_select);
        final ListViewMenuSelectAdapter adapter = new ListViewMenuSelectAdapter(mainActivity);
        lvMenuSelect.setAdapter(adapter);

        elvMenuSelect = (ExpandableListView) mvRoot.findViewById(R.id.elv_menu_select);
        ExpListViewMenuSelectAdapter eAdapter = new ExpListViewMenuSelectAdapter(mainActivity);
        elvMenuSelect.setAdapter(eAdapter);
        menuItemSelectPointVisible(adapter, eAdapter);


    }

    public void menuItemSelectPointVisible(final ListViewMenuSelectAdapter adapter,
                                           final ExpListViewMenuSelectAdapter eAdapter) {
        lvMenuSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (eAdapter.isChildPointState()) {
                    eAdapter.setChildPointState(false);
                    eAdapter.notifyDataSetChanged();
                }
                adapter.setListPointState(true);
                adapter.setSelectPosition(position);
                adapter.notifyDataSetChanged();

                if (mNextScreenNumder != null) {
                    switch (position) {
                        case SELECT_POSITION0:
                            mCurrentScreen = MEUN_ID_HOME;
                            break;
                        case SELECT_POSITION1:
                            mCurrentScreen = MENU_ID_NETOS;
                            break;
                        case SELECT_POSITION2:
                            mCurrentScreen = MENU_ID_CLOUD;
                            break;
                        default:
                            mCurrentScreen = MEUN_ID_HOME;
                            break;
                    }
                    mNextScreenNumder.setNextFragment(mCurrentScreen);
                } else {
                    new IllegalAccessException("MenuFragment中，接口NewScreenNumber为空");
                }

            }
        });
        elvMenuSelect.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (adapter.isListPointState()) {
                    adapter.setListPointState(false);
                    adapter.notifyDataSetChanged();
                }
                eAdapter.setChildPointState(true);
                eAdapter.setChildPosition(childPosition);
                mNextScreenNumder.setNextFragment(childPosition);
                eAdapter.notifyDataSetChanged();

                if (mNextScreenNumder != null) {
                    switch (childPosition) {
                        case SELECT_POSITION0:
                            mCurrentScreen = MENU_ID_MYCOLLECTION;
                            break;
                        case SELECT_POSITION1:
                            mCurrentScreen = MENU_ID_PICTURE;
                            break;
                    }
                    mNextScreenNumder.setNextFragment(mCurrentScreen);
                } else {
                    new IllegalAccessException("MenuFragment中，接口NewScreenNumber为空");
                }
                return true;
            }
        });

    }
}
