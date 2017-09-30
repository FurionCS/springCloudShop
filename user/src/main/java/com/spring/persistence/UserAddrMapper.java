package com.spring.persistence;

import com.spring.domain.model.UserAddr;
import org.springframework.stereotype.Repository;

/**
 * Created by ErnestCheng on 2017/9/30.
 */
@Repository
public interface UserAddrMapper {
    /**
     * 添加用户地址
     * @param userAddr
     * @return
     */
    Integer addUserAddr(UserAddr userAddr);
}
