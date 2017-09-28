package com.spring.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spring.common.model.util.converter.OffsetDateTimeToIso8601Serializer;
import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * @author Zhao Junjian
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Participant implements Serializable {

    private static final long serialVersionUID = -8013238614014494468L;

//    @ApiModelProperty(value = "发起确认操作的URI", required = true, example = "http://www.example.com/part/123")
    private String uri;

  //  @ApiModelProperty(value = "过期时间, ISO标准", required = true, example = "2017-03-20T14:00:41+08:00")
    @JsonSerialize(using = OffsetDateTimeToIso8601Serializer.class)
    private OffsetDateTime expireTime;
}
