package com.robinfood.core.dtos.response.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.robinfood.core.dtos.OrderCouponDTO;
import com.robinfood.core.dtos.response.order.ResponseCreatedOrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ResponseCreatedOrderTransactionDTO {

    public final LocalDateTime createdAt;

    public final Long id;

    public final List<ResponseCreatedOrderDTO> orders;

    public final String uuid;
}
