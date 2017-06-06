package com.spring.repository;

import com.spring.common.model.model.ErrorInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description 错误存储
 * @Author ErnestCheng
 * @Date 2017/6/6.
 */
@Repository
public interface ErrorRepository  extends MongoRepository<ErrorInfo,Long>{
}
