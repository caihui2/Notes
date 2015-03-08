package com.netos.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.netos.darabase.DBUrils;
import com.notos.entity.NotesObjInfo;

import java.text.SimpleDateFormat;
import java.util.AbstractCollection;
import java.util.Date;


public class AddNotesActivity extends Activity implements View.OnClickListener {

    private static final int REQUESTCODE = 1;
    public static final String ADDRESULT = "addResult";
    String typeName = "全部笔记";
    ImageButton imHandwriting, imGallery, imCamera, imRecord, imTodo2, imMore;
    EditText edTItlle,edContent;
    DBUrils mDbUrils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.an_add_notes);
        init();
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

        edTItlle = (EditText)findViewById(R.id.ed_title);

        edContent = (EditText)findViewById(R.id.ed_content);

        mDbUrils = new DBUrils(this,true);
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

    public void saveNote(){
        String title = edTItlle.getText().toString().trim();
        String content = edTItlle.getText().toString().trim();
        if(TextUtils.isEmpty(title) && TextUtils.isEmpty(content)){
            finish();
        }else{
            SimpleDateFormat format = new SimpleDateFormat("yyyy年mm月dd日 HH:mm");
            String time = format.format(new Date(System.currentTimeMillis()));
            NotesObjInfo mNotesObjInfo = new NotesObjInfo(title,content,time,typeName,0);
            long result = mDbUrils.addNs(mNotesObjInfo);
            if(result > 0){
                Intent mIntent = getIntent();
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(ADDRESULT,mNotesObjInfo);
                mIntent.putExtras(mBundle);
                setResult(RESULT_OK,mIntent);
                Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(this,"保存失败",Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
     if(requestCode == REQUESTCODE && resultCode == RESULT_OK){
         typeName = data.getStringExtra(SelectNoteTypeActivity.TYPE_NAME);
         
     }
    }
}
