package com.robinfood.usecases.saveeventhistory;

import com.robinfood.entities.EventHistoryEntity;
import com.robinfood.enums.AppLogsTraceEnum;
import com.robinfood.repository.eventhistory.EventHistoryRepository;
import com.robinfood.repository.eventhistory.IEventHistoryRepository;
import com.robinfood.utils.LogsUtil;
import com.robinfood.utils.ObjectMapperSingleton;

public class SaveEventHistoryUseCase implements ISaveEventHistoryUseCase {

    private final IEventHistoryRepository eventHistoryRepository;

    public SaveEventHistoryUseCase() {
        this.eventHistoryRepository = new EventHistoryRepository();
    }

    public SaveEventHistoryUseCase(
            IEventHistoryRepository eventHistoryRepository
    ) {
        this.eventHistoryRepository = eventHistoryRepository;
    }

    @Override
    public void invoke(
            Long eventDispatched,
            Long flowEventLogId,
            Long subscriberId,
            String token,
            String uuid
    ) {

        final EventHistoryEntity eventHistoryEntity = EventHistoryEntity.builder()
                .eventDispatched(eventDispatched)
                .flowEventLogId(flowEventLogId)
                .subscriberId(subscriberId)
                .uuid(uuid)
                .build();

        LogsUtil.info(AppLogsTraceEnum.INITIAL_PROCESS_EVENT_HISTORY.getMessage(),
                ObjectMapperSingleton.objectToJson(eventHistoryEntity)
        );

        eventHistoryRepository.saveEventHistory(eventHistoryEntity, token);
    }
}
