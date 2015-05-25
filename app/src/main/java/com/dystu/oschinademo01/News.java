package com.dystu.oschinademo01;

import android.widget.ImageView;

/**
 * Created by Administrator on 2015/5/20.
 */
public class News {

    private int iv_tip;

    private String tv_title;

    private String tv_description;

    private String tv_source;

    private String tv_time;

    private String tv_comment_count;

    public News(int iv_tip, String tv_title, String tv_description, String tv_source, String tv_time, String tv_comment_count) {
        this.iv_tip = iv_tip;
        this.tv_title = tv_title;
        this.tv_description = tv_description;
        this.tv_source = tv_source;
        this.tv_time = tv_time;
        this.tv_comment_count = tv_comment_count;
    }

    public int getIv_tip() {
        return iv_tip;
    }

    public String getTv_comment_count() {
        return tv_comment_count;
    }

    public String getTv_description() {
        return tv_description;
    }

    public String getTv_source() {
        return tv_source;
    }

    public String getTv_time() {
        return tv_time;
    }

    public String getTv_title() {
        return tv_title;
    }
}
