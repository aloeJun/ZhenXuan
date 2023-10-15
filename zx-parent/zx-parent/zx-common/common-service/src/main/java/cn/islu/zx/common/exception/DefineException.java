package cn.islu.zx.common.exception;

import cn.islu.zx.model.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * @author aloeJun
 * @date 2023/10/15 20:12
 * @description: TODO
 */
@Data
public class DefineException extends RuntimeException {

    private Integer code;          // 错误状态码
    private String message;        // 错误消息

    private ResultCodeEnum resultCodeEnum;     // 封装错误状态码和错误消息

    public DefineException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    public DefineException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
