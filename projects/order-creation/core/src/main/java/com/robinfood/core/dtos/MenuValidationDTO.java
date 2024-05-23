package com.robinfood.core.dtos;

import com.robinfood.core.dtos.transactionrequestdto.OrderDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MenuValidationDTO {

    private final Long countryId;

    private final Long flowId;

    private final OrderDTO order;

    private final Long platformId;

    private final String timezone;
}
