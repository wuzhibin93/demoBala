package com.enlink.de.src.main.java.com.enlink.exception;

import com.enlink.de.src.main.java.com.enlink.controller.common.AjaxResult;
import com.enlink.de.src.main.java.com.enlink.controller.common.HttpCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author changgq
 */
@Slf4j
@ControllerAdvice
public class GlobalException {

    @ResponseBody
    @ExceptionHandler({Exception.class})
    public AjaxResult handler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        LOGGER.error(e.getMessage());
        return AjaxResult.errorOf(HttpCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
