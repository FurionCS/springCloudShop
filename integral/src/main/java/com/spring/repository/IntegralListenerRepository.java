package com.spring.repository;

import com.spring.domain.event.UserLoginEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 积分监听存储
 */
@Repository
public interface IntegralListenerRepository  extends MongoRepository<UserLoginEvent,Long> {
}
