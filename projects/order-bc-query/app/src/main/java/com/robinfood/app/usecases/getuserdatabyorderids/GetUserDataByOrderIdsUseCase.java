package com.robinfood.app.usecases.getuserdatabyorderids;

import com.robinfood.app.mappers.UserDataMappers;
import com.robinfood.core.dtos.UserDataDTO;
import com.robinfood.repository.userdata.IUserDataRepository;
import kotlin.collections.CollectionsKt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of IGetUserDataByOrderIdsUseCase
 */
@Component
@Slf4j
public class GetUserDataByOrderIdsUseCase implements IGetUserDataByOrderIdsUseCase {

    private final IUserDataRepository userDataRepository;

    public GetUserDataByOrderIdsUseCase(IUserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @Override
    public List<UserDataDTO> invoke(List<Long> orderIds) {
        log.info("Starting process to get user data by order ids: {}", orderIds);

        return CollectionsKt.map(
            userDataRepository.findAllByOrderIdIn(orderIds),
            UserDataMappers::toUserDataDTO
        );
    }
}
