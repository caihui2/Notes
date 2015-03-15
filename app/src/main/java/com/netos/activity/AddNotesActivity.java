package com.netos.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.netos.activity.fragment.HomeFragment;
import com.netos.darabase.NoteUtil;

import java.text.SimpleDateFormat;
import java.util.Date;


public class AddNotesActivity extends Activity implements View.OnClickListener {
    public static final int REQUECT_TYPE_NAME = 1 ;
    int alterId = -1;
    String typeName = "全部笔记";
    ImageButton imHandwriting, imGallery, imCamera, imRecord, imTodo2, imMore;
    EditText edTItlle, edContent;
    NoteUtil mDbUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.an_add_notes);
        initData();
    }

    void initData() {
        imHandwriting = (ImageButton) findViewById(R.id.im_handwriting);
        imHandwriting.setOnClickListener(this);
        imGallery = (ImageButton) findViewById(R.id.im_gallery);
        imGallery.setOnClickListener(this);
        imCamera = (ImageButton) findViewById(R.id.im_camera);
        imCamera.setOnClickListener(this);
        imRecord = (ImageButton) findViewById(R.id.im_record);
        imRecord.setOnClickListener(this);
        imTodo2 = (ImageButton) findViewById(R.id.im_todo2);
        imTodo2 = (ImageButton) findViewById(R.id.im_todo2);
        imMore = (ImageButton) findViewById(R.id.im_more);
        imMore.setOnClickListener(this);
        edTItlle = (EditText) findViewById(R.id.ed_title);
        edContent = (EditText) findViewById(R.id.ed_content);
        mDbUtils = new NoteUtil(this, true);
    }


    public void closeWindow(View view) {
        //TODO
        saveNote();
    }

    public void setNoteType(View view) {
        //TODO
        Intent mIntent = new Intent(this, SelectNoteTypeActivity.class);
        startActivityForResult(mIntent, REQUECT_TYPE_NAME);
    }

    @Override
    public void onClick(View v) {
        //TODO
    }

    public void saveNote() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUECT_TYPE_NAME && resultCode == RESULT_OK) {
            typeName = data.getStringExtra(SelectNoteTypeActivity.TYPE_NAME);

        }
    }
}
