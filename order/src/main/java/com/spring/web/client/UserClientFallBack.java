package com.spring.web.client;

import com.spring.common.model.StatusCode;
import com.spring.common.model.model.ErrorInfo;
import com.spring.common.model.response.ObjectDataResponse;
import com.spring.domain.User;
import com.spring.domain.request.BalanceReservationRequest;
import com.spring.repository.ErrorRepository;
import feign.hystrix.FallbackFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.OffsetDateTime;

/**
 * @Description 用户接口回调
 * @author ErnestCheng
 * @Date 2017/5/31.
 */
@Component
public class UserClientFallBack implements FallbackFactory<UserClient> {
    Logger logger= Logger.getLogger(UserClientFallBack.class);

    @Autowired
    private ErrorRepository errorRepository;
    @Override
    public UserClient create(Throwable throwable) {
        return new UserClient() {
            @Override
            public ObjectDataResponse<User> getUserById(@RequestParam("userId") Integer userId) {
                logger.error("调用用户接口失败getUserById"+throwable.getMessage());
                // 记录到mongodb
                ErrorInfo errorInfo=new ErrorInfo<>();
                errorInfo.setCode(StatusCode.API_Fail);
                errorInfo.setMessage(throwable.getMessage());
                errorInfo.setCreateTime(OffsetDateTime.now());
                errorInfo.setData(userId);
                errorInfo.setUrl("getUserById");
                errorRepository.insert(errorInfo);
                return new ObjectDataResponse<User>(StatusCode.API_Fail,"调用getUserById失败：cause:"+throwable.getMessage());
            }

            @Override
            public ObjectDataResponse reserve(@RequestBody BalanceReservationRequest balanceReservationRequest) {
                logger.error("调用用户接口失败reserve"+throwable.getMessage());
                // 记录到mongodb
                ErrorInfo errorInfo=new ErrorInfo<>();
                errorInfo.setCode(StatusCode.API_Fail);
                errorInfo.setMessage(throwable.getMessage());
                errorInfo.setCreateTime(OffsetDateTime.now());
                errorInfo.setData(balanceReservationRequest);
                errorInfo.setUrl("/balances/reservation");
                errorRepository.insert(errorInfo);
                return new ObjectDataResponse(StatusCode.API_Fail,"调用reserve失败：cause："+throwable.getMessage());
            }
        };
    }
}
