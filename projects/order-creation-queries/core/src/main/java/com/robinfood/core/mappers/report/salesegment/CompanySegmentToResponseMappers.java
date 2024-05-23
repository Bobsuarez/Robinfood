package com.robinfood.core.mappers.report.salesegment;

import com.robinfood.core.dtos.configuration.CompanyDTO;
import com.robinfood.core.dtos.report.salebysegment.response.BrandsDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.ChannelsDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.CompanyDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.CountryDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.PaymentMethodsDTOResponse;
import com.robinfood.core.dtos.report.salebysegment.response.StoresDTOResponse;

public final class CompanySegmentToResponseMappers {

    private CompanySegmentToResponseMappers() {
        throw new IllegalStateException("Utility class");
    }

    public static CompanyDTOResponse toCompanyResponse(
            CompanyDTO companyInformation,
            BrandsDTOResponse resultBrands,
            ChannelsDTOResponse resultChannels,
            PaymentMethodsDTOResponse resultPaymentMethods,
            StoresDTOResponse resultStores
    ) {

        return CompanyDTOResponse.builder()
                .id(companyInformation.getId())
                .name(companyInformation.getName())
                .currency(companyInformation.getCurrency_type())
                .country(
                        CountryDTOResponse.builder()
                                .id(companyInformation.getCountry().getId())
                                .name(companyInformation.getCountry().getName())
                                .build()
                )
                .brands(resultBrands)
                .channels(resultChannels)
                .stores(resultStores)
                .paymentMethods(resultPaymentMethods)
                .build();
    }
}
