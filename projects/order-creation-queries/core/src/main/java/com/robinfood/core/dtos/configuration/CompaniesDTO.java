package com.robinfood.core.dtos.configuration;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CompaniesDTO {

     private final List<CompanyDTO> companies;
}
