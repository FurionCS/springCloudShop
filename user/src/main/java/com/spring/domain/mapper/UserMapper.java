package com.spring.domain.mapper;

import com.spring.domain.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description 用户mapper
 * @Author ErnestCheng
 * @Date 2017/5/27.
 */
@Mapper
public interface UserMapper {


    @Insert("insert into t_user(user_name,password,idCard,balance,createTime,leastTime) value(#{userName},#{password},#{idCard},#{balance},#{createTime},#{leastTime})")
    void addUser(User user);
}
