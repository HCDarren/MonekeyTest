package tecent.darren.monkey.hook;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.View;

import tecent.darren.monkey.DefaultActivityLifecycleCallbacks;
import tecent.darren.monkey.util.LogUtil;

/**
 * Copyright (C), 2019, Tencent
 * Author: darrenzeng
 * Date: 2019/12/13 11:44 AM
 * Description: 长按事件 hook
 * Version: 1.0.0
 */
public class LongClickEventHook extends BaseEventHook {
    @Override
    public void hookView(View view) {
        LogUtil.logE("LongClickEventHook View -> "+view.toString());
    }
}
