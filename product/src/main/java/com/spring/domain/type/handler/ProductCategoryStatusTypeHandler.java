package com.spring.domain.type.handler;


import com.spring.common.model.type.handler.GenericTypeHandler;
import com.spring.domain.type.ProductCategoryStatus;
import com.spring.domain.type.TccStatus;

/**
 * @author Mr.Cheng
 */
public class ProductCategoryStatusTypeHandler extends GenericTypeHandler<ProductCategoryStatus> {
    @Override
    public int getEnumIntegerValue(ProductCategoryStatus parameter) {
        return parameter.getStatus();
    }
}
