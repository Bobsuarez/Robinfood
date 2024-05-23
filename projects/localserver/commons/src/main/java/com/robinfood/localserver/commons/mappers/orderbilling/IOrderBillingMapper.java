package com.robinfood.localserver.commons.mappers.orderbilling;

import com.robinfood.localserver.commons.dtos.orders.billing.OrderBillingDTO;
import com.robinfood.localserver.commons.entities.orders.billing.OrderBillingEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IOrderBillingMapper {

    OrderBillingDTO orderBillingEntityToOrderBillingDTO(OrderBillingEntity orderBillingEntity);

    OrderBillingEntity orderBillingDTOToOrderBillingEntity(OrderBillingDTO orderBillingDTO);
}
