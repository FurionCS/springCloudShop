package com.spring.service;

import com.spring.domain.request.TccRequest;

/**
 * @Description
 * @Author ErnestCheng
 * @Date 2017/6/2.
 */
public interface TccService {
    /**
     * 确认预留资源
     * @param request
     */
    void confirm(TccRequest request);

    /**
     * 取消预留资源
     * @param request
     */
    void cancel(TccRequest request);
}
