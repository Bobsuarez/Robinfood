package com.robinfood.localserver.commons.mappers.orderbilling;

import com.robinfood.localserver.commons.dtos.orders.OrderDetailDTO;
import com.robinfood.localserver.commons.dtos.orders.billing.OrderBillingDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IOrderDetailEntityOrderBillingDtoMapper {

    OrderBillingDTO orderDetailEntityToOrderBillingDTO(OrderDetailDTO orderDetailDTO);

}
