package activity.netos.com.notes.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by yangcaihui on 15/4/3.
 */
public class AllNoteFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView mTextView = new TextView(getActivity());
        mTextView.setTextSize(110);
        mTextView.setText("我是哈哈");
        return mTextView;
    }
}
