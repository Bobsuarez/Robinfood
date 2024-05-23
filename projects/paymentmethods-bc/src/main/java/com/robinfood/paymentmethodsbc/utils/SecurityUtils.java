package com.robinfood.paymentmethodsbc.utils;

import com.robinfood.paymentmethodsbc.dto.sso.SSOUserDTO;
import com.robinfood.paymentmethodsbc.exceptions.BaseException;
import com.robinfood.paymentmethodsbc.security.SessionUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

    private SecurityUtils() {}

    public static Long getUserId() throws BaseException {
        Authentication authentication = SecurityContextHolder
            .getContext()
            .getAuthentication();

        final SessionUser sessionUser = (SessionUser) authentication.getPrincipal();
        final SSOUserDTO userDTO = sessionUser.getSsoUser();

        if (userDTO == null) {
            throw new BaseException("No existen datos de usuario en sesión");
        }

        return (long) userDTO.getUserId();
    }
}
