package com.dystu.oschinademo01;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2015/5/18.
 */
public abstract class BaseViewPagerFragment extends Fragment {

    protected PagerSlidingTabStrip mTabStrip;

    protected ViewPager mViewPager;

    protected ViewPagerFragmentAdapter mTabsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_viewpager_fragment, null);
    }

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.pager_tabstrip);
        mViewPager = (ViewPager) view.findViewById(R.id.pager);
        //getChildFragmentManager() Fragment嵌套Fragment时使用
        mTabsAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), mTabStrip, mViewPager);
        setScreenPageLimit();
        onSetupTabAdapter(mTabsAdapter);
    }

    protected abstract void onSetupTabAdapter(ViewPagerFragmentAdapter mTabsAdapter);

    protected void setScreenPageLimit() {

    }
}
