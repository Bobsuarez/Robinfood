package com.robinfood.app.usecases.getuserdatalistbyuserid;

import com.robinfood.app.mappers.UserDataMappers;
import com.robinfood.core.dtos.UserDataDTO;
import com.robinfood.core.entities.OrderUserDataEntity;
import com.robinfood.core.utilities.LocalDateTimeUtil;
import com.robinfood.repository.userdata.IUserDataRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of IGetUserDataListByUserIdAndDateUseCase
 */
@Component
@Slf4j
public class GetUserDataListByUserIdAndDateUseCase implements IGetUserDataListByUserIdAndDateUseCase {

    private final IUserDataRepository userDataRepository;

    public GetUserDataListByUserIdAndDateUseCase(IUserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @Override
    public List<UserDataDTO> invoke(
            LocalDate createdAt,
            Long userId
    ) {
        log.info("Starting process to get user data with user id [{}]", userId);

        final LocalDateTimeUtil localDateTimeUtil = new LocalDateTimeUtil(createdAt.atStartOfDay());
        final LocalDateTime initialDateTime = localDateTimeUtil.startOfDay();
        final LocalDateTime endDateTime = localDateTimeUtil.lastOfDay();
        final List<OrderUserDataEntity> userDataList = userDataRepository.findByUserIdAndCreatedAtBetween(
                userId,
                initialDateTime,
                endDateTime
        );
        return CollectionsKt.map(
                userDataList,
                UserDataMappers::toUserDataDTO
        );
    }
}
