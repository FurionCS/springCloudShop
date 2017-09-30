package com.spring.domain.type;

/**
 * Created by ErnestCheng on 2017/9/30.
 */
public enum UserAddrStatus {
    停用(0),正常(1);
    private final int status;
    UserAddrStatus(int status){this.status=status;}
    public int getStatus() {
        return status;
    }

}
