package tecent.darren.monkey.util;

import android.util.Log;

/**
 * Copyright (C), 2019, Tencent
 * Author: darrenzeng
 * Date: 2019/12/13 2:21 PM
 * Description: 日志打印
 * Version: 1.0.0
 */
public class LogUtil {
    private static final String TAG = "MONKEY_TEST_TAG";

    private static LogInterceptor mLogInterceptor;

    public static void init(LogInterceptor logInterceptor) {
        mLogInterceptor = logInterceptor;
    }

    public interface LogInterceptor {
        boolean log(int priority, String message);
    }

    public static void logE(String message) {
        if (mLogInterceptor != null) {
            boolean isIntercept = mLogInterceptor.log(Log.ERROR, message);
            if (isIntercept) {
                return;
            }
        }
        Log.e(TAG, message);
    }

    public static void logI(String message) {
        if (mLogInterceptor != null) {
            boolean isIntercept = mLogInterceptor.log(Log.INFO, message);
            if (isIntercept) {
                return;
            }
        }
        Log.i(TAG, message);
    }
}
