package cn.islu.zx.common.exception;

import cn.islu.zx.model.vo.common.Result;
import cn.islu.zx.model.vo.common.ResultCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author aloeJun
 * @date 2023/10/15 20:12
 * @description: 统一异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DefineException.class)
    @ResponseBody
    public Result error(DefineException e) {
        e.printStackTrace();
        return Result.build(null, e.getResultCodeEnum());
    }
}