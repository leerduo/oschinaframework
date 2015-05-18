package com.dystu.oschinademo01;

import android.app.Dialog;
import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Administrator on 2015/5/18.
 */
public class QuickOptionalDialog extends Dialog {

    private ImageView mClose;


    public QuickOptionalDialog(Context context) {
        this(context, R.style.quick_option_dialog);
    }

    public QuickOptionalDialog(Context context, int theme) {
        super(context, theme);
    }

    protected QuickOptionalDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
