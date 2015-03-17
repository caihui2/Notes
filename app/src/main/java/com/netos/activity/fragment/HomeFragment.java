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
import com.netos.darabase.NoteUtil;
import com.notos.entity.NoteEntity;

import java.util.List;

/**
 * Created by yangcaihui on 15/2/14.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    private static final int REFRESH_RECODE = 1;
    public static final int UP_DA_RECODE = 2;
    public static final String ACTION_UP_DA = "action_update_data";
    public static final String UP_ID = "update_id";
    public static final String ALTER_NOTE_OBJ = "alter_note_obj";
    private List<NoteEntity> noteEntityList;
    private NoteUtil dbUtil;
    private NotesDataAdapter nAdter;
    private SpinnerDataAdapter sAdter;

    View rootView;
    Spinner sPinner;
    Context mContext;
    ListView lvNote;
    ImageView iAddNote;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.hf_home_fragment, null);
        initData();
        initContent();
        return rootView;
    }

    private void initData() {
        mContext = getActivity();
        dbUtil = new NoteUtil(mContext, false);
        sPinner = (Spinner) rootView.findViewById(R.id.sp_title);
        lvNote = (ListView) rootView.findViewById(R.id.lv_notes);
        iAddNote = (ImageView) rootView.findViewById(R.id.im_addNote);
        iAddNote.setOnClickListener(this);
    }

    private void initContent() {
        sAdter = new SpinnerDataAdapter(mContext, dbUtil);
        sPinner.setAdapter(sAdter);
        nAdter = new NotesDataAdapter(mContext, dbUtil);
        sPinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String stpName = (String) sAdter.getItem(position);
                nAdter.dataChange(stpName);
                if (nAdter.isDataEmpty()) {
                    lvNote.setAdapter(nAdter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REFRESH_RECODE && resultCode == Activity.RESULT_OK) {

            //TODO
            if (requestCode == UP_DA_RECODE && requestCode == Activity.RESULT_OK) {

            }
        }
    }

    /**
     * click add note activity
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_addNote:
                Intent mIntent = new Intent(mContext, AddNotesActivity.class);
                startActivityForResult(mIntent, REFRESH_RECODE);
                break;
        }
    }
}
