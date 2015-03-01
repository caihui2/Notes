package com.netos.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.netos.activity.adapter.SelectNoteTypeAdapter;
import com.netos.darabase.DBUrils;

import java.util.List;


public class SelectNoteTypeActivity extends Activity {
    ListView lvSelectType;
    DBUrils mDbUrils;
    List<String> tpList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snt_select_note_type);
        init();
    }

    public void init(){
        showCountType();
    }

    public void closeWindow(View view) {
        //TODO
        finish();
    }

    public void addNotype(View view){

    }

    public void showCountType(){
        lvSelectType = (ListView)findViewById(R.id.lv_selectType);
        mDbUrils = new DBUrils(this,false);
        tpList = mDbUrils.querysType();
        if(tpList != null){
            final SelectNoteTypeAdapter adapter = new SelectNoteTypeAdapter(this,tpList);
            lvSelectType.setAdapter(adapter);
            lvSelectType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    adapter.setSPosition(position);
                    adapter.notifyDataSetChanged();
                }
            });
        }

    }




}
