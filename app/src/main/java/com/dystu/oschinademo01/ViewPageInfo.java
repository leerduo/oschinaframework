package com.dystu.oschinademo01;

import android.os.Bundle;

/**
 * Created by Administrator on 2015/5/18.
 */
public final class ViewPageInfo {

    public final String tag;

    public final Class<?> clss;


    public final String title;

    public ViewPageInfo(String title, String tag, Class<?> clss) {
        this.title = title;
        this.tag = tag;
        this.clss = clss;
    }
}
