package com.koolyun.ysf.ysfdemo.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 不管是API还是HTML, 都统一异常处理
 */
@ControllerAdvice(basePackages = {"com.koolyun.ysf"})
@Slf4j
public class GlobalExceptionHandler implements ResponseBodyAdvice<Object> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    // 只对RestContoller 起作用 (只对@ResponseBody的Action起作用)
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType
            , Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        // 防止二次封装
        if (body instanceof Result) {
            return body;
        }

        // 防止controller返回是String类型报错
        if (body instanceof String) {
            return objectMapper.writeValueAsString(Result.ok(body));     // 返回String
        }

        return Result.ok(body);  // 返回Result
    }



    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object exceptionHandler(HttpServletRequest httpServletRequest, Exception e) {
        log.error("全局异常信息 ex= {}", e);


        // API接口异常
        if (isApiRequest(httpServletRequest)) {
            return Result.fail(50000, e.getMessage());
        } else {
            // 页面异常
            ModelAndView mav = new ModelAndView();
            mav.setViewName("500.html");
            return mav;
        }
    }

    /**
     * 是否为API请求
     *
     * @param request
     * @return
     */
    private boolean isApiRequest(HttpServletRequest request) {
        return request.getRequestURI().contains("/api/");
    }


}

