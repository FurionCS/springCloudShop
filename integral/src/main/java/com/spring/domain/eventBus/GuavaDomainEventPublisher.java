package com.spring.domain.eventBus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executors;

/**
 * 基于guava 的领域模型抽象类
 * Created by Mr.Cheng on 2017/9/29.
 */
public abstract class GuavaDomainEventPublisher implements  DomainEventPublisher {
    private EventBus syncBus=new EventBus(identify());
    private EventBus asyncBus=new AsyncEventBus(identify(), Executors.newFixedThreadPool(1));

    /**
     * 注册时直接注册到同步和异步的事件总线中
     * @param listener
     */
    @Override
    public void register(Object listener) {
        syncBus.register(listener);
        asyncBus.register(listener);
    }

    /**
     * 同步发布
     * @param event
     */
    @Override
    public void publish(DomainEvent event) {
        syncBus.post(event);
    }

    /**
     * 异步发布
     * @param event
     */
    @Override
    public void asyncPublish(DomainEvent event) {
        asyncBus.post(event);
    }
}
