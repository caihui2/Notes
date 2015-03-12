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
import com.netos.darabase.DBUtils;
import com.notos.entity.NotesObjInfo;

import java.text.SimpleDateFormat;
import java.util.Date;


public class AddNotesActivity extends Activity implements View.OnClickListener {

    private static final int REQUESTCODE = 1;
    public static final String ADDRESULT = "addResult";
    public static final String ALTEERRESULT = "alterResult";
    int alterId = -1;
    String typeName = "全部笔记";
    ImageButton imHandwriting, imGallery, imCamera, imRecord, imTodo2, imMore;
    EditText edTItlle, edContent;
    DBUtils mDbUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.an_add_notes);
        init();
        alterNoteData();
    }

    void init() {
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

        mDbUtils = new DBUtils(this, true);
    }

    public void alterNoteData() {
        Intent mIntent = getIntent();
        alterId = mIntent.getIntExtra(HomeFragment.UP_ID, -1);
        if (alterId != -1) {
            NotesObjInfo mNotesObjInfo = (NotesObjInfo)
                    mIntent.getSerializableExtra(HomeFragment.ALTER_NOTE_OBJ);
            edTItlle.setText(mNotesObjInfo.getTitle());
            edContent.setText(mNotesObjInfo.getContent());
        }
    }

    public void closeWindow(View view) {
        //TODO
        saveNote();
    }

    public void setNoteType(View view) {
        //TODO
        Intent mIntent = new Intent(this, SelectNoteTypeActivity.class);
        startActivityForResult(mIntent, REQUESTCODE);
    }

    @Override
    public void onClick(View v) {
        //TODO
    }

    public void saveNote() {
        String title = edTItlle.getText().toString().trim();
        String content = edContent.getText().toString().trim();
        if (alterId != -1) {
            if (TextUtils.isEmpty(title) && TextUtils.isEmpty(content)) {
                finish();
            } else {
                SimpleDateFormat format = new SimpleDateFormat("yyyy年mm月dd日 HH:mm");
                String time = format.format(new Date(System.currentTimeMillis()));
                NotesObjInfo mNotesObjInfo = new NotesObjInfo(content, title, time, typeName, 0);
                long result = mDbUtils.wIdUpNote(alterId, mNotesObjInfo);
                if (result > 0) {
                    Intent mIntent = getIntent();
                    setResult(RESULT_OK, mIntent);
                    Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
                }

            }
        }else {
            if (TextUtils.isEmpty(title) && TextUtils.isEmpty(content)) {
                finish();
            } else {
                SimpleDateFormat format = new SimpleDateFormat("yyyy年mm月dd日 HH:mm");
                String time = format.format(new Date(System.currentTimeMillis()));
                NotesObjInfo mNotesObjInfo = new NotesObjInfo(content, title, time, typeName, 0);
                long result = mDbUtils.addNote(mNotesObjInfo);
                if (result > 0) {
                    Intent mIntent = getIntent();
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable(ADDRESULT, mNotesObjInfo);
                    mIntent.putExtras(mBundle);
                    setResult(RESULT_OK, mIntent);
                    Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && resultCode == RESULT_OK) {
            typeName = data.getStringExtra(SelectNoteTypeActivity.TYPE_NAME);

        }
    }
}
