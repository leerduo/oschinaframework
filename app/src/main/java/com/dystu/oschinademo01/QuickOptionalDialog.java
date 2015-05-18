package com.dystu.oschinademo01;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * Created by Administrator on 2015/5/18.
 */
public class QuickOptionalDialog extends Dialog {

    private ImageView mClose;


    public QuickOptionalDialog(Context context) {
        this(context, R.style.quick_option_dialog);
    }

    private QuickOptionalDialog(Context context, int theme) {
        super(context, theme);
        View contentView = getLayoutInflater().inflate(R.layout.dialog_quick_option, null);
        mClose = (ImageView) contentView.findViewById(R.id.iv_close);
        Animation operatingAnim = AnimationUtils.loadAnimation(getContext(), R.anim.quick_option_close);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);

        mClose.startAnimation(operatingAnim);
        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuickOptionalDialog.this.dismiss();
            }
        });
        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                QuickOptionalDialog.this.dismiss();
                return true;
            }
        });
        super.setContentView(contentView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager windowManager = getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();

        layoutParams.width = display.getWidth();

        getWindow().setAttributes(layoutParams);
    }
}
