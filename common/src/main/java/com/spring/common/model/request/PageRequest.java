package com.spring.common.model.request;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Min;

/**
 * Created by Mr.Cheng on 2017/9/23.
 */
@Data
public class PageRequest extends  RestfulRequest{
    /**
     * 开始时间
     */
    private String startDate;
    /**
     * 结束时间
     */
    private String endDate;
    /**
     * 第几页
     */
    private int pageIndex;
    /**
     * 分页大小
     */
    private int pageSize;
    /**
     * 获得几条
     */
    private int limit;
}
