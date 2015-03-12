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
import com.netos.darabase.DBUtils;
import com.notos.entity.NotesObjInfo;

import java.util.List;

/**
 * Created by yangcaihui on 15/2/14.
 */
public class HomeFragment extends Fragment implements View.OnClickListener,
        AdapterView.OnItemSelectedListener {
    private static final int REFRESH_RECODE  = 1;
    public static final int  UP_DA_RECODE  = 2;
    public static final String ACTION_UP_DA = "action_update_data";
    public static final String UP_ID = "update_id";
    public static final String ALTER_NOTE_OBJ = "alter_note_obj";
    private List<NotesObjInfo> nObjList;
    private DBUtils dBUtil;

    NotesDataAdapter nAdter;
    SpinnerDataAdapter sAdter;
    View rootView;
    Spinner sPinner;
    Context mContext;
    ListView lvNote;
    ImageView iAddNote;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.hf_home_fragment, null);
        init();
        return rootView;
    }

    private void init() {
        mContext = getActivity();

        dBUtil = new DBUtils(mContext, false);

        sPinner = (Spinner) rootView.findViewById(R.id.sp_title);
        lvNote = (ListView) rootView.findViewById(R.id.lv_notes);
        nAdter = new NotesDataAdapter(mContext);


        lvOnitemClick();

        iAddNote = (ImageView) rootView.findViewById(R.id.im_addNote);
        iAddNote.setOnClickListener(this);
    }

//    private void showTypeData() {
//        NDAadapter = new NotesDataAdapter(mContext);
//        adapter = new SpinnerDataAdapter(mContext, mDbUrils);
//        boolean dataState = adapter.listState();
//        if (dataState) {
//            sPinner.setAdapter(adapter);
//        }
//        sPinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String type = (String) adapter.getItem(position);
//                lvShowState(type);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//    }

    private void lvShowState(String type) {
        if (type.equals("全部笔记")) {
            nObjList = dBUtil.qyNote();
            if (nObjList != null) {
                nAdter = new NotesDataAdapter(mContext);
                nAdter.setList(nObjList);
                lvNote.setAdapter(nAdter);
            }
        } else {
            if (type != null) {
                nObjList = dBUtil.qyTypeCount(type);
                if (nObjList != null) {
                    nAdter = new NotesDataAdapter(mContext);
                    nAdter.setList(nObjList);
                    lvNote.setAdapter(sAdter);

                } else {
                    lvNote.setAdapter(null);
                }
            }
        }
    }

    private void lvOnitemClick() {
        lvNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NotesObjInfo mNotesObjInfo = nObjList.get(position);
                int noteId = dBUtil.qyId(mNotesObjInfo.getTitle());
                Intent alIntent = new Intent();
                alIntent.setAction(ACTION_UP_DA);
                alIntent.addCategory(Intent.CATEGORY_DEFAULT);
                alIntent.putExtra(UP_ID, noteId);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(ALTER_NOTE_OBJ, mNotesObjInfo);
                alIntent.putExtras(mBundle);
                startActivityForResult(alIntent, UP_DA_RECODE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_addNote:
                Intent mIntent = new Intent(mContext, AddNotesActivity.class);
                startActivityForResult(mIntent, REFRESH_RECODE);
                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REFRESH_RECODE && resultCode == Activity.RESULT_OK) {
            NotesObjInfo mNotesObjInfo = (NotesObjInfo) data.getSerializableExtra(AddNotesActivity.ADDRESULT);
            if (mNotesObjInfo != null) {
                lvShowState("全部笔记");
                sAdter.notifyDataSetInvalidated();
            }
            //TODO
            if (requestCode == UP_DA_RECODE && requestCode == Activity.RESULT_OK) {
                lvShowState("全部笔记");
                sAdter.notifyDataSetInvalidated();
            }
        }
    }
}
