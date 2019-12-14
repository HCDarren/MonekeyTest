package tecent.darren.monkey.hook;

import android.app.Activity;
import android.app.Application;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tecent.darren.monkey.DefaultActivityLifecycleCallbacks;
import tecent.darren.monkey.MonkeyTest;
import tecent.darren.monkey.event.IEvent;
import tecent.darren.monkey.util.ApplicationUtil;
import tecent.darren.monkey.util.LogUtil;

/**
 * Copyright (C), 2019, Tencent
 * Author: darrenzeng
 * Date: 2019/12/13 2:16 PM
 * Description: hook 事件管理类
 * Version: 1.0.0
 */
public class HookEventManager {

    private static final List<IEventHook> mEventHooks = new ArrayList<>();

    private final DefaultActivityLifecycleCallbacks mViewHookLifecycleCallbacks = new DefaultActivityLifecycleCallbacks() {
        @Override
        public void onActivityResumed(final Activity activity) {
            // 当前线程空闲的时候进行 hook 操作
            Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
                @Override
                public boolean queueIdle() {
                    recursiveHookAllViews(activity.getWindow().getDecorView());
                    return false;
                }
            });
        }
    };

    public static final HookEventManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void hookViewEvent() {
        Application application = ApplicationUtil.getGlobalApplication();
        if (application != null) {
            // 防止反复调用的时候出现多个回掉对象
            application.unregisterActivityLifecycleCallbacks(mViewHookLifecycleCallbacks);
            application.registerActivityLifecycleCallbacks(mViewHookLifecycleCallbacks);
        } else {
            LogUtil.logE("hookViewEvent get global application is null.");
        }
    }

    /**
     * 递归 hook  所有 View 的事件
     *
     * @param view 当前需要 hook 的 view
     */
    private void recursiveHookAllViews(View view) {
        hookEventView(view);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = viewGroup.getChildAt(i);
                recursiveHookAllViews(childView);
            }
        }
    }

    /**
     * hook 当前 view 的 event 事件
     *
     * @param view
     */
    private void hookEventView(View view) {
        for (IEventHook event : mEventHooks) {
            event.hookView(view);
        }
    }

    static final class InstanceHolder {
        static final HookEventManager INSTANCE = new HookEventManager();
    }

    public void registerHookEvent(IEventHook event) {
        mEventHooks.add(event);
    }
}
