package com.spring.web.client;

import com.spring.common.model.StatusCode;
import com.spring.domain.model.User;
import com.spring.domain.response.ObjectDataResponse;
import feign.hystrix.FallbackFactory;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description 用户接口回调
 * @Author ErnestCheng
 * @Date 2017/5/31.
 */
@Component
public class UserClientFallBack implements FallbackFactory<UserClient> {
    Logger logger= Logger.getLogger(UserClientFallBack.class);
    @Override
    public UserClient create(Throwable throwable) {
        return new UserClient() {
            @Override
            public ObjectDataResponse<User> getUserById(@RequestParam("userId") Integer userId) {
                logger.error("调用用户接口失败getUserById"+throwable.getMessage());
                ObjectDataResponse objectDataResponse=new ObjectDataResponse();
                objectDataResponse.setCode(StatusCode.API_Fail);
                objectDataResponse.setMessage("调用getUserById失败：cause:"+throwable.getMessage());
                //TODO 记录到mongodb
                return objectDataResponse;
            }
        };
    }
}
