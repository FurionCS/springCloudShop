package com.spring.persistence;

import com.spring.domain.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description 用户数据接口
 * @Author ErnestCheng
 * @Date 2017/5/27.
 */
@Repository
public interface UserMapper {
    void addUser(User user);
}
