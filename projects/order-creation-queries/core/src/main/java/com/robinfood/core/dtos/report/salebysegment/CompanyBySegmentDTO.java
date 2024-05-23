package com.robinfood.core.dtos.report.salebysegment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class CompanyBySegmentDTO {

    private final List<BrandSegmentDTO> brands;

    private final List<ChannelSegmentDTO> channels;

    private final Long id;

    private final List<PaymentMethodSegmentDTO> paymentMethods;

    private final List<StoreSegmentDTO> stores;

}
