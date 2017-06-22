package com.spring.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

/**
 * @Description 用户权限对象
 * @Author ErnestCheng
 * @Date 2017/6/22.
 */
@Data
public class UserAuth {

    @Id
    private String id;
    @Indexed(unique=true, direction= IndexDirection.DESCENDING, dropDups=true)
    private  String userName;

    private String password;

    private List<String> roles;

}
