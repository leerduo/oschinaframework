package com.dystu.oschinademo01;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/5/18.
 */
public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {

    private final Context mContext;

    protected PagerSlidingTabStrip mPagerStrip;
    private final ViewPager mViewPager;
    private final ArrayList<ViewPageInfo> mTabs = new ArrayList<>();


    public ViewPagerFragmentAdapter(FragmentManager fm, PagerSlidingTabStrip pagerStrip, ViewPager viewPager) {
        super(fm);
        mContext = viewPager.getContext();
        this.mViewPager = viewPager;
        this.mPagerStrip = pagerStrip;
        mViewPager.setAdapter(this);
        mPagerStrip.setViewPager(mViewPager);
    }

    public void addTab(String title, String tag, Class<?> clss) {
        ViewPageInfo pageInfo = new ViewPageInfo(title, tag, clss);
        addFragment(pageInfo);
    }

    public void addAll(ArrayList<ViewPageInfo> mTabs) {
        for (ViewPageInfo viewPageInfo : mTabs) {
            addFragment(viewPageInfo);
        }
    }

    private void addFragment(ViewPageInfo pageInfo) {
        if (pageInfo == null) {
            return;
        }
        //加入tab title
        View view = LayoutInflater.from(mContext).inflate(R.layout.base_viewpager_fragment_tab_item, null, false);

        TextView title = (TextView) view.findViewById(R.id.tab_title);

        title.setText(pageInfo.title);
        mPagerStrip.addTab(view);
        mTabs.add(pageInfo);
        notifyDataSetChanged();
    }

    /**
     * 移除第一次
     */
    public void remove() {
        remove(0);
    }

    /**
     * 移除一个tab
     *
     * @param index 如果index小于0，则从第一个开始删，如果大于tab的数量则从最后一个开始删除
     */
    private void remove(int index) {
        if (mTabs.isEmpty()) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        if (index >= mTabs.size()) {
            index = mTabs.size() - 1;
        }
        mTabs.remove(index);
        mPagerStrip.removeTab(index, 1);
        notifyDataSetChanged();
    }

    /**
     * 移除所有的tab
     */
    public void removeAll() {
        if (mTabs.isEmpty()) {
            return;
        }
        mPagerStrip.removeAllTab();
        mTabs.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    /**
     * when you call notifyDataSetChanged() ,the view pager will remove all views and reloaded them all.
     *
     * @param object
     * @return
     */
    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        ViewPageInfo pageInfo = mTabs.get(position);

        return Fragment.instantiate(mContext, pageInfo.clss.getName());
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs.get(position).title;
    }
}
