package tecent.darren.monkey.util;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Copyright (C), 2019, Tencent
 * Author: darrenzeng
 * Date: 2019/12/12 3:21 PM
 * Description: 进程工具类
 * History:
 * Version: 1.0.0
 */
public class ProcessUtil {

    /**
     * 判断当前进程是否是主进程
     * @return true 是
     */
    public static boolean isMainProcess(Context context) {
        // 主进程的名字是 packageName ，其他进程的名字是 packageName:processName
        return getCurrentProcessName(context).equals(context.getPackageName());
    }

    /**
     * 获取当前进程的名字
     * @param context 上下文
     * @return 进程名
     */
    public static String getCurrentProcessName(Context context){
        int currentPid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfos =  activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcessInfos) {
            if(runningAppProcessInfo.pid == currentPid){
                return runningAppProcessInfo.processName;
            }
        }
        return "";
    }
}
