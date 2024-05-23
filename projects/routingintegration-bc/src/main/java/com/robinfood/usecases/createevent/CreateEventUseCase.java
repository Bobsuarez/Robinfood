package com.robinfood.usecases.createevent;

import com.robinfood.dtos.createeventflow.request.EventRequestDTO;
import com.robinfood.dtos.createeventflow.response.FlowEventLogsResponseDTO;
import com.robinfood.entities.FlowEventLogsEntity;
import com.robinfood.mappers.EntityFlowEventLogsToDTOMapper;
import com.robinfood.mappers.request.EventRequestDTOToEntityMapper;
import com.robinfood.repository.floweventlogs.FlowEventLogsRepository;
import com.robinfood.repository.floweventlogs.IFlowEventLogsRepository;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;
import lombok.AllArgsConstructor;

import java.math.BigInteger;

import static com.robinfood.constants.GeneralConstants.DEFAULT_INTEGER;
import static com.robinfood.enums.AppLogsEnum.RESPONSE_OBTAINED_SAVING;
import static com.robinfood.enums.AppLogsEnum.SEND_INFORMATION_FLOWS;
import static com.robinfood.enums.AppLogsEnum.START_INFORMATION_FLOWS;

@AllArgsConstructor
public class CreateEventUseCase implements ICreateEventUseCase {

    private final IFlowEventLogsRepository flowEventLogsRepository;

    public CreateEventUseCase() {
        flowEventLogsRepository = FlowEventLogsRepository.getInstance();
    }

    @Override
    public FlowEventLogsResponseDTO invoke(EventRequestDTO eventRequestDTO) {

        LogsUtil.info(
                START_INFORMATION_FLOWS.getMessageWithCode(),
                eventRequestDTO.getUuid(),
                eventRequestDTO
        );

        final FlowEventLogsEntity eventLogsEntity = EventRequestDTOToEntityMapper
                .buildToFlowEventLogsEntity(eventRequestDTO);

        LogsUtil.info(SEND_INFORMATION_FLOWS.getMessageWithCode(), ObjectMapperSingleton.objectToJson(eventLogsEntity));

        final BigInteger getIdInserted = flowEventLogsRepository.save(eventLogsEntity);

        if (getIdInserted.compareTo(BigInteger.ZERO) < DEFAULT_INTEGER) {
            return FlowEventLogsResponseDTO.builder().build();
        }

        final FlowEventLogsEntity saveEventLogsEntity = flowEventLogsRepository.searchByEventIdAndFlowId(
                eventRequestDTO.getEventId(),
                eventRequestDTO.getFlowId()
        );

        LogsUtil.info(
                RESPONSE_OBTAINED_SAVING.getMessageWithCode(), ObjectMapperSingleton.objectToJson(saveEventLogsEntity)
        );

        return EntityFlowEventLogsToDTOMapper.buildToFlowEventLogsDTO(
                saveEventLogsEntity, eventRequestDTO.getUuid()
        );
    }

}
