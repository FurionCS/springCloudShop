package com.spring.persistence;

import com.spring.domain.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 订单mapper
 * @Author ErnestCheng
 * @Date 2017/5/31.
 */
@Repository
public interface OrderMapper {

    List<Order> listOrder();
}
