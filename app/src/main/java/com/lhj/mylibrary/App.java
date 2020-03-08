package com.lhj.mylibrary;

import android.app.Application;

import com.julyzeng.baserecycleradapterlib.BaseRecyclerAdapter;
import com.julyzeng.baserecycleradapterlib.BaseViewHolder;

/**
 * 项目：国民健康平台
 *
 * @Creator:liuhuajian
 * @创建日期： 2019/10/26 17:50
 * @版本0.2
 * @类说明：
 */
public class App extends Application {
    public static App mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
