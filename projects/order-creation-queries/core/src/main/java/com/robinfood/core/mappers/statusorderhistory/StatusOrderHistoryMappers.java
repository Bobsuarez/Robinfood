package com.robinfood.core.mappers.statusorderhistory;

import com.robinfood.core.dtos.statusorderhistory.OrderUserDataDTO;
import com.robinfood.core.dtos.statusorderhistory.StatusOrderHistoryDTO;
import com.robinfood.core.dtos.user.UserDTO;

import java.util.List;

public final class StatusOrderHistoryMappers {

    private StatusOrderHistoryMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static StatusOrderHistoryDTO buildUserData(
            StatusOrderHistoryDTO statusOrderHistoryDTO,
            List<UserDTO> userDTOList
    ) {

        final UserDTO userDTO = userDTOList.stream()
                .filter((UserDTO user) -> user.getId().equals(statusOrderHistoryDTO.getUserId()))
                .findFirst()
                .orElseThrow();

        statusOrderHistoryDTO.setOrderUserData(
                OrderUserDataDTO.builder()
                        .firstName(userDTO.getFirstName())
                        .lastName(userDTO.getLastName())
                        .build()
        );

        return statusOrderHistoryDTO;
    }
}
