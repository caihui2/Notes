package com.netos.activity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yangcaihui on 15/2/8.
 */
public class SlideMenu extends ViewGroup {

    public SlideMenu(Context context) {
        this(context,null,0);
    }

    public SlideMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        View menuView = getChildAt(0);
        int menuViewWidth = menuView.getLayoutParams().width+menuView.getLeft()+menuView.getRight();
        menuView.measure(menuViewWidth,heightMeasureSpec);
        View mainView = getChildAt(1);
        mainView.measure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int viewCount = getChildCount();
        if(viewCount != 2){
            new RuntimeException("必须要两个view");
        }
        View meunView = getChildAt(0);
        meunView.layout(-meunView.getMeasuredWidth(),0,0,meunView.getMeasuredHeight());
        View mainView = getChildAt(1);
        mainView.layout(0,0,mainView.getMeasuredWidth(),mainView.getMeasuredHeight());
    }
}
