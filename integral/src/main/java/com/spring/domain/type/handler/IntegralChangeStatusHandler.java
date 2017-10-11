package com.spring.domain.type.handler;


import com.spring.common.model.type.handler.GenericTypeHandler;
import com.spring.domain.type.IntegralChangeStatus;

/**
 * @author Zhao Junjian
 */
public class IntegralChangeStatusHandler extends GenericTypeHandler<IntegralChangeStatus> {
    @Override
    public int getEnumIntegerValue(IntegralChangeStatus parameter) {
        return parameter.getStatus();
    }
}
