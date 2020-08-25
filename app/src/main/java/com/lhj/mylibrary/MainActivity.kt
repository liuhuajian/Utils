package com.lhj.mylibrary

import android.os.Bundle
import com.julyzeng.baserecycleradapterlib.BaseRecyclerAdapter
import com.julyzeng.baserecycleradapterlib.BaseViewHolder
import com.lhj.mylibrary.base.BaseActivity
import com.lhj.mylibrary.http.HttpCallBack
import com.lhj.mylibrary.http.data.MyApiManager
import io.reactivex.Observable
import io.reactivex.Observer
import org.jetbrains.annotations.NotNull

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       for (index in 1 downTo 100){

       }
        var array = arrayOf("","","")
        for (index in array.indices){
        }
        for (index in array){
            index
        }
    }
}

