package com.robinfood.localserver.commons.mappers.electronicbill;

import com.robinfood.localserver.commons.dtos.electronicbill.ElectronicBillRequestDTO;
import com.robinfood.localserver.commons.dtos.electronicbillbcsaopaulo.SatHubResultDto;
import com.robinfood.localserver.commons.dtos.orders.OrderBuyerDTO;
import com.robinfood.localserver.commons.dtos.storeconfiguration.StoreDTO;

public final class ElectronicBillRequestDTOMapper {

    public static ElectronicBillRequestDTO getElectronicBillRequestMapper(SatHubResultDto satHubResultDto,
                                                                           OrderBuyerDTO orderBuyerDTO,
                                                                           StoreDTO storeDTO) {
        return ElectronicBillRequestDTO
                .builder()
                .cnpj(satHubResultDto.getCNPJ())
                .ie(satHubResultDto.getIE())
                .keyQuery(satHubResultDto.getKeyQuery())
                .qrCode(satHubResultDto.getQrCode())
                .queryKey(satHubResultDto.getQueryKey())
                .storeEmail(storeDTO.getEmail())
                .storePhone(storeDTO.getPhone())
                .satNumber(satHubResultDto.getSatNumber())
                .success(satHubResultDto.getSuccess())
                .buyerIdentifier(orderBuyerDTO.getIdentifier())
                .build();
    }

}
