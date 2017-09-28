package com.spring.domain.type.handler;

import com.spring.common.model.type.handler.GenericTypeHandler;
import com.spring.domain.type.UserStatus;

/**
 * Created by Mr.Cheng on 2017/9/23.
 */
public class UserStatusTypeHandler extends GenericTypeHandler<UserStatus> {
    @Override
    public int getEnumIntegerValue(UserStatus parameter) {
        return parameter.getStatus();
    }
}
