package com.robinfood.core.dtos.report.salebysegment.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
public class CountryDTOResponse {

    private final Long id;

    private final String name;
}
