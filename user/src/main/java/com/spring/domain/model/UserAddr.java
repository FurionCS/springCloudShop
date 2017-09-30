package com.spring.domain.model;

import com.spring.domain.type.UserAddrStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by ErnestCheng on 2017/9/30.
 */
@Data
@ToString
@NoArgsConstructor
public class UserAddr {
    private int id;
    @NotNull
    private Integer userId;
    @NotNull
    private String receiverName;
    @NotNull
    private String receiverPhone;
    @NotNull
    private String receiverCity;
    @NotNull
    private String receiverDistrict;
    @NotNull
    private String receiverAddress;
    @NotNull
    @Length(min=6,max = 6)
    private String receiverCode;
    private Timestamp createTime;
    private Timestamp updateTime;
    private UserAddrStatus status;
}
