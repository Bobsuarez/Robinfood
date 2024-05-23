package com.robinfood.usecases.saveevent;

import com.robinfood.dtos.ChangeStatusDTO;
import com.robinfood.dtos.EventDTO;
import com.robinfood.entities.EventEntity;
import com.robinfood.enums.AppLogsTraceEnum;
import com.robinfood.repository.event.EventRepository;
import com.robinfood.repository.event.IEventRepository;
import com.robinfood.utils.LogsUtil;
import com.robinfood.utils.ObjectMapperSingleton;

import static com.robinfood.constants.Constants.CHANGE_STATUS_ID;


public class SaveEventUseCase implements ISaveEventUseCase {

    private final IEventRepository eventRepository;

    public SaveEventUseCase() {
        this.eventRepository = new EventRepository();
    }

    public SaveEventUseCase(IEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public EventDTO invoke(ChangeStatusDTO changeStatusDTO, String eventId, String token) {

        final String payloadDataFormatJson = ObjectMapperSingleton.objectToJson(changeStatusDTO);

        final EventEntity eventEntityBuilder = EventEntity.builder()
                .eventId(eventId)
                .payload(payloadDataFormatJson)
                .flowId(CHANGE_STATUS_ID)
                .uuid(changeStatusDTO.getOrderUuid())
                .build();

        LogsUtil.info(
                AppLogsTraceEnum.INITIAL_SAVE_EVENT.getMessage()
        );

        final EventEntity eventEntityResponse = eventRepository.saveEvent(eventEntityBuilder, token);

        LogsUtil.info(
                AppLogsTraceEnum.FINAL_SAVE_EVENT.getMessage(),eventEntityResponse.getId(),
                eventEntityResponse.getEventId()
        );

        return ObjectMapperSingleton.objectToClassConvertValue(eventEntityResponse, EventDTO.class);
    }
}
