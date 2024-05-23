package com.robinfood.core.dtos.report.salebysegment.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ItemDTOResponse {

    private final String currency;

    private final Long id;

    private final String name;

    private final OrdersDTOResponse orders;

}
