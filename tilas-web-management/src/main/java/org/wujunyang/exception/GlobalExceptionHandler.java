package org.wujunyang.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.wujunyang.pojo.Result;

@Slf4j                          // 自动生成日志对象log
@RestControllerAdvice           // 声明为全局Controller异常处理器
public class GlobalExceptionHandler {
    
    /**
     * 处理数据库唯一约束冲突异常
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("数据库唯一约束冲突", e);
        
        String message = e.getMessage();
        if (message.contains("emp.username")) {
            return Result.error("用户名已存在，请使用其他用户名");
        } else if (message.contains("emp.phone")) {
            return Result.error("手机号已存在，请使用其他手机号");
        } else {
            return Result.error("数据重复，请检查输入信息");
        }
    }
    
    /**
     * 处理请求参数缺失异常
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("请求参数缺失", e);
        return Result.error("缺少必需的参数：" + e.getParameterName());
    }
    
    /**
     * 处理参数类型转换异常
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("参数类型转换错误", e);
        return Result.error("参数类型错误：" + e.getName() + " 应该是 " + e.getRequiredType().getSimpleName() + " 类型");
    }
    
    /**
     * 处理业务逻辑异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(RuntimeException e) {
        log.error("业务逻辑异常", e);
        return Result.error(e.getMessage());
    }
    
    /**
     * 处理所有其他异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("程序出错啦~", e);  // 记录错误日志（含堆栈）
        return Result.error("系统繁忙，请稍后重试");  // 返回友好提示
    }
}