package com.netos.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.netos.activity.fragment.CloudFragment;
import com.netos.activity.fragment.CollectionFragment;
import com.netos.activity.fragment.HomeFragment;
import com.netos.activity.fragment.MenuFragment;
import com.netos.activity.fragment.NetosFragment;
import com.netos.activity.fragment.PictureFragment;
import com.netos.activity.view.SlideMenu;


public class MainActivity extends Activity implements MenuFragment.NextScreenNumder {
    private SlideMenu mSlideMenu;

    private MenuFragment mMenuFragment;

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    void init() {
        mFragmentManager = getFragmentManager();
        mMenuFragment = (MenuFragment) mFragmentManager.findFragmentById(R.id.menu_fragment);
        mMenuFragment.setNextScreenNumder(this);
        exchangeFm(new HomeFragment());

        mSlideMenu = (SlideMenu)findViewById(R.id.slideMenu);
    }

    //MenuFragment Signal communication
    @Override
    public void setNextFragment(int mCurrentScteen) {
        switch (mCurrentScteen) {
            case MenuFragment.MEUN_ID_HOME:
                exchangeFm(new HomeFragment());
                break;
            case MenuFragment.MENU_ID_NETOS:
                exchangeFm(new NetosFragment());
                break;
            case MenuFragment.MENU_ID_CLOUD:
                exchangeFm(new CloudFragment());
                break;
            case MenuFragment.MENU_ID_MYCOLLECTION:
                exchangeFm(new CollectionFragment());
                break;
            case MenuFragment.MENU_ID_PICTURE:
                exchangeFm(new PictureFragment());
                break;
        }
    }

    void exchangeFm(Fragment mFragment) {
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.content_fragment, mFragment);
        mFragmentTransaction.commit();
    }

    public void openMenu(View view){
       if(mSlideMenu.isMainScreenShowing()){
           mSlideMenu.openMenu();
       }else{
           mSlideMenu.closeMenu();
       }
    }
    public void onClick(View voi){
       System.out.println("测试运行开始————");
    }

}
