package com.robinfood.app.usecases.getusersbyfullnamelike;

import com.robinfood.app.mappers.UserDataMappers;
import com.robinfood.core.dtos.UserDataDTO;
import com.robinfood.core.entities.OrderUserDataEntity;
import com.robinfood.repository.userdata.IUserDataRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_TIME_END;
import static com.robinfood.core.constants.GlobalConstants.LOCAL_DATE_TIME_START;

/**
 * Implementation of IGetUsersByFullNameLikeAndLocalDate
 */
@Service
public class GetUsersByFullNameLikeAndLocalDateUseCase implements IGetUsersByFullNameLikeAndLocalDateUseCase {

    private final IUserDataRepository userDataRepository;

    public GetUsersByFullNameLikeAndLocalDateUseCase(IUserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @Override
    public List<UserDataDTO> invoke(
            String firstName,
            String lastName,
            Map<String, LocalDateTime> localDateTimeMap,
            Long storeId
    ) {

        List<OrderUserDataEntity> orderUserDataEntities = userDataRepository
                .findAllByFirstNameContainingOrLastNameContainingAndCreatedAtBetween(
                        firstName,
                        lastName,
                        localDateTimeMap.get(LOCAL_DATE_TIME_START),
                        localDateTimeMap.get(LOCAL_DATE_TIME_END),
                        storeId
                );

        return orderUserDataEntities.stream()
                .map(UserDataMappers::toUserDataDTO).collect(Collectors.toList());

    }

}
