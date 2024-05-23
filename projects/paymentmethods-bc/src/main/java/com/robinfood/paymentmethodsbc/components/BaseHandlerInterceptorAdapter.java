package com.robinfood.paymentmethodsbc.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.enums.ResponseCode;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import static com.robinfood.paymentmethodsbc.utils.ResponseUtils.getErrorDTOMap;

@Component
public class BaseHandlerInterceptorAdapter implements HandlerInterceptor {

    @Override
    public void afterCompletion(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler,
        Exception ex
    )
        throws Exception {
        HandlerInterceptor.super.afterCompletion(
            request,
            response,
            handler,
            ex
        );
    }

    @Override
    public void postHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler,
        ModelAndView modelAndView
    )
        throws Exception {
        HandlerInterceptor.super.postHandle(
            request,
            response,
            handler,
            modelAndView
        );
    }

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    )
        throws Exception {
        if (response.getStatus() == HttpStatus.SC_NOT_FOUND) {
            ResponseDTO<String> responseResult = new ResponseDTO<String>();
            responseResult.setCode(ResponseCode.RESOURCES_NOT_EXIST.getCode());
            responseResult.setData(null);
            responseResult.setMessage(
                ResponseCode.RESOURCES_NOT_EXIST.getMessage()
            );
            responseResult.setError(
                getErrorDTOMap(
                    "NOT_FOUND",
                    "request",
                    ResponseCode.RESOURCES_NOT_EXIST.getMessage()
                )
            );
            response.setContentType("application/json");
            OutputStream out = response.getOutputStream();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(out, responseResult);
            out.flush();
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
