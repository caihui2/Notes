package activity.netos.com.notes;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.LinearLayout;

import activity.netos.com.notes.fragment.AllNoteFragment;
import activity.netos.com.notes.fragment.CldTeamFragment;
import activity.netos.com.notes.fragment.CollectFragment;
import activity.netos.com.notes.fragment.MenuFragment;
import activity.netos.com.notes.fragment.MyNoteFragment;
import activity.netos.com.notes.fragment.PoTransferFragment;


public class NoteMainAc extends Activity {

    private LinearLayout contentFr;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    // fragment define
    private MenuFragment menuFragment;
    private AllNoteFragment allNoteFragment;
    private MyNoteFragment myNoteFragment;
    private CldTeamFragment cldTeamFragment;
    private CollectFragment collectFragment;
    private PoTransferFragment poTransferFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_main);
        init();
        setReplaceId(0, true);
    }

    public void init() {
        // fragment init
        allNoteFragment = new AllNoteFragment();
        myNoteFragment = new MyNoteFragment();
        cldTeamFragment = new CldTeamFragment();
        collectFragment = new CollectFragment();
        poTransferFragment = new PoTransferFragment();

    }

    public void replaceFragment(Fragment fragment) {
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.content_fr, fragment);
        transaction.commit();
    }

    public void setReplaceId(int position, boolean isMode) {
        if (isMode) {
            switch (position) {
                case 0:
                    replaceFragment(allNoteFragment);
                    break;
                case 1:
                    replaceFragment(myNoteFragment);
                    break;
                case 2:
                    replaceFragment(cldTeamFragment);
                    break;
            }
        }
        if (!isMode) {
            switch (position) {
                case 0:
                    replaceFragment(collectFragment);
                    break;
                case 1:
                    replaceFragment(poTransferFragment);
                    break;
            }
        }
    }

}
