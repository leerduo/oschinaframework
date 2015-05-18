package com.dystu.oschinademo01;

/**
 * Created by Administrator on 2015/5/15.
 */
public enum MainTab {

    NEWS(0, R.string.main_tab_name_news, R.drawable.tab_icon_new, NewsViewPagerFragment.class),
    TWEET(0, R.string.main_tab_name_tweet, R.drawable.tab_icon_tweet, TweetsViewPagerFragment.class),
    QUICK(0, R.string.main_tab_name_quick, R.drawable.tab_icon_new, null),
    EXPLORE(0, R.string.main_tab_name_explore, R.drawable.tab_icon_explore, ExploreFragment.class),
    ME(0, R.string.main_tab_name_my, R.drawable.tab_icon_me, MyInformationFragment.class);


    private int id;

    private int resName;

    private int resIcon;

    private Class<?> clz;

   private  MainTab(int id, int resName, int resIcon, Class<?> clz) {
        this.id = id;
        this.resName = resName;
        this.resIcon = resIcon;
        this.clz = clz;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
}
