package com.spring.domain.eventBus;

import java.util.Date;

/**
 * Created by Mr.Cheng on 2017/9/29.
 */
public abstract class DomainEvent {
    //发生的时间
    private Date occurredTime;
    //身份标识，相当于对这个领域的名字
    protected  abstract String identify();
    public DomainEvent(){occurredTime=new Date();}
    public  Date getOccurredTime(){return occurredTime;}
}
