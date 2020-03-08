package com.lhj.mylibrary.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lhj.mylibrary.http.BaseObserver
import com.lhj.mylibrary.http.BaseView
import com.lhj.mylibrary.http.HttpCallBack
import com.lhj.mylibrary.http.bean.JsonResult
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseActivity :AppCompatActivity(),BaseView {
    /**
     * 使用CompositeDisposable来持有所有的compositeDisposable
     */
    protected var compositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        compositeDisposable = CompositeDisposable()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }

    /**
     * 创建观察者  这里对观察着 过滤一次，过滤出我们想要的信息，错误的信息toast
     *
     * @param onNext
     * @param <T>
     * @return
    </T> */
    fun <T> newObserver(onNext: HttpCallBack<T>?): Observer<*>? {
        return object : BaseObserver<T>(this, onNext) {
            override fun onComplete() {
                super.onComplete()
            }

            override fun onError(e: Throwable) {
                super.onError(e)
            }

            override fun onNext(t: JsonResult<T>) {
                super.onNext(t)
            }

            override fun onSubscribe(d: Disposable) {
                super.onSubscribe(d)
                compositeDisposable?.add(d)
            }
        }
    }

    /**
     * 创建观察者  这里对观察着 过滤一次，过滤出我们想要的信息，错误的信息toast
     *
     * @param onNext
     * @param <T>
     * @return
    </T> */
    protected open fun <T> newObserver(onNext: HttpCallBack<T>?, isShow:Boolean): Observer<*>? {
        return object : BaseObserver<T>(this, onNext) {
            override fun onComplete() {
                super.onComplete()
            }

            override fun onError(e: Throwable) {
                super.onError(e)
            }

            override fun onNext(t: JsonResult<T>) {
                super.onNext(t)
            }

            override fun onSubscribe(d: Disposable) {
                super.onSubscribe(d)
                compositeDisposable?.add(d)
            }
        }
    }
}