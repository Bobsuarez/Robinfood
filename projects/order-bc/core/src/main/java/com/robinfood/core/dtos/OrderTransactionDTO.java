package com.robinfood.core.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OrderTransactionDTO {

    private final CompanyDTO company;

    private final DeviceDTO device;

    private final List<DiscountDTO> discounts;

    private final List<OrderDTO> orders;

    private final Boolean paid;

    private final Double total;

    private final List<TransactionPaymentDTO> transactionPaymentDTOList;

    private final UserDataDTO user;
}
