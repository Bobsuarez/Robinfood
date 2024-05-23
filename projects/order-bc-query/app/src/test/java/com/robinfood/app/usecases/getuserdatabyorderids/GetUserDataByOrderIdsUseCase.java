package com.robinfood.app.usecases.getuserdatabyorderids;

import com.robinfood.app.mappers.UserDataMappers;
import com.robinfood.core.dtos.UserDataDTO;
import com.robinfood.repository.userdata.IUserDataRepository;
import java.util.List;
import kotlin.collections.CollectionsKt;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of IGetUserDataByOrderIdsUseCase
 */
@AllArgsConstructor
@Slf4j
public class GetUserDataByOrderIdsUseCase implements IGetUserDataByOrderIdsUseCase {

    private final IUserDataRepository userDataRepository;

    @Override
    public List<UserDataDTO> invoke(List<Long> orderIds) {

        log.info("Starting process to get user data by order ids: {}", orderIds);

        return CollectionsKt.map(
            userDataRepository.findAllByOrderIdIn(orderIds),
            UserDataMappers::toUserDataDTO
        );
    }
}
