package com.robinfood.core.dtos.configuration;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class TimezonesByCompanyDTO {

    private List<String> timezones;
}
