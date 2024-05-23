package com.robinfood.app.usecases.hasaccessinformationbyuser;

import com.robinfood.core.dtos.UserDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.exceptions.UnauthorizedAccessException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Implementation of IGetAuthenticatedUserUseCase
 */
@AllArgsConstructor
@Service
@Slf4j
public class HasAccessInformationByUserUseCase implements IHasAccessInformationByUserUseCase {
    @Override
    public Result<Long> invoke(Long userId) {
        log.trace("Validate access to information of user {}", userId);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDTO user = (UserDTO) authentication.getPrincipal();

        Long userAuthenticatedId = Long.valueOf(user.getUsername());

        if(!userId.equals(userAuthenticatedId)) {
            return new Result.Error(
                new UnauthorizedAccessException("You cannot consult information of other users"),
                HttpStatus.UNAUTHORIZED
            );
        }

        return new Result.Success<>(userAuthenticatedId);
    }
}
