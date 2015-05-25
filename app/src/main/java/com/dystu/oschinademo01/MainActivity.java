package com.dystu.oschinademo01;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements TabHost.OnTabChangeListener{

    private MyFragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabHost = (MyFragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);
        if (Build.VERSION.SDK_INT > 10){
            mTabHost.getTabWidget().setShowDividers(0);
            initTabs();
            mTabHost.setCurrentTab(0);
            mTabHost.setOnTabChangedListener(this);
        }

        ImageView showQuickOption = (ImageView) findViewById(R.id.quick_option_iv);
        showQuickOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQuickOption();
            }
        });
    }

    private void showQuickOption() {
        final QuickOptionalDialog dialog = new QuickOptionalDialog(MainActivity.this);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void initTabs() {
        MainTab[] tabs = MainTab.values();
        int size = tabs.length;
        for (int i = 0; i < size; i++) {
            MainTab mainTab = tabs[i];
            TabHost.TabSpec tab = mTabHost.newTabSpec(getString(mainTab.getResName()));
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab_indicator, null);
            TextView title = (TextView) view.findViewById(R.id.tab_title);
            Drawable drawable = this.getResources().getDrawable(mainTab.getResIcon());
            title.setCompoundDrawablesWithIntrinsicBounds(null,drawable,null,null);
            if (i == 2){
                view.setVisibility(View.INVISIBLE);
                mTabHost.setNoTabChangedTag(getString(mainTab.getResName()));
            }
            title.setText(getString(mainTab.getResName()));
            tab.setIndicator(view);
            tab.setContent(new TabHost.TabContentFactory() {
                @Override
                public View createTabContent(String tag) {
                    return new View(MainActivity.this);
                }
            });

            mTabHost.addTab(tab,mainTab.getClz(),null);
        }
    }


    @Override
    public void onTabChanged(String tabId) {
        int size = mTabHost.getTabWidget().getTabCount();
        for (int i = 0; i < size; i++) {
            View view = mTabHost.getTabWidget().getChildAt(i);
            if (i == mTabHost.getCurrentTab()){
                view.setSelected(true);
            }else{
                view.setSelected(false);
            }
        }
    }
}
