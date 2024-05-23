package com.robinfood.configurations.dto.v1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.configurations.models.AbstractBaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CompanyBrandResponseDTO extends AbstractBaseEntity {

    private static final long serialVersionUID = 4830719431883225267L;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "logo")
    private String logo;

    @JsonProperty(value = "menuId")
    private Long menuId;

}
