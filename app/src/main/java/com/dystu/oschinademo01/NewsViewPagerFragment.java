package com.dystu.oschinademo01;

/**
 * Created by Administrator on 2015/5/15.
 */
public class NewsViewPagerFragment extends BaseViewPagerFragment {

    @Override
    protected void onSetupTabAdapter(ViewPagerFragmentAdapter adapter) {
        String[] title = getResources().getStringArray(R.array.news_viewpage_arrays);
        adapter.addTab(title[0],"news",NewsFragment.class);
        adapter.addTab(title[1],"hot",HotFragment.class);
        adapter.addTab(title[2],"blog",BlogFragment.class);
        adapter.addTab(title[3],"recommend",RecommendFragment.class);
    }

    @Override
    protected void setScreenPageLimit() {
        mViewPager.setOffscreenPageLimit(3);
    }


}
