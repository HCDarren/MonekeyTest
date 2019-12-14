package tencent.darren.monekey.test;

import android.app.Application;
import android.util.Log;

import tecent.darren.monkey.MonkeyTest;
import tecent.darren.monkey.util.LogUtil;

/**
 * Copyright (C), 2019, Tencent
 * Author: darrenzeng
 * Date: 2019/12/12 5:05 PM
 * Description: application
 * History:
 * <author>  <time>  <version>
 * darrenzeng 2019/12/12 1.0.0
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MonkeyTest.getInstance()
                .monkeyTimes(100)
                .pageMonkeyTimes(10)
                .init(this)
                .addLogInterceptor(new LogUtil.LogInterceptor() {
                    @Override
                    public boolean log(int priority, String message) {
                        // 需要把这些记录到文件，方便 Bug 复现与跟踪
                        Log.e("Monkey TAG", message);
                        return true;
                    }
                })
                .startMonkey();
    }
}
