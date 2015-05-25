package com.dystu.oschinademo01;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Administrator on 2015/5/25.
 */
public class MySwipeRefreshLayout extends SwipeRefreshLayout implements AbsListView.OnScrollListener {


    private int mTouchSlop;

    private ListView mListView;


    private OnLoadListener mOnLoadListener;


    private View mListViewFooter;

    private int mYDown;

    private int mLastY;

    private boolean isLoading = false;


    private AbsListView.OnScrollListener onScrollListener;


    public MySwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

        mListViewFooter = LayoutInflater.from(context).inflate(R.layout.list_cell_footer, null,
                false);
    }

    //set the footer of the ListView with a ProgressBar in it
    public void setFooterView(Context context, ListView mListView, int layoutId) {
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mListViewFooter = LayoutInflater.from(context).inflate(layoutId, null,
                false);
        mListView.addFooterView(mListViewFooter);
        mListView.setFooterDividersEnabled(false);
        this.mListView = mListView;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        // ��ʼ��ListView����
        if (mListView == null) {
            getListView();
        }
    }


    private void getListView() {
        int childs = getChildCount();
        if (childs > 0) {
            View childView = getChildAt(0);
            if (childView instanceof ListView) {
                mListView = (ListView) childView;
                mListView.setOnScrollListener(this);
            }
        }
    }

    public void setAdapter(ListAdapter adapter) {
        if (mListView.getFooterViewsCount() == 0) {
            mListView.addFooterView(mListViewFooter);
        }
        mListView.setAdapter(adapter);
        mListView.removeFooterView(mListViewFooter);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mYDown = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                mLastY = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_UP:
                if (canLoad()) {
                    loadData();
                }
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(event);
    }


    private boolean canLoad() {
        return isBottom() && !isLoading && isPullUp();
    }


    private boolean isBottom() {

        if (mListView != null && mListView.getAdapter() != null) {
            return mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount() - 1);
        }
        return false;
    }


    private boolean isPullUp() {
        return (mYDown - mLastY) >= mTouchSlop;
    }


    private void loadData() {
        if (mOnLoadListener != null) {

            setLoading(true);
            //
            mOnLoadListener.onLoad();
        }
    }

    /**
     * @param loading
     */
    public void setLoading(boolean loading) {
        isLoading = loading;
        if (isLoading && mListView.getFooterViewsCount() == 0) {
            mListView.addFooterView(mListViewFooter);
        } else {

            if (mListView.getAdapter() instanceof HeaderViewListAdapter) {
                mListView.removeFooterView(mListViewFooter);
            } else {
                mListViewFooter.setVisibility(View.GONE);
            }
            mYDown = 0;
            mLastY = 0;

        }
    }

    public void addScrollListener(AbsListView.OnScrollListener listener) {
        onScrollListener = listener;
    }

    /**
     * @param loadListener
     */
    public void setOnLoadListener(OnLoadListener loadListener) {
        mOnLoadListener = loadListener;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (onScrollListener != null) {
            onScrollListener.onScrollStateChanged(view, scrollState);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                         int totalItemCount) {

        if (onScrollListener != null) {
            onScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }


        if (canLoad()) {
            loadData();
        }
    }


    public static interface OnLoadListener {
        public void onLoad();
    }
}