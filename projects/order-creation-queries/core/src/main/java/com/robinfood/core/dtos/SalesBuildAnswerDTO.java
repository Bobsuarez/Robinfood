package com.robinfood.core.dtos;

import com.robinfood.core.dtos.configuration.BrandDTO;
import com.robinfood.core.dtos.configuration.ChannelDTO;
import com.robinfood.core.dtos.configuration.CompanyDTO;
import com.robinfood.core.dtos.configuration.StoreWithIdAndNameDTO;
import com.robinfood.core.dtos.report.salebysegment.GetSaleBySegmentDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class SalesBuildAnswerDTO {

    private List<BrandDTO> brandDTOList;

    private List<ChannelDTO> channelDTOList;

    private List<CompanyDTO> companiesDTOList;

    private List<PaymentMethodsFilterDTO> paymentDTOList;

    private GetSaleBySegmentDTO saleSegmentDTO;

    private List<StoreWithIdAndNameDTO> storesDTOList;

}
