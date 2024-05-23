package com.robinfood.app.mocks.bysegment;

import com.robinfood.app.mocks.ConfigChannelsDTOMock;
import com.robinfood.app.mocks.configurations.CompaniesDTOMock;
import com.robinfood.app.mocks.StoreWithIdAndNameDTOMock;
import com.robinfood.app.mocks.configurations.BrandDTOMock;
import com.robinfood.core.dtos.SalesBuildAnswerDTO;

import java.util.List;

public class SalesBuildAnswerDTOMock {

    public static SalesBuildAnswerDTO getDefault() {
        return SalesBuildAnswerDTO.builder()
                .brandDTOList(List.of(BrandDTOMock.getDataDefault()))
                .companiesDTOList(CompaniesDTOMock.getDataDefault().getCompanies())
                .channelDTOList(ConfigChannelsDTOMock.getDataDefault().getContent())
                .storesDTOList(List.of(StoreWithIdAndNameDTOMock.getDataDefault()))
                .saleSegmentDTO(GetSaleBySegmentDTOMock.getDataDefault())
                .paymentDTOList(List.of(PaymentMethodDTOMock.getDataDefault()))
                .build();
    }
}
