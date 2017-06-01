package com.spring.persistence;

import com.spring.domain.model.Order;
import com.spring.domain.model.OrderParticipant;
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


}
