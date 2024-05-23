package com.robinfood.localserver.commons.mappers.orders;

import com.robinfood.localserver.commons.dtos.orders.OrderDetailDTO;
import com.robinfood.localserver.commons.dtos.searchallorders.OrderResponseDTO;
import com.robinfood.localserver.commons.entities.orders.OrderDetailEntity;
import com.robinfood.localserver.commons.entities.orders.OrderRequestEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IOrderDetailMapper {

    OrderDetailEntity orderDetailDTOToOrderDetailEntity(OrderDetailDTO orderDetailDTO);

    OrderDetailDTO orderDetailEntityToOrderDetailDTO(OrderDetailEntity orderDetailEntity);

    List<OrderResponseDTO> entityDTOToOrderLocalResponseDTO(
            List<OrderRequestEntity>listOrderResponseEntity);
}
