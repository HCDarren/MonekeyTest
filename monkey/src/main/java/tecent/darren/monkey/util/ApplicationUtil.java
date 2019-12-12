package tecent.darren.monkey.util;

import android.app.Application;
import android.app.Instrumentation;

import java.lang.reflect.Method;

/**
 * Copyright (C), 2019, Tencent
 * Author: darrenzeng
 * Date: 2019/12/12 4:22 PM
 * Description: 应用 Application 相关的工具类
 * History:
 * Version: 1.0.0
 */
public class ApplicationUtil {

    public static int getScreenWidth() {
        return getGlobalApplication().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return getGlobalApplication().getResources().getDisplayMetrics().heightPixels;
    }

    public static Instrumentation getApplicationInstrumentation() {
        try {
            // 先通过反射获取 ActivityThread
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
            currentActivityThreadMethod.setAccessible(true);
            Object activityThread = currentActivityThreadMethod.invoke(null);
            // 再通过反射获取 Instrumentation
            Method getInstrumentationMethod = activityThreadClass.getDeclaredMethod("getInstrumentation");
            getInstrumentationMethod.setAccessible(true);
            return (Instrumentation) getInstrumentationMethod.invoke(activityThread);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Application getGlobalApplication() {
        try {
            // 先通过反射获取 ActivityThread
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
            currentActivityThreadMethod.setAccessible(true);
            Object activityThread = currentActivityThreadMethod.invoke(null);
            // 再通过反射获取 Application
            Method getApplicationMethod = activityThreadClass.getDeclaredMethod("getApplication");
            getApplicationMethod.setAccessible(true);
            return (Application) getApplicationMethod.invoke(activityThread);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
