package com.lhj.mylibrary.http;


import android.arch.lifecycle.LifecycleObserver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.lhj.mylibrary.Constants;
import com.trello.rxlifecycle3.LifecycleProvider;
import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.android.ActivityEvent;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 项目：xxx_xxx
 * 作    者：julyzeng （曾招林)  364298140@qq.com
 * 版    本：1.0
 * 创建日期：2017/4/4 11:57
 * 描    述：
 * 修订历史：
 */

public class Api implements LifecycleProvider<ActivityEvent>, LifecycleObserver {

    private LifecycleProvider<ActivityEvent> provider;

    public Api() {
        setProvider(this);
    }

    public void setProvider(LifecycleProvider<ActivityEvent> provider) {
        this.provider = provider;
    }

    public LifecycleProvider<ActivityEvent> getProvider() {
        return provider;
    }

    protected <T> Observable<T> applySchedulers(Observable<T> responseObservable) {
        Observable<T> tObservable = responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
//        tObservable
//                .compose(this.bindUntilEvent(ActivityEvent.DESTROY)).subscribe();//取消订阅
        return tObservable;

    }

    protected RequestBody createRequestBody(Object params) {
        return RequestBody.create(MediaType.parse(Constants.MEDIA_TYPE_CHARSET), new Gson().toJson(params));
    }

    /***
     * 检查网络
     *
     * @param context
     * @return
     */
    private boolean checkNet(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                // 获取网络连接管理的对像
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info == null || !info.isAvailable()) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Observable<ActivityEvent> lifecycle() {
        return null;
    }

    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(ActivityEvent activityEvent) {
        return null;
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return null;
    }
}
