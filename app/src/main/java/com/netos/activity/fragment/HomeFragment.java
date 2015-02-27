package com.netos.activity.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.netos.activity.AddNotesActivity;
import com.netos.activity.R;
import com.netos.activity.adapter.NotesDataAdapter;
import com.netos.darabase.DBUrils;
import com.notos.entity.NotesObjInfo;

import java.util.List;

/**
 * Created by yangcaihui on 15/2/14.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{
     View rootView;
     Spinner spTitle;
     Context mActivity;
     ListView lvNotes;
     ImageView imAddNOtes;

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
        List<NotesObjInfo> mNotesObjInfoList = db.querys();
        if(mNotesObjInfoList != null) {
            NotesDataAdapter noteAdapter = new NotesDataAdapter(mActivity,mNotesObjInfoList);
            lvNotes.setAdapter(noteAdapter);

        imAddNOtes = (ImageView)rootView.findViewById(R.id.im_addNote);
        imAddNOtes.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.im_addNote:
                Intent mIntent = new Intent(mActivity, AddNotesActivity.class);
                mActivity.startActivity(mIntent);
                break;
        }
    }
}
