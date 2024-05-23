package com.robinfood.core.mappers;

import com.robinfood.core.dtos.MenuValidationDTO;
import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import com.robinfood.core.entities.menuvalidationentities.MenuValidationEntity;
import com.robinfood.core.entities.menuvalidationentities.MenuValidationPaymentEntity;
import com.robinfood.core.entities.menuvalidationentities.MenuValidationProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class MenuValidationMappers {

    private MenuValidationMappers() {
    }

    public static MenuValidationEntity toMenuValidationEntity(MenuValidationDTO menuValidationDTO) {

        final OrderDTO orderDTO = menuValidationDTO.getOrder();

        final MenuValidationPaymentEntity menuValidationPaymentEntity = MenuValidationPaymentMappers
                .toMenuValidationPaymentEntity(orderDTO);

        final List<MenuValidationProductEntity> menuValidationProductEntities = orderDTO.getFinalProducts().stream()
                        .map(FinalProductMappers::toMenuValidationProductEntity)
                        .collect(Collectors.toList());

        return MenuValidationEntity.builder()
                .brandId(orderDTO.getBrand().getId())
                .countryId(menuValidationDTO.getCountryId())
                .flowId(menuValidationDTO.getFlowId())
                .payment(menuValidationPaymentEntity)
                .platformId(menuValidationDTO.getPlatformId())
                .products(menuValidationProductEntities)
                .storeId(orderDTO.getStore().getId())
                .uuid(orderDTO.getUuid().toString())
                .build();
    }
}
