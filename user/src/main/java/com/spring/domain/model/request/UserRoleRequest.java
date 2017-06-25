package com.spring.domain.model.request;

import com.spring.domain.model.Role;
import com.spring.domain.model.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description:用户和角色请求
 * @Author : Mr.Cheng
 * @Date:2017/6/23
 */
@Data
public class UserRoleRequest {
    /**
     * 用户
      */
    @NotNull
   private User user;
    /**
     * 角色id列表
     */
   private List<Integer> roleIds;

}
