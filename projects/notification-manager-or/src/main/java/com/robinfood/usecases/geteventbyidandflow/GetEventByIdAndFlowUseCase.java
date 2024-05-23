package com.robinfood.usecases.geteventbyidandflow;

import com.robinfood.dtos.EventDTO;
import com.robinfood.entities.EventEntity;
import com.robinfood.repository.event.EventRepository;
import com.robinfood.repository.event.IEventRepository;
import com.robinfood.utils.LogsUtil;
import com.robinfood.utils.ObjectMapperSingleton;

public class GetEventByIdAndFlowUseCase implements IGetEventByIdAndFlowUseCase {

    private final IEventRepository eventRepository;

    public GetEventByIdAndFlowUseCase(
            IEventRepository eventRepository
    ) {
        this.eventRepository = eventRepository;
    }

    public GetEventByIdAndFlowUseCase() {
        eventRepository = new EventRepository();
    }

    @Override
    public EventDTO invoke(String eventId, String flowCode, String orderUuid, String token) {

        final EventEntity eventEntity = eventRepository.getEventByIdAndFlow(eventId, flowCode, orderUuid, token);

        LogsUtil.info("Response event repository get event by id and flow {}", eventEntity);

        return ObjectMapperSingleton.objectToClassConvertValue(eventEntity, EventDTO.class);
    }
}
