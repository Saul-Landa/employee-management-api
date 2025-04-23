package com.slanda.employeemanagement.api.util.middlewares;

import com.slanda.employeemanagement.api.util.LogUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Enumeration;

import static com.slanda.employeemanagement.api.util.constants.LogConstants.*;

@Component
public class RequestHeaderLoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        LogUtil.info(REQUEST_HEADERS_START);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            LogUtil.info(REQUEST_HEADERS, headerName, headerValue);
        }

        LogUtil.info(REQUEST_HEADERS_END);
        return true;
    }
}
