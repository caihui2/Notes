package com.netos.activity.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.netos.activity.R;
import com.netos.activity.adapter.NotesDataAdapter;
import com.netos.darabase.DBUrils;
import com.notos.entity.NotesObjInfo;

import java.util.List;

/**
 * Created by yangcaihui on 15/2/14.
 */
public class HomeFragment extends Fragment {
     View rootView;
     Spinner spTitle;
     Context mActivity;
     ListView lvNotes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       rootView = inflater.inflate(R.layout.hf_home_fragment,null);
        init();
        return rootView;
    }

    void  init(){
       mActivity = getActivity();
       spTitle = (Spinner)rootView.findViewById(R.id.sp_title);
       String str[] = {"全部笔记","默认笔记"};
       ArrayAdapter<String> adapter = new ArrayAdapter<String>(mActivity,
               android.R.layout.simple_spinner_dropdown_item,str);
       spTitle.setAdapter(adapter);

       lvNotes = (ListView)rootView.findViewById(R.id.lv_notes);
        DBUrils db = new DBUrils(mActivity,false);
        //db.add(new NotesObjInfo("笔记","阿萨达卡收到记录刷卡的进来卡萨多久开了撒快点就","2015年2月26日",1));
        List<NotesObjInfo> mNotesObjInfoList = db.querys();
        if(mNotesObjInfoList != null) {
            NotesDataAdapter noteAdapter = new NotesDataAdapter(mActivity,mNotesObjInfoList);
            lvNotes.setAdapter(noteAdapter);
        }
    }

}
