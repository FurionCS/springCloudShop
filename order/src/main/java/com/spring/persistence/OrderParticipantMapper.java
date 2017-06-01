package com.spring.persistence;

import com.spring.domain.model.OrderParticipant;
import org.springframework.stereotype.Repository;

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
}
