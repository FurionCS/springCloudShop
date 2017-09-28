package com.spring.domain.request;

import com.spring.domain.model.Role;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description:角色资源请求
 * @Author : Mr.Cheng
 * @Date:2017/6/23
 */
@Data
public class RoleResourcesRequest {

    @NotNull
    private Role role;

    private List<Integer> resourcesIds;

}
