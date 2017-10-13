package com.spring.domain.type;

/**
 * 积分规则改变类型
 * red表示红字，财务上的红字概念
 */
public enum IntegralChangeType {
    add(0),used(1),red(1);

    private final  Integer status;

    IntegralChangeType(Integer status){
        this.status=status;
    }

    public Integer getStatus() {
        return status;
    }
}
