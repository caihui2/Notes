package activity.netos.com.notes.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by yangcaihui on 15/3/18.
 */
public class SlideMenu extends ViewGroup {
    public static final int SCREEN_MENU = 0;
    public static final int SCREEN_MAIN = 1;
    public static final int SCREEN_INVALID = -1;

    private int mCurrentScreen;
    private int mNextScreen = SCREEN_INVALID;

    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int mTouchSlop;

    private float mLastMotionX;
    private float mLastMotionY;

    private final static int TOUCH_STATE_REST = 0;
    private final static int TOUCH_STATE_SCROLLING = 1;
    private static final int SNAP_VELOCITY = 1000;

    public int mTouchState = TOUCH_STATE_REST;
    private boolean mLocked ;
    private boolean mAllowLongPress;

    public SlideMenu(Context context) {
        this(context,null,0);
    }

    public SlideMenu(Context context, AttributeSet attrs) {
       this(context,attrs,0);
    }

    public SlideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(getContext());
        mCurrentScreen = SCREEN_MAIN;
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureViews(widthMeasureSpec,heightMeasureSpec);
    }

    //重新测量控件大小
    public void measureViews(int widthMeasureSpec, int heightMeasureSpec){
        View menuView = getChildAt(0);
        menuView.measure(menuView.getLayoutParams().width+menuView.getLeft()+menuView.getRight(),
        heightMeasureSpec);
        View mainView = getChildAt(1);
        mainView.measure(widthMeasureSpec,heightMeasureSpec);
    }

    //分配子控件的位置
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        if(childCount != 2){
            throw  new IllegalStateException("The childCount of SlidingMenu must be 2");
        }

        View menuView = getChildAt(0);
        final int width = menuView.getMeasuredWidth();
        menuView.layout(-width,0,0,menuView.getMeasuredHeight());

        View mainView = getChildAt(1);
        mainView.layout(0,0,mainView.getMeasuredWidth(),mainView.getMeasuredHeight());
    }
}
