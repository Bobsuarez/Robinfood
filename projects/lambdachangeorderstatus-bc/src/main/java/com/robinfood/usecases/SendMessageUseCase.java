package com.robinfood.usecases;

import com.robinfood.dtos.request.RequestChangeStateDTO;
import com.robinfood.enums.StatusEnum;
import com.robinfood.queues.publisher.ChangeStatusOrderPublisher;
import com.robinfood.utils.LogsUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.robinfood.constants.APIConstants.PILOT_STORES;
import static com.robinfood.constants.Constants.COMMA;
import static com.robinfood.constants.Constants.SPACE;

public class SendMessageUseCase implements ISendMessageUseCase {

    private static final String REGEX = COMMA + SPACE;

    private static final Pattern PILOT_STORES_PATTERN = Pattern.compile(REGEX);

    @Override
    public void invoke(RequestChangeStateDTO requestDTO, Long storeId) {

        LogsUtil.info("..:: PILOT STORES ::.. %s ", PILOT_STORES);

        List<Long> storesPilot = getStoresPilotsStack();

        LogsUtil.info("The store id obtained %s ", storeId);

        if (Objects.isNull(storeId) || !storesPilot.contains(storeId)) {
            sendMessages(requestDTO);
            return;
        }

        Arrays.stream(StatusEnum.values())
                .forEach((StatusEnum status) -> {
                    requestDTO.setStatusCode(status.name());
                    requestDTO.setStatusId(status.getStatusId());

                    LogsUtil.info("Sent statusId %s with --> statusCode %s",
                            requestDTO.getStatusId(),
                            requestDTO.getStatusCode());

                    sendMessages(requestDTO);
                });
    }

    public void sendMessages(RequestChangeStateDTO requestDTO) {
        ChangeStatusOrderPublisher.sendMessage(
                requestDTO
        );
    }

    List<Long> getStoresPilotsStack() {
        return Arrays.stream(PILOT_STORES_PATTERN.split(PILOT_STORES))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }
}