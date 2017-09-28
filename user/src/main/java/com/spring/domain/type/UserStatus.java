package com.spring.domain.type;

/**
 * Created by Mr.Cheng on 2017/9/23.
 */
public enum UserStatus {
    停用(0),正常(1),黑名单(1);
    private final int status;
    UserStatus(int status){this.status=status;}
    public int getStatus() {
        return status;
    }
}
