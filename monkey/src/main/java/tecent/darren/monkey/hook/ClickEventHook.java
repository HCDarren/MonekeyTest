package tecent.darren.monkey.hook;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;

import tecent.darren.monkey.util.LogUtil;

public class ClickEventHook extends BaseEventHook {

    private static Field mClickListenerField = null;

    private final ClickEventProxy mNullOriginalClickListener = new ClickEventProxy(null);

    static {
        try {
            Class<?> listenerInfoClass = Class.forName("android.view.View$ListenerInfo");
            mClickListenerField = listenerInfoClass.getDeclaredField("mOnClickListener");
            mClickListenerField.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hookView(View view) {
        Object listenerInfo = getListenerInfo(view);
        if (listenerInfo != null) {
            // 获取点击事件
            View.OnClickListener originalClickListener = getOnClickListener(listenerInfo);
            if (originalClickListener == null) {
                view.setOnClickListener(mNullOriginalClickListener);
            } else {
                if (originalClickListener instanceof ClickEventProxy) {

                } else {
                    view.setOnClickListener(new ClickEventProxy(originalClickListener));
                }
            }
        } else {
            LogUtil.logE("ClickEventHook view listenerInfo is null.");
        }
    }

    private View.OnClickListener getOnClickListener(Object listenerInfo) {
        try {
            return (View.OnClickListener) mClickListenerField.get(listenerInfo);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    final class ClickEventProxy implements View.OnClickListener {
        public View.OnClickListener originalClickListener;

        public ClickEventProxy(View.OnClickListener originalClickListener) {
            this.originalClickListener = originalClickListener;
        }

        @Override
        public void onClick(View v) {
            LogUtil.logI(parsingViewInfo(v));
            if (originalClickListener != null) {
                originalClickListener.onClick(v);
            }
        }
    }
}
