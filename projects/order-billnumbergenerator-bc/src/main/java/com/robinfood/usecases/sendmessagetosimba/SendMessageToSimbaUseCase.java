package com.robinfood.usecases.sendmessagetosimba;

import com.robinfood.dtos.sendordertosimba.request.TransactionRequestDTO;
import com.robinfood.repository.connectorsimba.ConnectorSimbaRepository;
import com.robinfood.repository.connectorsimba.IConnectorSimbaRepository;
import com.robinfood.util.LogsUtil;
import lombok.AllArgsConstructor;

import static com.robinfood.enums.AppLogsTraceEnum.INIT_CONNECTOR_SIMBA;
import static com.robinfood.enums.AppLogsTraceEnum.RESPONSE_CONNECTOR_SIMBA;

@AllArgsConstructor
public class SendMessageToSimbaUseCase implements ISendMessageToSimbaUseCase {

    private final IConnectorSimbaRepository connectorSimbaRepository;

    public SendMessageToSimbaUseCase() {
        this.connectorSimbaRepository = new ConnectorSimbaRepository();
    }

    @Override
    public String invoke(TransactionRequestDTO transactionRequestDTO, String token) {

        LogsUtil.info(
                INIT_CONNECTOR_SIMBA.getMessageWithCode()
        );

        final String responseSimba = connectorSimbaRepository.invoke(transactionRequestDTO, token);

        LogsUtil.info(
                RESPONSE_CONNECTOR_SIMBA.getMessageWithCode(),
                responseSimba
        );
        return responseSimba;
    }
}
