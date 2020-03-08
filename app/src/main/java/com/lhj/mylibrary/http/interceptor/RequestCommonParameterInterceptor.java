package com.lhj.mylibrary.http.interceptor;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 项目：国民健康平台
 *
 * @Creator:曾招林julyzeng
 * @创建日期： 2018/8/1 19:44
 * @版本0.1
 * @类说明：请求的公共参数拦截器(token刷新一同处理） 加密签名也可以里面进行处理
 */

public class RequestCommonParameterInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Response response = null;

//        if (oldRequest.method().equals("POST")) {//post请求方法添加公共参数
//            if (checkNet(BaseApp.mContext)) {
//                if (oldRequest.body() instanceof RequestBody) {
//                    String loginurl = BaseUrl.BASE_URL + "manager/account/accountCustomerInfo/login";
//
//                    String wxloginurl = BaseUrl.BASE_URL + "manager/account/accountCustomerInfo/V1.2/isExistByUnionId";
//
//                    if (loginurl.equals(oldRequest.url().toString()) || wxloginurl.equals(oldRequest.url().toString())) {//登陆接口-拦截器处理请求头中的token
//                        Headers headers = chain.proceed(oldRequest).headers();
//                        String token = headers.get("token");
//                        Tools.saveToken(token);
//                    }
//
//                    Headers.Builder builder = oldRequest
//                            .headers()
//                            .newBuilder();
//                    //统一追加Header参数
//                    Headers newBuilder = builder
//                            .add("token", Tools.getTokenString())//应用Key(01APP，02PC，03其他)
//                            .build();
//
//                    response = chain.proceed(oldRequest.newBuilder().headers(newBuilder).build());
//                }
//            }
//        }
        return response;
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

    /**
     * 添加公共参数
     *
     * @param oldRequest
     * @return
     */
    private Request addCommonParam(Request oldRequest) {

        HttpUrl url = oldRequest.url()
                .newBuilder()
                // Provide your custom parameter here
//                .addQueryParameter("appKey","01")//应用Key(01APP，02PC，03其他)
//                .addQueryParameter("uuid", DeviceUtil.getDeviceId(BaseApp.mContext))
                .build();

        Request newRequest = oldRequest.newBuilder()
//                .header("token", Tools.getTokenString())//应用Key(01APP，02PC，03其他)
                .method(oldRequest.method(), oldRequest.body())
                .url(url)
                .build();
        return newRequest;
    }
}
