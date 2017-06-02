package com.spring.domain.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.common.model.response.Response;
import com.spring.common.model.response.RestfulResponse;
import com.spring.domain.model.Participant;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Zhao Junjian
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse extends RestfulResponse {

    private static final long serialVersionUID = 841299264931794001L;

    @ApiModelProperty(value = "参与者确认资源", required = true)
    private Participant participantLink;

    public ReservationResponse(Participant participant) {
        this.participantLink = participant;
    }

}
