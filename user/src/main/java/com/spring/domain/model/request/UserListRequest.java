package com.spring.domain.model.request;

import com.spring.common.model.request.PageRequest;
import com.spring.domain.model.type.UserStatus;
import lombok.Data;

/**
 * Created by Mr.Cheng on 2017/9/23.
 */
@Data
public class UserListRequest extends PageRequest {
    private UserStatus userStatus;
}
