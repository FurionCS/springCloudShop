package com.spring.domain.type.handler;


import com.spring.common.model.type.handler.GenericTypeHandler;
import com.spring.domain.type.OrderStatus;

/**
 * @author Zhao Junjian
 */
public class OrderStatusHandler extends GenericTypeHandler<OrderStatus> {

    @Override
    public int getEnumIntegerValue(OrderStatus parameter) {
        return parameter.getCode();
    }
}
