package com.spring.domain.event;

import com.spring.domain.eventBus.DomainEvent;
import com.spring.domain.type.IntegralChangeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ErnestCheng on 2017/10/12.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserIntegralEvent extends DomainEvent {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 变化积分
     */
    private Integer changeSource;
    /**
     * 备注
     */
    private String remark;
    /**
     * 积分改变类型
     */
    private IntegralChangeType changeTypeStatus;
    @Override
    protected String identify() {
        return "user_integral_publisher";
    }
}
