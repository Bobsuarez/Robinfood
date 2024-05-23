package com.robinfood.core.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_STRING_VALUE;

@Data
@Getter
@RequiredArgsConstructor
@Setter
public class OrderHistoryItemDTO {

    private final String brandName;

    private final String operationDate;

    private String customerName = DEFAULT_STRING_VALUE;

    @JsonIgnore
    private final Long deliveryTypeId;

    private final Long id;

    private String invoiceNumber = DEFAULT_STRING_VALUE;

    private final String orderNumber;

    private final String originName;

    private String status = DEFAULT_STRING_VALUE;

    @JsonIgnore
    private final Long statusId;

    private final Double totalPrice;

}
