package com.spring.domain.model;

import com.spring.domain.type.IntegralChangeStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * 积分改变
 * Created by ErnestCheng on 2017/10/11.
 */
@Data
@ToString
@NoArgsConstructor
public class IntegralChange {
    /**
     * 积分改变id
     */
    private int id;
    /**
     * 改变名称
     */
    private String changeName;
    /**
     * 描述
     */
    private String changeDep;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 状态
     */
    private IntegralChangeStatus status;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
    /**
     * 数学公式
     */
    private String math;


}
