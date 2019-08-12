package com.revert.platform.common.exception;

import com.revert.platform.common.constant.StaticsData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author xiecong
 * @Date 2019/8/1 15:22
 * @Email
 * @Description TODO
 */
@NoArgsConstructor
public class CommonException extends RuntimeException implements Serializable {

    public CommonException(String code, String message){
        this.code = code;
        this.message = message;
    }

    @Getter
    @Setter
    private String code = StaticsData.CODE_ERROR;
    @Getter
    @Setter
    private String message = StaticsData.MSG_ERROR;

}
