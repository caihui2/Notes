package activity.netos.com.notes.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.List;

import activity.netos.com.notes.NoteMainAc;
import activity.netos.com.notes.R;

/**
 * Created by yangcaihui on 15/4/3.
 */
public class AllNoteFragment extends Fragment {
    private NoteMainAc mContext;
    private LayoutInflater mInflater;
    private ViewGroup allView;
    private ImageButton imSild;
    private Spinner spType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        init(container);
        initData();
        return allView;
    }

    public void init(ViewGroup container){
        mContext = (NoteMainAc)getActivity();
        mInflater = LayoutInflater.from(mContext);
        allView = (ViewGroup)mInflater.inflate(R.layout.fragment_all_note,container,false);
        imSild = (ImageButton)allView.findViewById(R.id.im_slid);
        spType = (Spinner)allView.findViewById(R.id.sp_type);
    }

    public void initData(){
        imSild.setOnClickListener(mContext);
        setSpinnerAdapter(spType);
    }

    public void setSpinnerAdapter(Spinner sp){
        List<String> data = new ArrayList<String>();
        data.add("全部笔记本");
        data.add("默认笔记");
        ArrayAdapter<String> spAdapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_dropdown_item,data);
        sp.setAdapter(spAdapter);
    }

}
