package com.robinfood.localorderbc.usecases.findallorderbystatusidusecase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.robinfood.localorderbc.dtos.OrderResponseDTO;

import java.util.List;

public interface IFindAllOrdersByStatusIdUseCase {
    List<OrderResponseDTO> invoke(Long statusId) throws JsonProcessingException;
}
