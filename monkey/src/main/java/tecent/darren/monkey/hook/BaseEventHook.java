package tecent.darren.monkey.hook;

import android.app.Application;
import android.content.res.Resources;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import tecent.darren.monkey.data.ViewInfo;
import tecent.darren.monkey.util.ApplicationUtil;

/**
 * Copyright (C), 2019, Tencent
 * Author: darrenzeng
 * Date: 2019/12/13 11:40 AM
 * Description: monkey 事件分发 hook 基础类
 * Version: 1.0.0
 */
abstract class BaseEventHook implements IEventHook {
    protected static Method mGetListenerInfoMethod = null;

    private int[] mOutLocation = new int[2];

    static {
        try {
            mGetListenerInfoMethod = View.class.getDeclaredMethod("getListenerInfo");
            mGetListenerInfoMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    protected Object getListenerInfo(View view) {
        try {
            return mGetListenerInfoMethod.invoke(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String parsingViewInfo(View v) {
        ViewInfo viewInfo = new ViewInfo();
        viewInfo.viewName = v.getClass().getName();

        try {
            Resources resources = v.getResources();
            String idName = resources.getResourceName(v.getId());
            viewInfo.idName = idName;
        } catch (Exception e) {
            viewInfo.idName = "null id";
        }

        v.getLocationOnScreen(mOutLocation);
        viewInfo.x = mOutLocation[0];
        viewInfo.x = mOutLocation[1];

        viewInfo.width = v.getMeasuredWidth();
        viewInfo.height = v.getMeasuredHeight();

        return viewInfo.toString();
    }
}
