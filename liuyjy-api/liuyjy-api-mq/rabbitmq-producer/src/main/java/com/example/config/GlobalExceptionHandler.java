package com.example.config;

import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.example.liuyjyentity.response.HartReturnResult;
import com.example.liuyjyentity.response.ResponseParam;
import com.fasterxml.jackson.core.JsonParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通用 Api Controller 全局异常处理
 * </p>
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * <p>
     * 自定义 REST 业务异常
     * <p>
     *
     * @param e 异常类型
     * @return
     */
    String error = "Rest request error{}";


    @ExceptionHandler(value = Exception.class)
    public ResponseParam handleBadRequest(Exception e) {
        /*
         * 业务逻辑异常
         */

        if (e instanceof ApiException) {
            IErrorCode errorCode = ((ApiException) e).getErrorCode();
            if (null != errorCode) {
                log.error(error, errorCode.toString());
                return HartReturnResult.systemError(errorCode.toString());
            }
            log.error(error, e.getMessage());
            return HartReturnResult.systemError(e.getMessage());
        }

        /*
         * 参数校验异常
         */
        if (e instanceof BindException) {
            BindingResult bindingResult = ((BindException) e).getBindingResult();
            if (null != bindingResult && bindingResult.hasErrors()) {
                List<Object> jsonList = new ArrayList<>();
                bindingResult.getFieldErrors().stream().forEach(fieldError -> {
                    Map<String, Object> jsonObject = new HashMap<>(2);
                    jsonObject.put("name", fieldError.getField());
                    jsonObject.put("msg", fieldError.getDefaultMessage());
                    jsonList.add(jsonObject);
                });
                log.error(error, e.getMessage());
                return HartReturnResult.systemError(e.getMessage());
            }
        }

        /**
         * 系统内部异常，打印异常栈
         */
        log.error("Error: handleBadRequest StackTrace : {}", e);
        return HartReturnResult.systemError(e.getMessage());
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseParam handleError1(MultipartException e, RedirectAttributes redirectAttributes) {
        log.error(error, e.getMessage());
        return HartReturnResult.systemError("文件处理时出现异常");
    }

    @ExceptionHandler(IOException.class)
    public ResponseParam handleError1(IOException e, RedirectAttributes redirectAttributes) {
        log.error(error, e.getCause().getMessage());
        return HartReturnResult.systemError("文件处理时出现异常");
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseParam handleError1(JsonParseException e, RedirectAttributes redirectAttributes) {
        log.error(error, e.getCause().getMessage());
        return HartReturnResult.systemError("json解析错误");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseParam handleError1(HttpMessageNotReadableException e, RedirectAttributes redirectAttributes) {
        log.error(error, e.getCause().getMessage());
        return HartReturnResult.systemError("json解析错误");
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseParam handleError1(NumberFormatException e, RedirectAttributes redirectAttributes) {
        log.error(error, e.getCause().getMessage());
        return HartReturnResult.systemError("数据内容解析错误");
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseParam handleError1(NullPointerException e, RedirectAttributes redirectAttributes) {
        log.error(error, e.getCause().getMessage());
        return HartReturnResult.systemError("数据错误");
    }


}
