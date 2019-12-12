package tencent.darren.monekey.test;

import android.app.Application;

import tecent.darren.monkey.MonkeyTest;

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

        MonkeyTest.init(this);
    }
}
