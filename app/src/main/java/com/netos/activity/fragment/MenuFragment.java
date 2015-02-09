package com.netos.activity.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.netos.activity.R;

/**
 * Created by yangcaihui on 15/2/9.
 */
public class MenuFragment extends Fragment {
    private  View mvRoot;
    private ListView lvMenuSelect;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mvRoot = inflater.inflate(R.layout.mf_menu_fragment,null);
        init();
        return mvRoot;
    }

    public void init(){
        lvMenuSelect = (ListView)mvRoot.findViewById(R.id.lv_meun_select);
    }
}
