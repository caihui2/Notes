package com.netos.activity.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.netos.activity.AddNotesActivity;
import com.netos.activity.R;
import com.netos.activity.adapter.NotesDataAdapter;
import com.netos.activity.adapter.SpinnerDataAdapter;
import com.netos.darabase.DBUrils;
import com.notos.entity.NotesObjInfo;

import java.util.List;

/**
 * Created by yangcaihui on 15/2/14.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    private static final int RESQUESTCODE = 1;
    View rootView;
    Spinner spTitle;
    Context mActivity;
    ListView lvNotes;
    ImageView imAddNOtes;

    List<NotesObjInfo> mNotesObjInfoList;
    NotesDataAdapter noteAdapter;
    SpinnerDataAdapter adapter;
    DBUrils mDbUrils;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.hf_home_fragment, null);
        init();
        return rootView;
    }

    void init() {
        mActivity = getActivity();

        mDbUrils = new DBUrils(mActivity, false);

        spTitle = (Spinner) rootView.findViewById(R.id.sp_title);
        lvNotes = (ListView) rootView.findViewById(R.id.lv_notes);
        showTypeData();

        imAddNOtes = (ImageView) rootView.findViewById(R.id.im_addNote);
        imAddNOtes.setOnClickListener(this);
    }

    void showTypeData() {
        noteAdapter = new NotesDataAdapter(mActivity);
        adapter = new SpinnerDataAdapter(mActivity, mDbUrils);
        boolean dataState = adapter.listState();
        if (dataState) {
            spTitle.setAdapter(adapter);
        }
        spTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String type = (String) adapter.getItem(position);
                if (type.equals("全部笔记")) {
                    mNotesObjInfoList = mDbUrils.querys();
                    noteAdapter.setList(mNotesObjInfoList);
                    if(mNotesObjInfoList != null){
                        lvNotes.setAdapter(noteAdapter);
                    }
                } else {
                    if (type != null) {
                        mNotesObjInfoList = mDbUrils.querysTypeObjCount(type);
                        if (mNotesObjInfoList != null) {
                            noteAdapter.setList(mNotesObjInfoList);
                        } else {
                            lvNotes.setAdapter(null);
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_addNote:
                Intent mIntent = new Intent(mActivity, AddNotesActivity.class);
                startActivityForResult(mIntent, RESQUESTCODE);
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESQUESTCODE && resultCode == Activity.RESULT_OK) {
            NotesObjInfo mNotesObjInfo = (NotesObjInfo) data.getSerializableExtra(AddNotesActivity.ADDRESULT);
            if (mNotesObjInfo != null) {
                mNotesObjInfoList.add(mNotesObjInfo);
                noteAdapter.notifyDataSetChanged();
            }

        }


    }
}
