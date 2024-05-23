package com.robinfood.storeor.dtos;

import com.robinfood.storeor.dtos.response.StoreResponseDTO;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class UserStoreConfigurationsResponseDTO {

    private Long userId;
    private StoreResponseDTO store;
}
