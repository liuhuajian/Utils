package com.lhj.mylibrary.http;


import com.lhj.mylibrary.App;
import com.lhj.mylibrary.http.interceptor.RequestCommonParameterInterceptor;
import com.lhj.mylibrary.http.interceptor.RequestPamarmsInterceptor;

import java.io.File;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 工厂模式
 * <p>
 * 利用第三方retrofit加载网络请求 返回实体类
 */
public class ServiceFactory {

    public static <T> T createServiceFrom(final Class<T> serviceClass) {
        OkHttpClient okHttpClient = null;
        try {
            //设置 请求的缓存
            File cacheFile = new File(App.mContext.getExternalCacheDir(), "lhjMessi");
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
            String[] openPath = null;
            InputStream[] inputStreams = null;
            try {
                //Https证书
                openPath = App.mContext.getAssets().list("certs");
                inputStreams = new InputStream[openPath.length];
                if (openPath != null) {
                    int i = 0;
                    for (String cert : openPath) {
                        InputStream is = App.mContext.getAssets().open("certs/" + cert);
                        inputStreams[i] = is;
                        i++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            HttpsUtils.SSLParams sslSocketFactory = HttpsUtils.getSslSocketFactory(inputStreams, null, null);
            okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .cache(cache)
                    .addInterceptor(new RequestCommonParameterInterceptor())//添加公共参数拦截器
                    .addInterceptor(new RequestPamarmsInterceptor())
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .sslSocketFactory(sslSocketFactory.sSLSocketFactory, sslSocketFactory.trustManager)
//                .authenticator(new TokenAuthenticator())
//                .addInterceptor(new TokenInterceptor())
//                .addInterceptor(new CacheInterceptor())
//                .addNetworkInterceptor(new CacheInterceptor())
//                .addInterceptor(interceptor)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(BaseUrl.BASE_URL)
//                .baseUrl("http://192.168.1.18:10007/test/web/")
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 添加Rx适配器
                .client(okHttpClient)//自定义客户端 处理参数加密或者修改请求
                .build();
        return adapter.create(serviceClass);
    }

    public static <T> T createServiceFrom2(final Class<T> serviceClass) {
        OkHttpClient okHttpClient = null;
        try {
            //设置 请求的缓存
            File cacheFile = new File(App.mContext.getExternalCacheDir(), "guoming");
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
            String[] openPath = null;
            InputStream[] inputStreams = null;
            try {
                //Https证书
                openPath = App.mContext.getAssets().list("certs");
                inputStreams = new InputStream[openPath.length];
                if (openPath != null) {
                    int i = 0;
                    for (String cert : openPath) {
                        InputStream is = App.mContext.getAssets().open("certs/" + cert);
                        inputStreams[i] = is;
                        i++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            HttpsUtils.SSLParams sslSocketFactory = HttpsUtils.getSslSocketFactory(inputStreams, null, null);
            okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .cache(cache)
                    .addInterceptor(new RequestCommonParameterInterceptor())//添加公共参数拦截器
                    .addInterceptor(new RequestPamarmsInterceptor())
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .sslSocketFactory(sslSocketFactory.sSLSocketFactory, sslSocketFactory.trustManager)
//                .authenticator(new TokenAuthenticator())
//                .addInterceptor(new TokenInterceptor())
//                .addInterceptor(new CacheInterceptor())
//                .addNetworkInterceptor(new CacheInterceptor())
//                .addInterceptor(interceptor)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("https://api.test.nhf.cn/wjj-web-manager/")
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 添加Rx适配器
                .client(okHttpClient)//自定义客户端 处理参数加密或者修改请求
                .build();
        return adapter.create(serviceClass);
    }


    public static <T> T createServiceTest(final Class<T> serviceClass) {
        OkHttpClient okHttpClient = null;
        try {
            //设置 请求的缓存
            File cacheFile = new File(App.mContext.getExternalCacheDir(), "guoming");
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
            String[] openPath = null;
            InputStream[] inputStreams = null;
            try {
                //Https证书
                openPath = App.mContext.getAssets().list("certs");
                inputStreams = new InputStream[openPath.length];
                if (openPath != null) {
                    int i = 0;
                    for (String cert : openPath) {
                        InputStream is = App.mContext.getAssets().open("certs/" + cert);
                        inputStreams[i] = is;
                        i++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            HttpsUtils.SSLParams sslSocketFactory = HttpsUtils.getSslSocketFactory(inputStreams, null, null);
            okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .cache(cache)
                    .addInterceptor(new RequestCommonParameterInterceptor())//添加公共参数拦截器
                    .addInterceptor(new RequestPamarmsInterceptor())
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .sslSocketFactory(sslSocketFactory.sSLSocketFactory, sslSocketFactory.trustManager)
//                .authenticator(new TokenAuthenticator())
//                .addInterceptor(new TokenInterceptor())
//                .addInterceptor(new CacheInterceptor())
//                .addNetworkInterceptor(new CacheInterceptor())
//                .addInterceptor(interceptor)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("https://api-prod.nahefa.com.cn/wjj-web-manager/")
//                .baseUrl("http://192.168.1.18:10007/test/web/")
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 添加Rx适配器
                .client(okHttpClient)//自定义客户端 处理参数加密或者修改请求
                .build();
        return adapter.create(serviceClass);
    }


}
