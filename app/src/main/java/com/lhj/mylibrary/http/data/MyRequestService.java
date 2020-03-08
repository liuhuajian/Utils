package com.lhj.mylibrary.http.data;

import com.lhj.mylibrary.http.bean.JsonResult;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Copyright (C), 2019-2019, 国民健康科技有限公司
 *
 * @ProjectName: 国民技能
 * @Description: 购物网络请求
 * @Author: liulei
 * @CreateDate: 2019/10/21 15:39
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/10/21 15:39
 * @UpdateRemark: 更新说明
 * @Version: 0.1
 */
public interface MyRequestService {

    /**
     * 商品详情接口
     *
     * @param requestBody requestBody
     * @return Observable
     */
    @POST("goodsMain/xk/goodsBaseById")
    Observable<JsonResult<String>> requestGoodsDetailed(@Body RequestBody requestBody);

}