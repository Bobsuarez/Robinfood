package com.robinfood.usecases.createsubscribereventhistorylogs;

import com.robinfood.dtos.createsubscribereventhistorylogs.request.SubscriberEventHistoryLogsRequestDTO;
import com.robinfood.dtos.createsubscribereventhistorylogs.response.SubscriberEventHistoryLogsResponseDTO;
import com.robinfood.entities.SubscriberEventHistoryLogsEntity;
import com.robinfood.mappers.SubscriberEventHistoryLogsToDTOMapper;
import com.robinfood.mappers.request.SubscriberEventHistoryLogsRequestDTOToEntityMapper;
import com.robinfood.repository.subscribereventhistorylogs.ISubscriberEventHistoryLogsRepository;
import com.robinfood.repository.subscribereventhistorylogs.SubscriberEventHistoryLogsRepository;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;
import lombok.AllArgsConstructor;

import java.math.BigInteger;

import static com.robinfood.constants.GeneralConstants.DEFAULT_INTEGER;
import static com.robinfood.enums.AppLogsEnum.SEND_INFORMATION_EVENT_FLOWS;
import static com.robinfood.enums.AppLogsEnum.START_INFORMATION_EVENT_FLOWS;

@AllArgsConstructor
public class CreateSubscriberEventHistoryLogsUseCase implements ICreateSubscriberEventHistoryLogsUseCase {

    private final ISubscriberEventHistoryLogsRepository subscriberEventHistoryLogsRepository;

    public CreateSubscriberEventHistoryLogsUseCase() {
        this.subscriberEventHistoryLogsRepository = SubscriberEventHistoryLogsRepository.getInstance();
    }

    @Override
    public SubscriberEventHistoryLogsResponseDTO invoke(SubscriberEventHistoryLogsRequestDTO requestDTO) {

        LogsUtil.info(START_INFORMATION_EVENT_FLOWS.getMessageWithCode(), requestDTO);

        final SubscriberEventHistoryLogsEntity entity = SubscriberEventHistoryLogsRequestDTOToEntityMapper
                .buildToSubscriberEventHistoryLogsEntity(requestDTO);

        final BigInteger getIdInserted = subscriberEventHistoryLogsRepository.save(entity);

        if (getIdInserted.compareTo(BigInteger.ZERO) < DEFAULT_INTEGER) {
            return SubscriberEventHistoryLogsResponseDTO.builder().build();
        }

        final SubscriberEventHistoryLogsEntity saveEntity = subscriberEventHistoryLogsRepository.searchById(
                getIdInserted.longValue()
        );

        LogsUtil.info(
                SEND_INFORMATION_EVENT_FLOWS.getMessageWithCode(),
                ObjectMapperSingleton.objectToJson(
                        saveEntity
                )
        );

        return SubscriberEventHistoryLogsToDTOMapper.buildToSubscriberEventHistoryLogsResponseDTO(
                saveEntity,
                requestDTO.getUuid()
        );
    }
}
