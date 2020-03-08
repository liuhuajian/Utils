package com.lhj.mylibrary.http;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.lhj.mylibrary.R;
import com.lhj.mylibrary.Util.CheckReferenceUtils;
import com.lhj.mylibrary.Util.MyLogger;
import com.lhj.mylibrary.Util.ToastUtil;
import com.lhj.mylibrary.http.bean.JsonResult;
import com.lhj.mylibrary.http.exception.APIException;
import com.lhj.mylibrary.http.exception.ApiErrorCode;
import com.lhj.mylibrary.http.exception.HttpErrorCode;
import com.lhj.mylibrary.http.exception.NetWorkThrowable;

import org.apache.http.conn.ConnectTimeoutException;

import java.lang.ref.SoftReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * 项目：xxx_xxx
 * 作    者：julyzeng （曾招林)  364298140@qq.com
 * 版    本：1.0
 * 创建日期：2018/6/10 6:18
 * 描    述：观察者
 * 修订历史：叶贤明  解耦，同一处理请求错误情况。添加dialog// 暂时不添加2.0样式工厂
 */

public class BaseObserver<T> implements Observer<JsonResult<T>> {
    //处理多此loading 显示
    private static boolean isNeedShowLoading = true;
    private boolean isShowLoading = false;
    private SoftReference<Activity> mContext;
    private ICallBack<T> mCallBack;
    //定位当前显示dialog 的view 的id
    private static int id;
    //网络加载的dialog 点击是否可以取消
    private Boolean isCancle;
    //是否显示dialog
    private boolean isShowDialog;
    private AlertDialog dialog;
    //抽象view
    private SoftReference<BaseView> baseView;

    public BaseObserver(BaseView view, ICallBack<T> callBack) {
        this(callBack, view, true);
    }

    public BaseObserver(ICallBack<T> callBack, BaseView view, boolean isShowDialog) {
        this(callBack, view, isShowDialog, false);
    }

    public BaseObserver(ICallBack<T> callBack, BaseView view, boolean isShowDialog, boolean isCancle) {
        this.isShowDialog = isShowDialog;
        this.isCancle = isCancle;
        Context context = null;
        baseView = new SoftReference(view);
        if (view instanceof Activity) {
            context = (Context) view;
        } else if (view instanceof Fragment) {
            context = ((Fragment) view).getActivity();
        } else {
            throw new ClassCastException("this view must is BaseActivity child");
        }

        mContext = new SoftReference(context);
        mCallBack = CheckReferenceUtils.checkReferIsNull(callBack);
        if (mContext.get() != null) {
            View dialogView = View.inflate(mContext.get(), R.layout.http_dialog, null);
            ImageView ivAnimation = dialogView.findViewById(R.id.iv_anim);
//            ImageLoader.loadAnimationDrawable(mContext.get(),1,ivAnimation);
            dialog = new AlertDialog.Builder(mContext.get(), R.style.progressDialog).
                    setView(dialogView).setCancelable(this.isCancle).create();
        }
        if (isShowDialog && isNeedShowLoading) {
            isNeedShowLoading = false;
            isShowLoading = true;
        }
    }

    /***
     * 检查网络
     *
     * @param context
     * @return
     */
    public boolean checkNet(Context context) {
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
    public void onSubscribe(@NonNull Disposable d) {

        if (!d.isDisposed() && mContext.get() != null) {
            if (!checkNet(mContext.get())) {
                onError(new NetWorkThrowable(ApiErrorCode.ERROR_NO_INTERNET, "网络连接异常"));
                d.dispose();
                return;
            }
            if (!mContext.get().isFinishing())
                showDialog();
        }
    }

    void showDialog() {
        if (dialog != null && isShowDialog && !dialog.isShowing() && isShowLoading) {
            dialog.show();
        }
    }

    void hiddenDialog() {
        if (isShowDialog && dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void onNext(@NonNull JsonResult<T> t) {
        JsonResult<T> basicBean = (JsonResult) t;
        if (HttpErrorCode.SUCCESS.equals(basicBean.getCode())) {
            mCallBack.onNext(basicBean.getData());
            hiddenDialog();
        } else {
            if (!HttpErrorCode.TOKEN_NULL.equals(basicBean.getCode())) {
                onError(new APIException(basicBean.getCode(), basicBean.getMessage()));
                if (mCallBack != null) {
                    mCallBack.onError(basicBean.getCode());
                }
            }
        }

    }


    @Override
    public void onError(@NonNull Throwable e) {
        MyLogger.e("excep===" + e.getMessage());
        if (e instanceof NetWorkThrowable) {
//            ErrorStateHandler.ErrorHander(mContext.get(), baseView.get().getStatesLayoutManager(), (NetWorkThrowable) e);
        } else if (e instanceof ConnectTimeoutException) {
            ToastUtil.getInstance().showToast("连接超时");
        } else if (e instanceof ConnectException) {
            ToastUtil.getInstance().showToast("连接服务器请求异常");
        } else if (e instanceof SocketTimeoutException) {
            ToastUtil.getInstance().showToast("连接服务器超时，服务器小哥哥正在努力加油~");//服务器重启或者崩溃时
        } else if (e instanceof HttpException) {
            if(((HttpException) e).code() != 401) {
                ToastUtil.getInstance().showToast("服务器小哥哥正在努力加油~");//一般是服务出错或参数错误
            }
        } else {
            ToastUtil.getInstance().showToast(e.getMessage());
        }
        mCallBack.onError(e);
        hiddenDialog();
        isNeedShowLoading = true;

        isShowLoading = false;
    }

    @Override
    public void onComplete() {
        mCallBack.onCompleted();
        hiddenDialog();
        isNeedShowLoading = true;
        isShowLoading = false;
    }
}
