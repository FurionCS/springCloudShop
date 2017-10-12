package com.spring.domain.eventBus;

/**
 * 领域模型发布器，接口，规范领域模型实现类
 * Created by Mr.Cheng on 2017/9/29.
 */
public interface DomainEventPublisher<T extends DomainEvent> {
    String identify();

    void register(Object listener);

    void publish(T event);

    void asyncPublish(T event);
}
