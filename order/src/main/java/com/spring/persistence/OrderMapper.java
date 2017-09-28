package com.spring.persistence;

import com.spring.domain.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 订单mapper
 * @Author ErnestCheng
 * @Date 2017/5/31.
 */
@Repository
public interface OrderMapper {
    /**
     * 获得订单列表
     * @return
     */
    List<Order> listOrder();

    /**
     * 添加订单
     * @return
     */
    int addOrder(Order order);

    /**
     * 获得订单
     * @param orderId
     * @return
     */
    Order getOrderById(@Param("orderId") Integer orderId);

    /**
     * 更新订单
     * @param order
     * @return
     */
    int updateOrder(Order order);


}
