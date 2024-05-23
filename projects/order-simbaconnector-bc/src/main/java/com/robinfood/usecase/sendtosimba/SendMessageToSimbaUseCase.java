package com.robinfood.usecase.sendtosimba;

import com.fasterxml.jackson.databind.JsonNode;
import com.robinfood.constants.HttpStatusConstants;
import com.robinfood.dtos.request.orderbill.TransactionRequestDTO;
import com.robinfood.dtos.request.simba.SimbaContractDTO;
import com.robinfood.dtos.response.OrderElectronicBillingDTO;
import com.robinfood.exceptions.ApiClientsException;
import com.robinfood.exceptions.ApplicationException;
import com.robinfood.repository.connectorsimba.ConnectorSimbaRepository;
import com.robinfood.repository.connectorsimba.IConnectorSimbaRepository;
import com.robinfood.usecase.buildcontratsimba.BuildContractSimbaUseCase;
import com.robinfood.usecase.buildcontratsimba.IBuildContractSimbaUseCase;
import com.robinfood.util.LogsUtil;
import com.robinfood.util.ObjectMapperSingleton;
import com.robinfood.util.ValidateNodeUtil;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static com.robinfood.constants.GeneralConstants.DATOS_BASICOS;
import static com.robinfood.constants.GeneralConstants.DOC_ELECTRONICO_ESTENDIDO;
import static com.robinfood.constants.GeneralConstants.RESPUESTA_UNITARIA;
import static com.robinfood.constants.GeneralConstants.URL_QR;
import static com.robinfood.constants.simba.TercerosConstants.NIT;
import static com.robinfood.enums.AppLogsTraceEnum.DATA_TO_SEND_TO_SIMBA;
import static com.robinfood.enums.AppLogsTraceEnum.INIT_CONNECTOR_SIMBA;
import static com.robinfood.enums.AppLogsTraceEnum.RESPONSE_CONNECTOR_SIMBA;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_APP_EXCEPTION;

public class SendMessageToSimbaUseCase implements ISendMessageToSimbaUseCase {

    private final IConnectorSimbaRepository connectorSimbaRepository;
    private final IBuildContractSimbaUseCase buildContractSimbaUseCase;

    public SendMessageToSimbaUseCase() {
        this.buildContractSimbaUseCase = new BuildContractSimbaUseCase();
        this.connectorSimbaRepository = new ConnectorSimbaRepository();
    }

    public SendMessageToSimbaUseCase(IConnectorSimbaRepository connectorSimbaRepository,
                                     IBuildContractSimbaUseCase buildContractSimbaUseCase) {
        this.connectorSimbaRepository = connectorSimbaRepository;
        this.buildContractSimbaUseCase = buildContractSimbaUseCase;
    }

    @Override
    public OrderElectronicBillingDTO invoke(TransactionRequestDTO transactionRequestDTO) {

        try {
            LogsUtil.info(
                    INIT_CONNECTOR_SIMBA.getMessageWithCode()
            );

            final SimbaContractDTO simbaContractDTO = buildContractSimbaUseCase.invoke(transactionRequestDTO);

            LogsUtil.info(
                    DATA_TO_SEND_TO_SIMBA.getMessageWithCode(),
                    ObjectMapperSingleton.objectToJson(simbaContractDTO)
            );

            final JsonNode responseSimba = connectorSimbaRepository.invoke(simbaContractDTO);

            LogsUtil.info(
                    RESPONSE_CONNECTOR_SIMBA.getMessageWithCode(),
                    responseSimba
            );

            return buildResponseSimba(responseSimba);

        } catch (ApplicationException a) {

            LogsUtil.error(
                    ERROR_APP_EXCEPTION.getMessageWithCode(), a.toString(), Arrays.toString(a.getStackTrace()),
                    ObjectMapperSingleton.objectToJson(a.getMessage())
            );

            throw new ApiClientsException(a);

        } catch (RuntimeException exception) {

            LogsUtil.error(exception.getMessage());
            throw new ApiClientsException(exception);
        }

    }

    private OrderElectronicBillingDTO buildResponseSimba(JsonNode jsonNodeResponseSimba) {

        ValidateNodeUtil.validateNode(jsonNodeResponseSimba);

        JsonNode respuestaUnitaria = jsonNodeResponseSimba.path(RESPUESTA_UNITARIA);
        JsonNode docElectronicoExtendido = respuestaUnitaria.path(DOC_ELECTRONICO_ESTENDIDO);
        JsonNode datosBasicos = docElectronicoExtendido.path(DATOS_BASICOS);

        String cude = datosBasicos.path("CUDE").asText("");

        return OrderElectronicBillingDTO.builder()
                .cufe(cude)
                .fechaYHoraEmision(datosBasicos.path("FechaYHoraEmision").asText(null))
                .fechaYHoraEmisionSpecified(datosBasicos.path("FechaYHoraEmisionSpecified").asBoolean(Boolean.FALSE))
                .qr(URL_QR.concat(cude))
                .nitEmisor(datosBasicos.path("NitEmisor").asText(null))
                .numero(datosBasicos.path("Numero").asText(null))
                .prefijo(datosBasicos.path("Prefijo").asText(null))
                .payload(jsonNodeResponseSimba)
                .statusCode(getStatusCodeResponseSimba(respuestaUnitaria))
                .tipoDocumento(NIT)
                .build();
    }

    private int getStatusCodeResponseSimba(JsonNode respuestaUnitaria) {

        int codeStatus = HttpStatusConstants.SC_OK.getCodeHttp();
        JsonNode encabezadoRespuesta = respuestaUnitaria.path("EncabezadoRespuesta");
        Boolean solicitudAceptada = encabezadoRespuesta.path("SolicitudAceptada").asBoolean(Boolean.FALSE);
        Boolean isEmisible = validatedValueIssuable(encabezadoRespuesta);

        if (solicitudAceptada.equals(Boolean.FALSE) || isEmisible.equals(Boolean.FALSE)) {
            codeStatus = HttpStatusConstants.SC_INTERNAL_SERVER_ERROR.getCodeHttp();
        }

        return codeStatus;
    }

    private boolean validatedValueIssuable(JsonNode nodeMain) {

        JsonNode processIndicator = nodeMain
                .path("EstadosGenerales")
                .path("IndicadorProceso");

        Optional<JsonNode> indicatorEmisibleNode = StreamSupport
                .stream(processIndicator.spliterator(), false)
                .filter(indicator -> "IND_EMISIBLE".equals(indicator.path("Nombre").asText()))
                .findFirst();

        return indicatorEmisibleNode
                .map(indicator -> indicator.path("Value").asBoolean())
                .orElse(false);
    }
}
