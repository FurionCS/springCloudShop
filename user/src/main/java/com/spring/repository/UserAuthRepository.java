package com.spring.repository;

import com.spring.domain.model.UserAuth;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description  用户权限 mongodb
 * @Author ErnestCheng
 * @Date 2017/6/22.
 */
@Repository
public interface UserAuthRepository  extends MongoRepository<UserAuth,Long> {

    UserAuth findByUserName(String username);
}
