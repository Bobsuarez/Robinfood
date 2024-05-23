package com.robinfood.usecases.geteventbyidandflow;

import com.robinfood.dtos.EventDTO;

/**
 * Get event by id and code flow use case
 */
public interface IGetEventByIdAndFlowUseCase {
    EventDTO invoke(String eventId, String flowCode, String orderUuid, String token);
}
