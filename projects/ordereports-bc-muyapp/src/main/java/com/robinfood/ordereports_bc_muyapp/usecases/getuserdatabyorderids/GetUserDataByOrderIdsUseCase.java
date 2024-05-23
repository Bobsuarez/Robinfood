package com.robinfood.ordereports_bc_muyapp.usecases.getuserdatabyorderids;

import com.robinfood.ordereports_bc_muyapp.dto.UserDataDTO;
import com.robinfood.ordereports_bc_muyapp.models.mapper.UserDataMapper;
import com.robinfood.ordereports_bc_muyapp.repository.orderuserdata.IOrderUserDataRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * Implementation of IGetUserDataByOrderIdsUseCase
 */
@AllArgsConstructor
@Component
@Slf4j
public class GetUserDataByOrderIdsUseCase implements IGetUserDataByOrderIdsUseCase {

    private final IOrderUserDataRepository userDataRepository;

    private final UserDataMapper userDataMapper;

    @Async
    @Override
    public CompletableFuture<UserDataDTO> invoke(Integer orderId) {

        return CompletableFuture.supplyAsync(() -> getUserDataDTO(orderId));

    }

    private UserDataDTO getUserDataDTO(Integer orderId) {

        return userDataRepository.findByOrderId(orderId)
                .stream()
                .findAny()
                .map(userDataMapper::toUserDataDTO)
                .orElse(UserDataDTO.builder()
                                .build());
    }
}
