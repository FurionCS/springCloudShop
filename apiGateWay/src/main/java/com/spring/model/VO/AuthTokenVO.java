
package com.spring.model.VO;

import lombok.Data;

import java.util.List;

/**
 * Created by YangFan on 2016/11/28 上午10:53.
 * <p/>
 */
@Data
public class AuthTokenVO {
    private String token;
    private Long userId;
    private List<String> resourceList;

}
