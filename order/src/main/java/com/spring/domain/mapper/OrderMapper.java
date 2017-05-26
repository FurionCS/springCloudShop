package com.spring.domain.mapper;

import com.spring.domain.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description 订单mapper
 * @Author ErnestCheng
 * @Date 2017/5/26.
 */
@Mapper
public interface OrderMapper {

    @Select("select id,create_time as createTime,update_time as updateTime,delete_time as deleteTime,user_id as userId,product_id as productId,price,status from t_order where user_id=#{userId}")
    List<Order> listOrderByUserId(Integer userId);

}
