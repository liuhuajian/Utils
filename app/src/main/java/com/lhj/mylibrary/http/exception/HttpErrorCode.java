package com.lhj.mylibrary.http.exception;

/**
 * 项目：国民健康平台
 *
 * @Creator:曾招林julyzeng
 * @创建日期： 2018/8/1 17:20
 * @版本0.1
 * @类说明：
 */

public class HttpErrorCode {

    /** 成功返回值 **/
    public static final String SUCCESS = "1000";
    /** 该手机号未注册 **/
    public static final String USER_PHONE_IS_UNLOGIN = "1003";
    /** 该第三方用户未注册 **/
    public static final String THIRD_USER_IS_UNLOGIN = "1004";
    /** 用户不存在 **/
    public static final String USER_NO = "1009";
    /** token null **/
    public static final String TOKEN_NULL = "2001";
    /** token错误 **/
    public static final String TOKEN_ERROR = "2002";
}
