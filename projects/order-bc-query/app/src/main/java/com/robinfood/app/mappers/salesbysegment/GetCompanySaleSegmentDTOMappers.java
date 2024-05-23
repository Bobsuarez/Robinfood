package com.robinfood.app.mappers.salesbysegment;

import com.robinfood.core.dtos.report.salebysegment.*;

import java.util.List;

public class GetCompanySaleSegmentDTOMappers {

    public static CompanyBySegmentDTO filtersListToCompanyBySegmentDTO(
            List<BrandSegmentDTO> brandDTOList,
            List<ChannelSegmentDTO> channelDTOList,
            Long idCompany,
            List<PaymentMethodSegmentDTO> paymentMethodsList,
            List<StoreSegmentDTO> storesList) {

       return CompanyBySegmentDTO.builder()
                .brandDTOList(brandDTOList)
                .channelDTOList(channelDTOList)
                .idCompany(idCompany)
                .paymentMethodsList(paymentMethodsList)
                .storesList(storesList)
                .build();
    }
}
