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
        val adapter = object : BaseRecyclerAdapter<Any?>(this, null, R.layout.activity_main) {
            override fun convert(
                baseViewHolder: BaseViewHolder,
                o: Any?
            ) {
            }
        }

        var map = HashMap<String,Any>()
        MyApiManager().requestGoodsDetailed(map)
            .subscribe({

            },{

            })
    }
}

