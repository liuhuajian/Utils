package com.lhj.mylibrary.Util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;

/**
 * 项目：国民健康平台
 *
 * @Creator:liuhuajian
 * @创建日期： 2018/11/8 20:20
 * @版本0.2
 * @类说明：
 */
public class ResourceUtil {

    public static Uri resourceIdToUri(Context context, int resourceId) {
        Resources r =context.getResources();
        String uriString = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(resourceId) + "/"
                + r.getResourceTypeName(resourceId) + "/"
                + r.getResourceEntryName(resourceId);
        MyLogger.i("uriString-->"+uriString);
        Uri uri =  Uri.parse(uriString);
        return uri;
    }

    public static int  getResId(Context context, String name, String defType) {
        String packageName = context.getApplicationInfo().packageName;
        return context.getResources().getIdentifier(name, defType, packageName);
    }
}
