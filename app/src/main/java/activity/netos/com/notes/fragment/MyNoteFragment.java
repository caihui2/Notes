package activity.netos.com.notes.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import activity.netos.com.notes.R;

/**
 * Created by yangcaihui on 15/4/4.
 */
public class MyNoteFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView mTextView = new TextView(getActivity());
        mTextView.setTextSize(110);
        mTextView.setText("我是笔记本");
        return mTextView;
    }
}
