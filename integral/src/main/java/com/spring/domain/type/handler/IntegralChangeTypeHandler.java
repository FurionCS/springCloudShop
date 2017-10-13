package com.spring.domain.type.handler;


import com.spring.common.model.type.handler.GenericTypeHandler;
import com.spring.domain.type.IntegralChangeStatus;
import com.spring.domain.type.IntegralChangeType;

/**
 * @author Zhao Junjian
 */
public class IntegralChangeTypeHandler extends GenericTypeHandler<IntegralChangeType> {
    @Override
    public int getEnumIntegerValue(IntegralChangeType parameter) {
        return parameter.getStatus();
    }
}
