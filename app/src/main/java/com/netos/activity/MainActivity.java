package com.netos.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.netos.activity.view.SlideMenu;


public class MainActivity extends Activity {
    private SlideMenu mSlideMenu;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSlideMenu = (SlideMenu)findViewById(R.id.slideMenu);
        mButton =(Button)findViewById(R.id.bu);

    }

    public void onClicks(View view){
      if(mSlideMenu.isMainScreenShowing()){
          mSlideMenu.openMenu();
      }else{
          mSlideMenu.closeMenu();
      }
    }

}
