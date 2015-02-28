package com.netos.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.admin.DevicePolicyManager;

import android.content.ComponentName;
import android.os.Bundle;

import android.view.View;


public class SelectNoteTypeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snt_select_note_type);
    }

    public void closeWindow(View view) {
        //TODO
        finish();
    }

    public void addNotype(View view){
       AlertDialog.Builder  mBuilder= new AlertDialog.Builder(this);
        mBuilder.setTitle("增加类型");
        mBuilder.setMessage("时候");
        mBuilder.show();




    }




}
