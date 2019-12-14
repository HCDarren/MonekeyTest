package tecent.darren.monkey.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import tecent.darren.monkey.MonkeyTest;
import tecent.darren.monkey.event.ClickEvent;
import tecent.darren.monkey.event.EventFactory;
import tecent.darren.monkey.event.IEvent;
import tecent.darren.monkey.util.ThreadUtil;

/**
 * Copyright (C), 2019, Tencent
 * Author: darrenzeng
 * Date: 2019/12/12 3:41 PM
 * Description: monkey 执行的服务
 * History:
 * Version: 1.0.0
 */
public class MonkeyService extends Service {
    private EventFactory mEventFactory;

    // 直接通过 start 方法启动，这里直接返回空
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mEventFactory = EventFactory.createFactory();
        // 在子线程开始不断的发送，点击长按，触摸
        ThreadUtil.threadRun(new Runnable() {
            @Override
            public void run() {
                IEvent click = mEventFactory.createEvent(ClickEvent.class);
                click.dispatchEvent();
                mEventFactory.recycleEvent(click);
            }
        }, MonkeyTest.getInstance().getMonkeyTimes(), 50);
    }
}
