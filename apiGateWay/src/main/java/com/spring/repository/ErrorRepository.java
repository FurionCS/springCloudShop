package com.spring.repository;

import com.spring.model.ErrorInfo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 异常处理
 * @Author ErnestCheng
 * @Date 2017/6/23.
 */
@Repository
public interface ErrorRepository  extends MongoRepository<ErrorInfo,Long> {

}
