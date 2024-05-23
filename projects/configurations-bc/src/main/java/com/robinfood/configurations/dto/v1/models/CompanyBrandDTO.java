package com.robinfood.configurations.dto.v1.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robinfood.configurations.models.AbstractBaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CompanyBrandDTO extends AbstractBaseEntity {

    private static final long serialVersionUID = 4830719431883225267L;

    @Min(0)
    @Schema(example = "0")
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "company_id")
    private Long companyId;

    @Schema(example = "new channel")
    @JsonProperty(value = "name")
    private String name;

}
