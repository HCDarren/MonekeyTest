package tecent.darren.monkey.hook;

import android.view.View;

/**
 * Copyright (C), 2019, Tencent
 * Author: darrenzeng
 * Date: 2019/12/13 11:40 AM
 * Description: monkey 事件分发 hook
 * Version: 1.0.0
 */
interface IEventHook {
    void hookView(View view);
}
