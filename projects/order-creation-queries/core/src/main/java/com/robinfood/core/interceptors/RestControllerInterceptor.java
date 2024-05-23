package com.robinfood.core.interceptors;

import com.robinfood.core.constants.GlobalConstants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Implementation of HandlerInterceptor, this class intercepts the http
 * requests and responses finished statuses
 */
@Component
@Slf4j
public class RestControllerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull Object handler
    ) {
        String controllerBeanName = getControllerBeanName(handler);

        log.info(formatLogControllerAccess(
                GlobalConstants.REQUEST_CONTROLLER,
                controllerBeanName,
                request.getRequestURI()
        ));

        log.info(formatLogControllerAccess(
                request.getQueryString(),
                controllerBeanName,
                request.getRequestURI()
        ));

        return true;
    }

    @Override
    public void postHandle(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull Object handler,
            ModelAndView modelAndView
    ) {
        String controllerBeanName = getControllerBeanName(handler);

        log.info(formatLogControllerAccess(
                GlobalConstants.RESPONSE_CONTROLLER,
                controllerBeanName,
                request.getRequestURI()
        ));
    }

    private String getControllerBeanName(
            Object handler
    ) {
        String controllerBeanName;

        try {
            final Class<?> controllerBean = ((HandlerMethod) handler).getBeanType();
            controllerBeanName = controllerBean.getName();
        } catch (ClassCastException exception) {
            log.warn(
                    String.format("Controller not detected: %s", exception)
            );
            controllerBeanName = "Controller not detected";
        }

        return controllerBeanName;
    }

    /**
     * This method formats the message for Logging
     *
     * @param message Principal log message
     * @param controllerName Refers to controller name that handles the HTTP request
     * @param uri Refers the URI in the HTTP request
     * @return Formated message
     */
    public String formatLogControllerAccess(String message, String controllerName, String uri) {
        return String.format(
                "%s %s %s",
                message,
                controllerName,
                uri
        );
    }
}
