package com.robinfood.localprinterbc.mappers.orderprint;

import com.robinfood.localprinterbc.dtos.orders.OrderDetailDTO;
import com.robinfood.localprinterbc.dtos.decorator.OrderPrintDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IOrderPrintMapper {

    OrderPrintDTO orderDetailDTOToOrderPrintDTO(OrderDetailDTO orderDetailDTO);
}
