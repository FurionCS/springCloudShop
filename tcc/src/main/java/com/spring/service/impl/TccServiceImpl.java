package com.spring.service.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.spring.common.model.StatusCode;
import com.spring.common.model.exception.GlobalException;
import com.spring.domain.model.Participant;
import com.spring.domain.model.TccErrorResponse;
import com.spring.domain.model.TccStatus;
import com.spring.domain.request.TccRequest;
import com.spring.service.TccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * @Description
 * @Author ErnestCheng
 * @Date 2017/6/2.
 */
@Service
public class TccServiceImpl  implements TccService{

    @Autowired
    private  RestTemplate restTemplate;

    private static final HttpEntity<?> REQUEST_ENTITY;

    static {
        final HttpHeaders header = new HttpHeaders();
        header.setAccept(ImmutableList.of(MediaType.APPLICATION_JSON_UTF8));
        header.setContentType(MediaType.APPLICATION_JSON_UTF8);
        REQUEST_ENTITY = new HttpEntity<>(header);
    }
    @Override
    public void confirm(TccRequest request) {
        Preconditions.checkNotNull(request);
        List<Participant> participantList=request.getParticipants();
        Preconditions.checkNotNull(participantList);
         int success=0;
         int fail=0;
        for (Participant participant : participantList) {
            participant.setExecuteTime(OffsetDateTime.now());
            //设置重试，防止参与者宕机或网络抖动
            ResponseEntity<String> response=restTemplate.exchange(participant.getUri(), HttpMethod.PUT,REQUEST_ENTITY,String.class);
            if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
                participant.setTccStatus(TccStatus.CONFIRMED);
                success++;
            } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                participant.setTccStatus(TccStatus.TIMEOUT);
                participant.setParticipantErrorResponse(response);
                fail++;
            } else {
                throw new GlobalException("未知错误", StatusCode.API_Fail);
            }
        }
        // 检查是否有冲突
        if (success > 0 && fail > 0) {
            // 出现冲突必须返回并需要人工介入
            throw new GlobalException("all reservation were cancelled or timeout", new TccErrorResponse(participantList));
        } else if (fail == participantList.size()) {
            // 全部timeout
            throw new GlobalException("although we have check the expire time in request body, we got an expiration when confirming actually");
        }
    }
}
