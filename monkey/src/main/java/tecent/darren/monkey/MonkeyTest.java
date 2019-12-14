package tecent.darren.monkey;

import android.app.Application;
import android.content.Intent;

import tecent.darren.monkey.exception.MonkeyInitException;
import tecent.darren.monkey.hook.ClickEventHook;
import tecent.darren.monkey.hook.HookEventManager;
import tecent.darren.monkey.hook.LongClickEventHook;
import tecent.darren.monkey.reflection.Reflection;
import tecent.darren.monkey.service.MonkeyService;
import tecent.darren.monkey.util.LogUtil;
import tecent.darren.monkey.util.ProcessUtil;


/**
 * Copyright (C), 2019, Tencent
 * Author: darrenzeng
 * Date: 2019/12/12 3:17 PM
 * Description: monkey 测试工具入口类
 * Version: 1.0.0
 */
public class MonkeyTest {
    private static boolean mInit = false;
    private static final String MONKEY_SERVICE_ACTION = "MONKEY_SERVICE_ACTION";
    /**
     * monkey 默认执行的此时一千万次
     */
    private int mMonkeyTimes = 1000_0000;

    /**
     * monkey 停留的次数，如果当前页面停留一千次，重新回到主进程的主页面
     * 简单的预防当前页面可能是没有什么操作，又或者是最后一个页面
     */
    private int mPageMonkeyTimes = 1000;


    private Application mApplication;

    public static final MonkeyTest getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public int getMonkeyTimes() {
        return mMonkeyTimes;
    }

    static final class InstanceHolder {
        static final MonkeyTest INSTANCE = new MonkeyTest();
    }

    public MonkeyTest monkeyTimes(int monkeyTimes) {
        if (monkeyTimes <= 0) {
            throw new MonkeyInitException("Set monkeyTimes error，It has to be greater than 0.");
        }
        this.mMonkeyTimes = monkeyTimes;
        return this;
    }


    public MonkeyTest pageMonkeyTimes(int pageMonkeyTimes) {
        if (pageMonkeyTimes <= 0) {
            throw new MonkeyInitException("Set pageMonkeyTimes error，It has to be greater than 0.");
        }
        this.mPageMonkeyTimes = pageMonkeyTimes;
        return this;
    }

    /**
     * 初始化入口
     */
    public MonkeyTest init(Application application) {
        if (mInit == true) {
            throw new MonkeyInitException("Monkey sdk init error，The initialization method can only be called once.");
        }
        mInit = true;
        // 解决 android 9.0 反射问题
        Reflection.unseal(application);
        this.mApplication = application;
        // 先注册所有需要 hook 的 event 事件
        HookEventManager.getInstance().registerHookEvent(new ClickEventHook());
        HookEventManager.getInstance().registerHookEvent(new LongClickEventHook());
        // hook 所有 View 的操作事件
        HookEventManager.getInstance().hookViewEvent();
        return this;
    }

    /**
     * 启动 monkey 服务
     */
    public void startMonkey() {
        if (mInit == false) {
            throw new MonkeyInitException("Start Monkey error，Please call the initialization method first.");
        }
        // 判断是否是主进程
        if (ProcessUtil.isMainProcess(mApplication)) {
            // 启动 monkey 的服务
            mApplication.startService(new Intent(mApplication, MonkeyService.class));
        }
    }

    public MonkeyTest addLogInterceptor(LogUtil.LogInterceptor logInterceptor) {
        LogUtil.init(logInterceptor);
        return this;
    }
}
