package com.robinfood.mappers;

import com.robinfood.constants.GeneralConstants;
import com.robinfood.constants.HttpStatusConstants;
import com.robinfood.dtos.sendordertosimba.request.OrderDTO;
import com.robinfood.dtos.sendordertosimba.request.TransactionRequestDTO;
import com.robinfood.entities.OrderElectronicBillingsEntity;

public class ElectronicBillingRequestDTOToEntityMapper {

    private ElectronicBillingRequestDTOToEntityMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static OrderElectronicBillingsEntity buildToThirdPartyEntity(
            OrderDTO orderDTO,
            TransactionRequestDTO requestBodySimba,
            Object responseBodySimba
    ) {
        return buildToThirdPartyEntity(
                orderDTO,
                responseBodySimba,
                requestBodySimba,
                HttpStatusConstants.SC_ACCEPTED.getCodeHttp()
        );
    }

    public static OrderElectronicBillingsEntity buildToThirdPartyEntity(
            OrderDTO orderDTO,
            TransactionRequestDTO requestBodySimba
    ) {
        return buildToThirdPartyEntity(
                orderDTO,
                GeneralConstants.DEFAULT_STRING_EMPTY,
                requestBodySimba,
                HttpStatusConstants.SC_ACCEPTED.getCodeHttp()
        );
    }

    public static OrderElectronicBillingsEntity buildToThirdPartyEntity(
            OrderDTO orderDTO,
            Object responseBodySimba,
            TransactionRequestDTO requestBodySimba,
            int statusCode
    ) {
        return OrderElectronicBillingsEntity.builder()
                .order_id(orderDTO.getId())
                .request_payload(requestBodySimba)
                .response_payload(responseBodySimba)
                .status_code(statusCode)
                .store_id(orderDTO.getStore().getId())
                .store_name(orderDTO.getStore().getName())
                .build();
    }
}
