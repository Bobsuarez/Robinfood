package com.robinfood.core.mappers.report.salesegment;

import com.robinfood.core.dtos.PaymentMethodsFilterDTO;
import com.robinfood.core.dtos.SalesBuildAnswerDTO;
import com.robinfood.core.dtos.configuration.BrandDTO;
import com.robinfood.core.dtos.configuration.ChannelDTO;
import com.robinfood.core.dtos.configuration.CompanyDTO;
import com.robinfood.core.dtos.configuration.StoreWithIdAndNameDTO;
import com.robinfood.core.dtos.report.salebysegment.GetSaleBySegmentDTO;

import java.util.List;

public final class SalesBuildAnswerDTOMappers {

    private SalesBuildAnswerDTOMappers() {
        throw new IllegalStateException("Utility class");
    }


    public static SalesBuildAnswerDTO saleSegmentToResponse(
            List<BrandDTO> brandDTOList,
            List<CompanyDTO> companiesDTOList,
            List<ChannelDTO> channelDTOList,
            List<PaymentMethodsFilterDTO> paymentMethodsList,
            GetSaleBySegmentDTO saleBySegmentDTO,
            List<StoreWithIdAndNameDTO> storeDTOList
    ) {

        return SalesBuildAnswerDTO.builder()
                .brandDTOList(brandDTOList)
                .companiesDTOList(companiesDTOList)
                .channelDTOList(channelDTOList)
                .paymentDTOList(paymentMethodsList)
                .saleSegmentDTO(saleBySegmentDTO)
                .storesDTOList(storeDTOList)
                .build();
    }
}
