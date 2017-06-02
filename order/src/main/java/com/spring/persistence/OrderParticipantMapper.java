package com.spring.persistence;

import com.spring.domain.model.OrderParticipant;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 订单预留资源信息
 * @Author ErnestCheng
 * @Date 2017/6/1.
 */
@Repository
public interface OrderParticipantMapper {
    /**
     * 添加预留资源信息
     * @param orderParticipant
     * @return
     */
    int addOrderParticipant(OrderParticipant orderParticipant);

    /**
     * 获得订单资源列表
     * @param orderId
     * @return
     */
    List<OrderParticipant> listOrderParticipantByOrderId(@Param("orderId") Integer orderId);
}
