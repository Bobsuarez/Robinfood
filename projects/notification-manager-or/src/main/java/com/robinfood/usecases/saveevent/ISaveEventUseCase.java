package com.robinfood.usecases.saveevent;

import com.robinfood.dtos.ChangeStatusDTO;
import com.robinfood.dtos.EventDTO;

/**
 * Save event received to replicate use case
 */
public interface ISaveEventUseCase {

    EventDTO invoke(ChangeStatusDTO changeStatusDTO, String eventId, String token);
}
