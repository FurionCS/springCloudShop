package com.spring.service;

import com.spring.domain.model.UserAddr;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by ErnestCheng on 2017/9/30.
 */
public interface UserAddrService {
    /**
     * 添加用户地址
     * @param userAddr
     * @return
     */
    boolean addUserAddr(UserAddr userAddr) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException;

    /**
     *
     * @return
     */
    List<UserAddr> findUserAddr();

}
