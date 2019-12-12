package tecent.darren.monkey.event;

import android.app.Instrumentation;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Random;

import tecent.darren.monkey.util.ApplicationUtil;
import tecent.darren.monkey.util.ThreadUtil;


/**
 * Copyright (C), 2019, Tencent
 * Author: darrenzeng
 * Date: 2019/12/12 3:50 PM
 * Description: 点击事件
 * History:
 * <author>  <time>  <version>
 * darrenzeng 2019/12/12 1.0.0
 */
public class ClickEvent extends AbstractEvent {
    private int mScreenWidth, mScreenHeight;
    private Instrumentation mInstrumentation;

    private Random mRandom = new Random();

    public ClickEvent() {
        mScreenWidth = ApplicationUtil.getScreenWidth();
        mScreenHeight = ApplicationUtil.getScreenHeight();
        mInstrumentation = ApplicationUtil.getApplicationInstrumentation();
    }

    @Override
    public void dispatchEvent() {
        // 随机发送点击事件


        int downX = mRandom.nextInt(mScreenWidth);
        int downY = mRandom.nextInt(mScreenHeight);

        long eventTime = System.currentTimeMillis();
        long sleepTime = 20;

        Log.e("TAG", "随机发送点击事件!" + downX + " , " + downY);

        try {
            mInstrumentation.sendPointerSync(MotionEvent.obtain(eventTime, eventTime, MotionEvent.ACTION_DOWN, downX, downY, 0));

            ThreadUtil.waitSleep(sleepTime);

            mInstrumentation.sendPointerSync(MotionEvent.obtain(eventTime, eventTime + sleepTime, MotionEvent.ACTION_UP, downX, downY, 0));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG", "sendPointerSync error");
        }
    }
}
