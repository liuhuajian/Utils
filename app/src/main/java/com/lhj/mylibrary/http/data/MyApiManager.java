package com.lhj.mylibrary.http.data;

import com.google.gson.Gson;
import com.lhj.mylibrary.Constants;
import com.lhj.mylibrary.http.Api;
import com.lhj.mylibrary.http.ServiceFactory;
import com.lhj.mylibrary.http.bean.JsonResult;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民技能
 * @Description: 购物ApiManager
 * @Author: liulei
 * @CreateDate: 2019/10/21 15:24
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/10/21 15:24
 * @UpdateRemark: 更新说明
 * @Version: 0.1
 */
public class MyApiManager extends Api {

    private MyRequestService mApiService = ServiceFactory.createServiceFrom(MyRequestService.class);

    private RequestBody getRequestBody(HashMap<String, Object> map) {
        return RequestBody.create(MediaType.parse(Constants.MEDIA_TYPE_CHARSET), new Gson().toJson(map));
    }

    /**
     * 请求商品详情
     *
     * @param map map
     * @return Observable
     */
    public Observable<JsonResult<String>> requestGoodsDetailed(HashMap<String, Object> map) {
        return applySchedulers(mApiService.requestGoodsDetailed(getRequestBody(map)));
    }


}