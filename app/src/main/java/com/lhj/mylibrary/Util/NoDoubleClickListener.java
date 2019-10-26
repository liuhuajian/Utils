package com.lhj.mylibrary.Util;

import android.view.View;

import java.util.Calendar;

/**
 * Created by messi on 2015/12/20.
 */
public abstract class NoDoubleClickListener implements View.OnClickListener {
    public static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;
    private int lastViewId = 0;
    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME || v.getId() != lastViewId){
            lastClickTime = currentTime;
            lastViewId = v.getId();
            onNoDoubleClick(v);
        }
    }

    protected abstract void onNoDoubleClick(View v);
}
