package com.robinfood.app.usecases.getuserdatalistbyuserid;

import com.robinfood.app.mappers.UserDataMappers;
import com.robinfood.core.dtos.UserDataDTO;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.repository.userdata.IUserDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetUserDataByOrderIdUseCase implements IGetUserDataByOrderIdUseCase {

    private final IUserDataRepository userDataRepository;

    public GetUserDataByOrderIdUseCase(IUserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @Override
    public UserDataDTO invoke(Long orderId) {

        log.info("Start of the user search process for the order with id {}", orderId);

        UserDataDTO userDataDTO = userDataRepository.findByOrderId(orderId)
            .map(UserDataMappers::toUserDataDTO)
            .orElseThrow( ()-> new GenericOrderBcException("User not found"));

        log.info("User found {}", userDataDTO);

        return userDataDTO;
    }
}
