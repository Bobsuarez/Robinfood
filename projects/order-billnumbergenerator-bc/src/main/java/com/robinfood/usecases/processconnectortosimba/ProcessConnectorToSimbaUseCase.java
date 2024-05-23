package com.robinfood.usecases.processconnectortosimba;

import com.robinfood.dtos.sendordertosimba.request.OrderDTO;
import com.robinfood.dtos.sendordertosimba.request.TransactionRequestDTO;
import com.robinfood.dtos.sendordertosimba.response.TransactionResponseDTO;
import com.robinfood.entities.OrderElectronicBillingsEntity;
import com.robinfood.exceptions.ApplicationException;
import com.robinfood.mappers.ElectronicBillingRequestDTOToEntityMapper;
import com.robinfood.usecases.saveelectronicbillings.ISaveElectronicBillingsUseCase;
import com.robinfood.usecases.saveelectronicbillings.SaveElectronicBillingsUseCase;
import com.robinfood.usecases.savethirdparty.ISaveThirdPartyUseCase;
import com.robinfood.usecases.savethirdparty.SaveThirdPartyUseCase;
import com.robinfood.usecases.sendmessagetosimba.ISendMessageToSimbaUseCase;
import com.robinfood.usecases.sendmessagetosimba.SendMessageToSimbaUseCase;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;
import lombok.AllArgsConstructor;

import static com.robinfood.enums.AppLogsTraceEnum.DATA_INVOKE_USE_CASE;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_PROCESS_SEND_CONNECTOR_SIMBA;

@AllArgsConstructor
public class ProcessConnectorToSimbaUseCase implements IProcessConnectorToSimbaUseCase {

    private final ISaveElectronicBillingsUseCase saveElectronicBillingsUseCase;

    private final ISaveThirdPartyUseCase saveThirdPartyUseCase;

    private final ISendMessageToSimbaUseCase sendMessageToSimbaUseCase;

    public ProcessConnectorToSimbaUseCase() {
        this.saveElectronicBillingsUseCase = new SaveElectronicBillingsUseCase();
        this.saveThirdPartyUseCase = new SaveThirdPartyUseCase();
        this.sendMessageToSimbaUseCase = new SendMessageToSimbaUseCase();
    }

    @Override
    public TransactionResponseDTO invoke(TransactionRequestDTO transactionRequestDTO, String token) {

        LogsUtil.info(
                DATA_INVOKE_USE_CASE.getMessageWithCode(),
                ProcessConnectorToSimbaUseCase.class.getName(),
                TransactionResponseDTO.class.getName(),
                ObjectMapperSingleton.objectToJson(transactionRequestDTO)
        );

        OrderDTO orderDTO = transactionRequestDTO.getOrders().get(0);

        final OrderElectronicBillingsEntity electronicBillingEntity =
                ElectronicBillingRequestDTOToEntityMapper.buildToThirdPartyEntity(orderDTO, transactionRequestDTO);

        try {

            saveThirdPartyUseCase.invoke(orderDTO.getId(), orderDTO.getThirdParty());

            if(Boolean.FALSE.equals(transactionRequestDTO.getPaid())){
                return TransactionResponseDTO
                        .builder()
                        .fullName(orderDTO.getThirdParty().getFullName())
                        .build();
            }

            String responseDataConnector = sendMessageToSimbaUseCase.invoke(transactionRequestDTO, token);

            electronicBillingEntity.setResponse_payload(responseDataConnector);

            saveElectronicBillingsUseCase.invoke(electronicBillingEntity);

        } catch (ApplicationException applicationException) {

            LogsUtil.error(
                    ERROR_PROCESS_SEND_CONNECTOR_SIMBA.replaceComplement(applicationException.getMessage()),
                    ObjectMapperSingleton.objectToJson(applicationException.getApiGatewayResponseDTO())
            );

            electronicBillingEntity.setStatus_code(applicationException.getApiGatewayResponseDTO().getStatusCode());
            electronicBillingEntity.setResponse_payload(applicationException.getApiGatewayResponseDTO().getBody());
            saveElectronicBillingsUseCase.invoke(electronicBillingEntity);

            throw new ApplicationException(applicationException);
        }

        return TransactionResponseDTO.builder()
                .fullName(orderDTO.getThirdParty().getFullName())
                .orderUuid(orderDTO.getUuid())
                .build();
    }

}
