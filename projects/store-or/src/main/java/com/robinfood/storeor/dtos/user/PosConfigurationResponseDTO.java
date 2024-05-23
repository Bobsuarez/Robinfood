package com.robinfood.storeor.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PosConfigurationResponseDTO {

    private UserStoreResponseDTO store;
    private UserPosResponseDTO pos;
}
