package com.spring.domain.model.VO;

import com.spring.domain.model.Role;
import lombok.Data;

import java.util.List;

/**
 * @Description 用户角色VO
 * @Author ErnestCheng
 * @Date 2017/6/23.
 */
@Data
public class UserRoleVO {
    /**
     * 用户id
     */
    private int userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户角色
     */
    private List<Role> roles;
}
