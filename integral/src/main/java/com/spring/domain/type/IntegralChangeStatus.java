package com.spring.domain.type;

/**
 * Created by ErnestCheng on 2017/10/11.
 */
public enum IntegralChangeStatus {
    停用(0),正常(1);

    private final Integer status;

    IntegralChangeStatus(int status){this.status=status;}

    public Integer getStatus() {
        return status;
    }
}
