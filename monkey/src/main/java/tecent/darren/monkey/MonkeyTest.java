package tecent.darren.monkey;

import android.app.Application;
import android.content.Intent;

import tecent.darren.monkey.exception.MonkeyInitException;
import tecent.darren.monkey.reflection.Reflection;
import tecent.darren.monkey.service.MonkeyService;
import tecent.darren.monkey.util.ProcessUtil;


/**
 * Copyright (C), 2019, Tencent
 * Author: darrenzeng
 * Date: 2019/12/12 3:17 PM
 * Description: monkey 测试工具入口类
 * History:
 * Version: 1.0.0
 */
public class MonkeyTest {
    private static boolean mInit = false;
    private static final String MONKEY_SERVICE_ACTION = "MONKEY_SERVICE_ACTION";

    /**
     * 初始化入口
     */
    public static void init(Application application) {
        if (mInit == true) {
            throw new MonkeyInitException("Monkey sdk init error，The initialization method can only be called once.");
        }
        mInit = true;
        // 解决 android 9.0 反射问题
        Reflection.unseal(application);

        // 1. 判断是否是主进程
        if (ProcessUtil.isMainProcess(application)) {
            // 启动 monkey 的服务
            application.startService(new Intent(application,MonkeyService.class));
        }
        // 2. 注册监听到所有的 Activity 的初始化创建
    }
}
