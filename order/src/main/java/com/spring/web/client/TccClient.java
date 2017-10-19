package com.spring.web.client;

import com.spring.domain.request.TccRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description tcc调用
 * @author ErnestCheng
 * @Date 2017/6/2.
 */
@FeignClient(name="tcc",fallbackFactory = TccClientFallBack.class)
public interface TccClient {

    @RequestMapping(value="/tcc/coordinator/confirmation",method = RequestMethod.POST)
    void confirm(@RequestBody TccRequest tccRequest);

    @RequestMapping(value="/tcc/coordinator/cancellation",method = RequestMethod.POST)
    void cancel(@RequestBody TccRequest tccRequest);
}
