package com.robinfood.core.dtos.report.salebysegment.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CompanyDTOResponse {

    private final BrandsDTOResponse brands;

    private final ChannelsDTOResponse channels;

    private final CountryDTOResponse country;

    private final String currency;

    private final Long id;

    private final String name;

    private final PaymentMethodsDTOResponse paymentMethods;

    private final StoresDTOResponse stores;

}
