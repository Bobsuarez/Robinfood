package com.robinfood.customersbc.thirdparties.commands.factories;

import com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers.AuthenticationCredentialsNotFoundExceptionHandler;
import com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers.EntityNotFoundExceptionHandler;
import com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers.JsonConversionExceptionHandler;
import com.robinfood.customersbc.thirdparties.exceptions.EntityNotFoundException;
import com.robinfood.customersbc.thirdparties.exceptions.JsonConversionException;
import com.robinfood.customersbc.thirdparties.commands.handlers.ExceptionHandler;
import com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers.AccessDeniedExceptionHandler;
import com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers.AuthenticationExceptionHandler;
import com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers.ServerWebInputExceptionHandler;
import com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers.SignatureExceptionHandler;
import com.robinfood.customersbc.thirdparties.commands.handlers.impl.exceptionhandlers.WebExchangeBindExceptionHandler;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class ExceptionHandlerFactory {
    private static final Map<String, ExceptionHandler> commandMap = new HashMap<>();

    static {
        commandMap.put(AccessDeniedException.class.getSimpleName(), new AccessDeniedExceptionHandler());
        commandMap.put(AuthenticationException.class.getSimpleName(), new AuthenticationExceptionHandler());
        commandMap.put(
            AuthenticationCredentialsNotFoundException.class.getSimpleName(),
            new AuthenticationCredentialsNotFoundExceptionHandler()
        );
        commandMap.put(WebExchangeBindException.class.getSimpleName(), new WebExchangeBindExceptionHandler());
        commandMap.put(ServerWebInputException.class.getSimpleName(), new ServerWebInputExceptionHandler());
        commandMap.put(JsonConversionException.class.getSimpleName(), new JsonConversionExceptionHandler());
        commandMap.put(SignatureException.class.getSimpleName(), new SignatureExceptionHandler());
        commandMap.put(EntityNotFoundException.class.getSimpleName(), new EntityNotFoundExceptionHandler());
    }

    private ExceptionHandlerFactory() {}

    public static Optional<ExceptionHandler> getExceptionHandler(String methodClass) {
        return Optional.ofNullable(commandMap.get(methodClass));
    }
}
