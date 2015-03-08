package com.netos.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.netos.activity.adapter.SelectNoteTypeAdapter;
import com.netos.activity.view.SwipeListView.SwipeMenu;
import com.netos.activity.view.SwipeListView.SwipeMenuCreator;
import com.netos.activity.view.SwipeListView.SwipeMenuItem;
import com.netos.activity.view.SwipeListView.SwipeMenuListView;
import com.netos.darabase.DBUrils;
import com.notos.entity.NotesObjInfo;

import java.util.List;


public class SelectNoteTypeActivity extends Activity {
    SwipeMenuListView lvSelectType;
    DBUrils mDbUrils;
    List<String> tpList;
    SelectNoteTypeAdapter adapter;
    public static final String TYPE_NAME = "type_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snt_select_note_type);
        init();
    }

    public void init() {
        showListView();
    }

    public void closeWindow(View view) {
        Toast.makeText(SelectNoteTypeActivity.this, "未选择类型", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void addNotype(View view) {
        showAddNotypeDialog();
    }

    public void showListView() {
        lvSelectType = (SwipeMenuListView) findViewById(R.id.lv_selectType);
        mDbUrils = new DBUrils(this, false);
        tpList = mDbUrils.querysType();

        if (tpList != null) {
            adapter = new SelectNoteTypeAdapter(this, tpList);
            lvSelectType.setAdapter(adapter);
            lvSelectType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    adapter.setSPosition(position);
                    adapter.setVState(true);
                    adapter.notifyDataSetChanged();
                    String typeName = (String) adapter.getItem(position);
                    Intent mIntent = getIntent();
                    mIntent.putExtra(TYPE_NAME, typeName);
                    setResult(RESULT_OK, mIntent);
                    Toast.makeText(SelectNoteTypeActivity.this, "笔记类型：" + typeName, Toast.LENGTH_SHORT)
                            .show();
                    finish();
                }
            });
        }

        //侧滑选项
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                deleteItem.setWidth(dp2px(90));
                deleteItem.setIcon(R.drawable.ic_delete);
                menu.addMenuItem(deleteItem);
            }
        };

        lvSelectType.setMenuCreator(creator);

        //侧滑删除按钮监听
        lvSelectType.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        String nameType = (String) adapter.getItem(position);
                        List<NotesObjInfo> mInfoList = mDbUrils.querysTypeObjCount(nameType);
                        mInfoList.size();
                        if (mInfoList.size() <= 0) {
                            int DResult = mDbUrils.deleteType(nameType);
                            if (DResult <= 0) {
                                Toast.makeText(SelectNoteTypeActivity.this, "删除失败，请重新删除",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                tpList.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        } else {
                            showDtDialog(nameType);
                            int DResult = mDbUrils.deleteType(nameType);
                            if (DResult <= 0) {
                                Toast.makeText(SelectNoteTypeActivity.this, "删除失败，请重新删除",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                tpList.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        }

                        break;
                }
                return false;
            }
        });

    }

    //增加类型
    public void showAddNotypeDialog() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle("添加笔记本类型");
        final EditText mEditText = new EditText(this);
        mBuilder.setView(mEditText);
        mBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tStr = mEditText.getText().toString().trim();
                String result = mDbUrils.queryTypeItem(tStr);
                if (result == null) {
                    long connt = mDbUrils.addType(tStr);
                    if (connt > 0) {
                        tpList.add(tStr);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(SelectNoteTypeActivity.this, "类型已存在", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
        mBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        mBuilder.show();
    }

    public void showDtDialog(final String typeName) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle("提示");
        mBuilder.setMessage("是否删除此类型中的笔记？");
        mBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int result = mDbUrils.deleteTypeNote(typeName);
                if (result <= 0) {
                    Toast.makeText(SelectNoteTypeActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        mBuilder.show();

    }


    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

}
