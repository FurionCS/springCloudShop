package com.spring.persistence;

import com.spring.domain.model.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description 资源
 * @Author ErnestCheng
 * @Date 2017/6/26.
 */
@Repository
public interface ResourcesMapper {
    /**
     * 添加资源
     * @param resource
     * @return
     */
    int addResource(Resource resource);

    /**
     * 获得资源
     * @param id
     * @return
     */
    Resource getResource(@Param("id") Integer id);


}
