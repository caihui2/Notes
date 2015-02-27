package com.netos.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;



public class AddNotesActivity extends Activity implements View.OnClickListener {

    private static final int REQUESTCODE = 1;
    ImageButton imHandwriting, imGallery, imCamera, imRecord, imTodo2, imMore;
    EditText edTItlle,edContent;

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
    }

    public void closeWindow(View view) {
        //TODO
        finish();
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
}
