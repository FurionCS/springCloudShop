package com.spring.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spring.common.model.util.converter.OffsetDateTimeToIso8601Serializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * @author Zhao Junjian
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Participant implements Serializable {

    private static final long serialVersionUID = -8013238614014494468L;

    @ApiModelProperty(value = "发起确认操作的URI", required = true, example = "http://www.example.com/part/123")
    private String uri;

    @ApiModelProperty(value = "过期时间, ISO标准", required = true, example = "2017-03-20T14:00:41+08:00")
    @JsonSerialize(using = OffsetDateTimeToIso8601Serializer.class)
    private OffsetDateTime expireTime;

    @ApiModelProperty(value = "发起confirm的时间, ISO标准", hidden = true, example = "2017-03-20T14:00:41+08:00")
    @JsonSerialize(using = OffsetDateTimeToIso8601Serializer.class)
    private OffsetDateTime executeTime;

    @ApiModelProperty(value = "参与方返回的错误码", hidden = true, example = "451")
    private ResponseEntity<?> participantErrorResponse;

    @ApiModelProperty(value = "预留资源的状态", hidden = true, example = "TO_BE_CONFIRMED")
    private TccStatus tccStatus = TccStatus.TO_BE_CONFIRMED;
}
