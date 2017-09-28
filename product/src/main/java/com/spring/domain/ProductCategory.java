package com.spring.domain;

import com.spring.domain.type.ProductCategoryStatus;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by ErnestCheng on 2017/9/27.
 */
@Data
public class ProductCategory {
    private int id;
    private String name;
    private Integer sortOrder;
    private Timestamp createTime;
    private Timestamp updateTime;
    private ProductCategoryStatus status;
}
