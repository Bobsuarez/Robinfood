package com.robinfood.configurations.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.configurations.dto.v1.models.CompanyDTO;
import com.robinfood.configurations.dto.v1.models.PosDTO;
import com.robinfood.configurations.dto.v1.models.StoreDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalePointDTO implements Serializable {

    private static final long serialVersionUID = -5326711640123093528L;

    @JsonProperty(value = "pos")
    private PosDTO pos;

    @JsonProperty(value = "store")
    private StoreDTO store;

    @JsonProperty(value = "company")
    private CompanyDTO company;

    @Schema(example = "https://logo.jpg")
    @JsonProperty(value = "logo")
    private String logo;
}
