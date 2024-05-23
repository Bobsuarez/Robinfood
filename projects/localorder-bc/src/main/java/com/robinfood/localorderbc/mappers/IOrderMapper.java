package com.robinfood.localorderbc.mappers;

import com.robinfood.localorderbc.dtos.OrderDTO;
import com.robinfood.localorderbc.entities.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IOrderMapper {

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "deletedAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    OrderEntity orderDtoToOrderEntity(OrderDTO orderDTO);

    OrderDTO orderEntityToOrderDto(OrderEntity orderEntity);

}
