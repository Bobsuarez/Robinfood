package com.robinfood.configurations.dto.v1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.robinfood.configurations.dto.v1.StoreDTO;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserStoreDTO implements Serializable {

    private static final long serialVersionUID = 2296370318659297003L;

    @JsonProperty(value = "userId")
    private Long userId;

    @Schema(example = "Store")
    @JsonProperty(value = "store")
    private StoreDTO store;
}
