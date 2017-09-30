package com.spring.domain.type.handler;

import com.spring.common.model.type.handler.GenericTypeHandler;
import com.spring.domain.type.TccStatus;
import com.spring.domain.type.UserAddrStatus;

/**
 * Created by ErnestCheng on 2017/9/30.
 */
public class UserAddrStatusTypeHandler extends GenericTypeHandler<UserAddrStatus> {
    @Override
    public int getEnumIntegerValue(UserAddrStatus parameter) {
        return parameter.getStatus();
    }
}
