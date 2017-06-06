package com.spring.web.client;

import com.spring.common.model.StatusCode;
import com.spring.common.model.exception.GlobalException;
import com.spring.common.model.model.ErrorInfo;
import com.spring.domain.request.TccRequest;
import com.spring.repository.ErrorRepository;
import feign.hystrix.FallbackFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.OffsetDateTime;

/**
 * @Description tcc调用回调
 * @Author ErnestCheng
 * @Date 2017/6/2.
 */
@Component
public class TccClientFallBack implements FallbackFactory<TccClient>{
    Logger logger= Logger.getLogger(TccClientFallBack.class);

    @Autowired
    private ErrorRepository errorRepository;
    @Override
    public TccClient create(Throwable throwable) {
        return new TccClient() {
            @Override
            public void confirm(@RequestBody TccRequest tccRequest) {
                logger.error("调用确认资源错误：confirm：case:"+throwable.getMessage());
                // 记录到Mongodb
                ErrorInfo errorInfo=new ErrorInfo<>();
                errorInfo.setCode(StatusCode.API_Fail);
                errorInfo.setMessage(throwable.getMessage());
                errorInfo.setCreateTime(OffsetDateTime.now());
                errorInfo.setData(tccRequest);
                errorInfo.setUrl("/tcc/coordinator/confirmation");
                errorRepository.insert(errorInfo);
                throw new GlobalException("api失败：", StatusCode.API_Fail);
            }
        };
    }
}
