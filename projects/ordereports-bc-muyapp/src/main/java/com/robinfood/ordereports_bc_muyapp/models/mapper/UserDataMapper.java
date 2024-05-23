package com.robinfood.ordereports_bc_muyapp.models.mapper;

import com.robinfood.ordereports_bc_muyapp.dto.UserDataDTO;
import com.robinfood.ordereports_bc_muyapp.models.entities.OrderUserDataEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDataMapper {

    public UserDataDTO toUserDataDTO(
            OrderUserDataEntity userDataEntity
    ) {
        return UserDataDTO.builder()
                .email(userDataEntity.getEmail())
                .firstName(userDataEntity.getFirstName())
                .id(userDataEntity.getUserId())
                .lastName(userDataEntity.getLastName())
                .mobile(userDataEntity.getMobile())
                .orderId(userDataEntity.getOrderId())
                .build();
    }

}
