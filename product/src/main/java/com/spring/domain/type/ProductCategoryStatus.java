package com.spring.domain.type;

/**
 * Created by ErnestCheng on 2017/9/27.
 */
public enum ProductCategoryStatus {
    停用(0),正常(1);

    private final int status;

    ProductCategoryStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
