package com.robinfood.paymentmethodsbc.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.paymentmethodsbc.dto.common.response.ResponseDTO;
import com.robinfood.paymentmethodsbc.enums.ResponseCode;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import static com.robinfood.paymentmethodsbc.utils.ResponseUtils.getErrorDTOMap;

/**
 * Clase componente AuthenticationEntryPoint que permite definir el
 * comportamiento de la autenticacion en spring asi como la salida de error se
 * personalizo la salida para que usara ResponseResultDTO
 *
 */
@Component
public class JwtAuthenticationEntryPoint
    implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public void commence(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException authException
    )
        throws IOException {
        ResponseDTO<String> responseResult = new ResponseDTO<>();
        responseResult.setCode(ResponseCode.UNAUTHORIZED.getCode());
        responseResult.setData(null);
        responseResult.setMessage(ResponseCode.UNAUTHORIZED.getMessage());
        responseResult.setError(
            getErrorDTOMap("UNAUTORIZED", "user", "Acceso denegado")
        );
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, responseResult);
        out.flush();
    }
}
