package com.robinfood.paymentmethodsbc.components;

import com.robinfood.paymentmethodsbc.annotations.BaseResponse;
import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.enums.ResponseCode;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Clase que permite unificar el formato de salida de las peticiones realizadas por los
 * controladores
 *
 */
@ControllerAdvice(annotations = BaseResponse.class)
public class ResponseResultHandlerAdvice implements ResponseBodyAdvice<Object> {

    /**
     *
     * @author Johnatan Ramos
     *
     * @param returnType
     * @param converterType
     * @return boolean
     */
    @Override
    public boolean supports(
        MethodParameter returnType,
        Class<? extends HttpMessageConverter<?>> converterType
    ) {
        return true;
    }

    /**
     *
     * @author Johnatan Ramos
     *
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return Object
     */
    @Override
    public Object beforeBodyWrite(
        Object body,
        MethodParameter returnType,
        MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType,
        ServerHttpRequest request,
        ServerHttpResponse response
    ) {
        if (MediaType.APPLICATION_JSON.equals(selectedContentType)) {
            if (body instanceof ResponseDTO) {
                return body;
            } else {
                HttpServletResponse servletResponse =
                    ((ServletServerHttpResponse) response).getServletResponse();
                return new ResponseDTO<Object>(
                    servletResponse.getStatus(),
                    ResponseCode.SUCCESS.getMessage(),
                    body,
                    new ResponseDTO.ErrorDTO()
                );
            }
        }
        return body;
    }
}
