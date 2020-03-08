package com.lhj.mylibrary.http.interceptor;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.lhj.mylibrary.App;
import com.lhj.mylibrary.Util.MyLogger;
import com.lhj.mylibrary.http.exception.HttpErrorCode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;


/**
 * 项目名称：
 * 类描述： - 自定义 restAdatper Client 拦截器修改参数类型并添加签名等共同参数重新请求
 * OKhttp3.x不支持Client形式处理请求。只支持拦截器
 * 创建人：julyzeng
 * 创建时间：2016/5/9 10:28
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @version 1.0
 */
public class RequestPamarmsInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Response request1 = null;
        try {
            request1 = chain.proceed(request);

            ResponseBody responseBody = request1.peekBody(1024 * 1024);
            String content = responseBody.string();

            if (!bodyToString(request).contains("/9j/") && !bodyToString(request).contains("JFIF")) {
                MyLogger.e("Url-->" + request1.request().url() + "\r\n \r\n" + request.headers() + "\r\n"
                        + "\r\n" + bodyToString(request) + "\r\n \r\n" + "body-->" + content);
            } else {
                MyLogger.e("Url-->" + request1.request().url() + "\r\n \r\n" + "body-->" + content);
            }
            // 判断stateCode值

            JSONObject jsonObject = new JSONObject(content);
            String stateCode = jsonObject.optString("code");

            if (HttpErrorCode.USER_NO.equals(stateCode) || HttpErrorCode.TOKEN_NULL.equals(stateCode) || HttpErrorCode.TOKEN_ERROR.equals(stateCode)) {

//                UserInfoHelper.userInfo = null;
//                Tools.saveToken(null);
//                Observable.timer(500, TimeUnit.MILLISECONDS)
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Consumer<Long>() {
//                            @Override
//                            public void accept(Long aLong) throws Exception {
////                                ARouter.getInstance().build(ARouteConstant.LOGIN).navigation();
//                            }
//                        }, new Consumer<Throwable>() {
//                            @Override
//                            public void accept(Throwable throwable) throws Exception {
//                                throwable.printStackTrace();
//                            }
//                        });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//            else if ("401".equals(stateCode)) {  // token失效，重新执行请求

//                //取出本地的refreshToken
//                String refreshToken = Tools.getTokenString();
//                // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求
//                RequestApiService service = ServiceFactory.createServiceFrom(RequestApiService.class);
//                Call<JsonResult<JSONObject>> call = service.refreshToken(refreshToken);
//                // 要用retrofit的同步方式
//                JsonResult<JSONObject> jsonResult = call.execute().body();//返回新的token
//                if (HttpErrorCode.SUCCESS.equals(jsonResult.getCode())) {
//                    JSONObject jsonObject1 = jsonResult.getData();
//                    String newToken = jsonObject1.getString("token");
//                    Tools.saveToken(newToken);//保存token
//                }
//                response = chain.proceed(addCommonParam(oldRequest));
//            } else {
//                response = response.newBuilder()
//                        .header("token",Tools.getTokenString())
//                        .body(ResponseBody.create(null, resp))
//                        .build();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        if (request1 != null)
            return request1;
        //在这里获取到request后就可以做任何事情了
        if (request != null) {
            Response response = chain.proceed(request);
            return response;
        } else {
            Toast.makeText(App.mContext, "请求超时", Toast.LENGTH_SHORT).show();
        }
        return null;
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


    private static String bodyToString(final Request request) {

        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            if (copy.body() == null) {
                return "get------------";
            }
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
