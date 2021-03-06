package com.wds.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.wds.api.MyApp;

public class NetCheckUtil {
    /**
     * 检查是否有网络
     *
     * @return
     */
    public static boolean checkNetWork() {
        ConnectivityManager manager = (ConnectivityManager) MyApp.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null;
    }

    /**
     * 当前是否是wifi链接
     *
     * @return
     */
    public static boolean isWifiConnected() {
        ConnectivityManager manager = (ConnectivityManager) MyApp.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return info != null;
    }

    /**
     * 检查手机（4,3,2）G是否链接
     */
    public static boolean isMobileNetworkConnected() {
        ConnectivityManager manager = (ConnectivityManager) MyApp.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return info != null;
    }

    public static long getSystemTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取屏幕的dpi
     *
     * @param at
     * @return
     */
    public static int getDpi(Activity at) {
        DisplayMetrics dm = new DisplayMetrics();
        at.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.densityDpi;
    }


    /**
     * 获取包名
     *
     * @param context
     * @return
     */
    public static String getPgName(Context context) {
        return context.getPackageName();
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.P)
    public static Long getVersionCode(Context context, String pg) {
        PackageInfo pgInfo = null;
        try {
            pgInfo = context.getPackageManager().getPackageInfo(pg, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return Long.valueOf(pgInfo.versionCode);
        } else {
            return pgInfo.getLongVersionCode();
        }
    }

    public static int getScreenWidth(Context context) {

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();

        return width;
    }
    public static int getScreenheight(Context context) {

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

    public static int dip2px(Context context, float dpValue) {

        float scale = context.getResources().getDisplayMetrics().density;

        return (int) (dpValue * scale + 0.5f);

    }

}
