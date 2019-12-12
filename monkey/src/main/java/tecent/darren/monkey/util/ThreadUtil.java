package tecent.darren.monkey.util;

import android.util.Log;

/**
 * Copyright (C), 2019, Tencent
 * Author: darrenzeng
 * Date: 2019/12/12 4:22 PM
 * Description: 线程工具类
 * History:
 * Version: 1.0.0
 */
public class ThreadUtil {
    public static final int INFINITE = -1;

    /**
     * 子线程运行
     *
     * @param runnable
     * @param repeatCount 执行次数
     * @param waitTime    等待时间
     */
    public static void threadRun(final Runnable runnable, final int repeatCount, final int waitTime) {
        new Thread() {
            @Override
            public void run() {
                int executeCount = repeatCount;
                // 如果 -1 一直执行
                if (executeCount == INFINITE) {
                    executeCount = Integer.MAX_VALUE;
                }

                while (executeCount-- > 0) {
                    runnable.run();
                    try {
                        Thread.sleep(waitTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.e("TAG", "当前剩余执行次数：" + executeCount);
                }
            }
        }.start();
    }

    public static void waitSleep(long waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
