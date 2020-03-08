package com.lhj.mylibrary.http;

/**
 * 项目：国民健康平台
 *
 * @Creator:liuhuajian
 * @创建日期： 2019/4/16 11:02
 * @版本0.2
 * @类说明：
 */
public class BaseUrl {
    //请求最基础Urlhttp://api-xktest.nhf.cn/wjj-web-manager/
//    public static final String[] BASE_URLS = {"https://api.dev.nhf.cn/wjj-web-manager/", "https://api.test.nhf.cn/wjj-web-manager/", "https://api.uat.nhf.cn/wjj-web-manager/", "https://api-prod.nahefa.com.cn/wjj-web-manager/"};
    public static final String[] BASE_URLS = {"https://api.dev.nhf.cn/wjj-web-manager/", "http://api.test.nhf.cn/wjj-web-manager/", "https://api.uat.nhf.cn/wjj-web-manager/", "https://api-prod.nahefa.com.cn/wjj-web-manager/"};
    public static String BASE_URL = BASE_URLS[1];//dev开发环境  http://119.3.56.222:10006/application/
    //    public static String BASE_URL = "http://119.3.56.222:10106/";//dev开发环境
//    public static String BASE_URL = "http://10.66.203.162:10106/";//dev开发环境
    public static String BASE_URL_H5 = "";
    public static final String[] BASE_URL_H5S = {"https://h5.dev.nhf.cn/", "https://h5.test.nhf.cn/", "https://h5.uat.nhf.cn/", "https://h5-prod.nahefa.com.cn/"};
    /**
     * 售后政策链接
     */
    public static String AFTER_SALE_H5 = "";
    public static final String[] AFTER_SALE_H5S = {"https://public.dev.nhf.cn/afterSale.html", "https://public.test.nhf.cn/afterSale.html", "https://public.uat.nhf.cn/afterSale.html", "https://public-prod.nahefa.com.cn/afterSale.html"};

    /**
     * 部分H5新的链接
     */
    public static String BASE_PUBLIC_URL_H5 = "";
    public static final String[] BASE_PUBLIC_URL_H5S = {"https://public.dev.nhf.cn/", "https://public.test.nhf.cn/", "https://public.uat.nhf.cn/", "https://public-prod.nahefa.com.cn/"};

}
