package com.robinfood.configurations.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyResponseDTO implements Serializable {

    private static final long serialVersionUID = 4637180375121276617L;

    @NotBlank
    @JsonProperty("companies")
    public List<CompanyBodyHeaderDTO> companiesList;

}
