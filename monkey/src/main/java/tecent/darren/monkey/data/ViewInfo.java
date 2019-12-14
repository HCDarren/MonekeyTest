package tecent.darren.monkey.data;

/**
 * Copyright (C), 2019, Tencent
 * Author: darrenzeng
 * Date: 2019/12/13 3:39 PM
 * Description: view info
 * Version: 1.0.0
 */
public class ViewInfo {
    /**
     * 该控件 id 的名字
     */
    public String idName;

    /**
     * 该控件的名字
     */
    public String viewName;

    /**
     * 控件在屏幕 x 轴位置
     */
    public int x;

    /**
     * 控件在屏幕 y 轴位置
     */
    public int y;

    /**
     * 控件的宽度
     */
    public int width;

    /**
     * 控件的高度
     */
    public int height;

    @Override
    public String toString() {
        return "ViewInfo{" +
                "idName='" + idName + '\'' +
                ", viewName='" + viewName + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
