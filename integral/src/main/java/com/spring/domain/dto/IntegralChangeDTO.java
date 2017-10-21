package com.spring.domain.dto;


import com.google.common.base.Converter;
import com.spring.domain.model.IntegralChange;
import com.spring.domain.type.IntegralChangeStatus;
import com.spring.domain.type.IntegralChangeType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;

/**
 * 积分变化DTO
 * @author cs
 */
@Data
@NoArgsConstructor
public class IntegralChangeDTO{
    /**
     * 改变名称
     */
    @NonNull
    private String changeName;
    /**
     * 规则编码
     */
    @NonNull
    private String code;
    /**
     * 描述
     */
    @NonNull
    private String changeDep;
    /**
     * 积分变化类型
     */
    @NonNull
    private IntegralChangeType changeType;
    /**
     * 状态
     */
    @NonNull
    private IntegralChangeStatus status;
    /**
     * 数学公式
     */
    @NonNull
    private String math;

    public IntegralChange convertToIntegralChange(){
        UserDTOConvert userDTOConvert = new UserDTOConvert();
        IntegralChange convert = userDTOConvert.convert(this);
        return convert;
    }

    public IntegralChangeDTO convertFor(IntegralChange user){
        UserDTOConvert userDTOConvert = new UserDTOConvert();
        IntegralChangeDTO convert = userDTOConvert.reverse().convert(user);
        return convert;
    }

    private static class UserDTOConvert extends Converter<IntegralChangeDTO, IntegralChange> {
        @Override
        protected IntegralChange doForward(IntegralChangeDTO integralChangeDTO) {
            IntegralChange integralChange = new IntegralChange();
            BeanUtils.copyProperties(integralChangeDTO,integralChange);
            return integralChange;
        }

        @Override
        protected IntegralChangeDTO doBackward(IntegralChange integralChange) {
            IntegralChangeDTO integralChangeDTO = new IntegralChangeDTO();
            BeanUtils.copyProperties(integralChange,integralChangeDTO);
            return integralChangeDTO;
        }
    }
}
