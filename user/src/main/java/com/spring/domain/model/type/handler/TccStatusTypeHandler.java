package com.spring.domain.model.type.handler;


import com.spring.common.model.type.handler.GenericTypeHandler;
import com.spring.domain.model.type.TccStatus;

/**
 * @author Zhao Junjian
 */
public class TccStatusTypeHandler extends GenericTypeHandler<TccStatus> {
    @Override
    public int getEnumIntegerValue(TccStatus parameter) {
        return parameter.getStatus();
    }
}
