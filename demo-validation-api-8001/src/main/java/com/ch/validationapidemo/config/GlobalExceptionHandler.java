package com.ch.validationapidemo.config;

import com.ch.common.domain.Result;
import javafx.fxml.Initializable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.spi.InitialContextFactory;
import javax.validation.ValidationException;

/**
 * @className: GlobalExceptionHandler
 * @Auther: ch
 * @Date: 2022/3/1 09:52
 * @Description: 全局异常捕获
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler{
    /**
     * 处理Validated校验异常
     * <p>
     * 注: 常见的ConstraintViolationException异常， 也属于ValidationException异常
     *
     * @param e 捕获到的异常
     * @return 返回给前端的data
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    public Result handleParameterVerificationException(BindException e) {
        Result<Object> result = new Result<>();
        result.setCode(HttpStatus.BAD_REQUEST.value());
        result.setSuccess(false);
        result.setMessage(e.getAllErrors().get(0).getDefaultMessage());
        result.setTimestamp(System.currentTimeMillis());
        return result;
    }
}
