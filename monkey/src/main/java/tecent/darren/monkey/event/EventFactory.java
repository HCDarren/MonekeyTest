package tecent.darren.monkey.event;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2019, Tencent
 * Author: darrenzeng
 * Date: 2019/12/12 3:52 PM
 * Description: 事件工厂
 * History:
 * Version: 1.0.0
 */
public class EventFactory {
    private Map<Class, List<IEvent>> mRecycleEvents = new HashMap<>();

    /**
     * 创建 EventFactory 线程不安全的
     */
    public static EventFactory createFactory() {
        return new EventFactory();
    }

    /**
     * 创建 Event
     * @param eventClass
     * @return
     */
    public IEvent createEvent(Class<? extends IEvent> eventClass) {
        List<IEvent> recycleEventLists = mRecycleEvents.get(eventClass);
        if (recycleEventLists == null) {
            recycleEventLists = new LinkedList<>();
            mRecycleEvents.put(eventClass, recycleEventLists);
        }

        if (!recycleEventLists.isEmpty()) {
            return recycleEventLists.remove(0);
        }

        try {
            return eventClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 回收 Event
     * @param event
     */
    public void recycleEvent(IEvent event) {
        Class<?> eventClass = event.getClass();
        List<IEvent> recycleEventLists = mRecycleEvents.get(event.getClass());
        if (recycleEventLists == null) {
            recycleEventLists = new LinkedList<>();
            mRecycleEvents.put(eventClass, recycleEventLists);
        }
        recycleEventLists.add(event);
    }
}
