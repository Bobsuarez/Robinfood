package com.robinfood.app.usecases.getusersbyfullnamelike;

import com.robinfood.core.dtos.UserDataDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Get users by full name like
 */
public interface IGetUsersByFullNameLikeAndLocalDateUseCase {

    /**
     * Get users by full name like
     *
     * @param firstName first name user
     * @param lastName last name user
     * @return List Users
     */
    List<UserDataDTO> invoke(
            String firstName,
            String lastName,
            Map<String, LocalDateTime> localDateTimeMap,
            Long storeId
    );
}
