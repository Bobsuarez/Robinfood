package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.robinfood.dtos.v1.APIGatewayProxyRequestEvent;
import com.robinfood.dtos.v1.response.ApiGatewayResponseDTO;
import com.robinfood.enums.AppLogsEnum;
import com.robinfood.exceptions.ApplicationException;
import com.robinfood.exceptions.NullFieldsValidationException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.usecases.updateresolutionsbyposid.IUpdateResolutionsByPosIdUseCase;
import com.robinfood.usecases.updateresolutionsbyposid.UpdateResolutionsByPosIdUseCase;
import com.robinfood.util.ObjectMapperSingletonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.robinfood.constants.GeneralConstants.AUTHORIZATION;
import static com.robinfood.constants.GeneralConstants.FIELDS_POS_ID;
import static com.robinfood.constants.GeneralConstants.FIELDS_ID;
import static com.robinfood.constants.GeneralConstants.PATH_PARAMETER_POS_ID_IS_NULL;
import static com.robinfood.constants.GeneralConstants.PATH_PARAMETER_RESOLUTION_ID_IS_NULL;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_APP_EXCEPTION;

@Component
@Slf4j
public class UpdateResolutionWithPosHandler  implements
        RequestHandler<APIGatewayProxyRequestEvent, ApiGatewayResponseDTO> {

    private final IUpdateResolutionsByPosIdUseCase updateResolutionsByPosIdUseCase;

    public UpdateResolutionWithPosHandler(IUpdateResolutionsByPosIdUseCase updateResolutionsByPosIdUseCase) {
        this.updateResolutionsByPosIdUseCase = updateResolutionsByPosIdUseCase;
    }

    public UpdateResolutionWithPosHandler() {
        this.updateResolutionsByPosIdUseCase = new UpdateResolutionsByPosIdUseCase();
    }

    @Override
    public ApiGatewayResponseDTO handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        ApiGatewayResponseDTO apiGatewayResponseDTO;

        try {

            log.info(AppLogsEnum.PROGRAM_START.getMessageWithCode(), UpdateResolutionWithPosHandler.class.getName());

            String authorization = event.getHeaders().get(AUTHORIZATION);
            String id = event.getPathParameters().get(FIELDS_ID);
            String posId = event.getPathParameters().get(FIELDS_POS_ID);

            JwtMiddleware.validateToken(authorization);

            validatePathParameter(id, posId);

            log.info(
                    AppLogsEnum.UPDATE_PARAMETERS_RESOLUTION_AND_POS_REQUEST.getMessageWithCode(),
                    id,
                    posId);

            updateResolutionsByPosIdUseCase.invoke(Long.valueOf(id), Long.valueOf(posId));

            log.info(AppLogsEnum.UPDATE_RESOLUTION_SUCCESSFULLY.getMessage(), id);

            apiGatewayResponseDTO =  ApiGatewayResponseDTO.builder()
                    .setStatusCode(HttpStatus.SC_ACCEPTED)
                    .setObjectBody(ResponseMapper
                            .buildDefault(HttpStatus.SC_ACCEPTED,
                                    null,
                                    AppLogsEnum.UPDATE_SUCCESSFULLY.getMessage()))
                    .build();

        } catch (ApplicationException e){
            log.error(
                    ERROR_APP_EXCEPTION.getMessageWithCode(), e,
                    e.getStackTrace(),
                    ObjectMapperSingletonUtil.objectToJson(e.getApiGatewayResponseDTO())
            );

            apiGatewayResponseDTO = e.getApiGatewayResponseDTO();
        }
        return apiGatewayResponseDTO;
    }

    private static void validatePathParameter(String id, String posId) {
        if(Objects.isNull(id)){
            throw new NullFieldsValidationException(ResponseMapper
                    .buildWithError(HttpStatus.SC_BAD_REQUEST,
                            PATH_PARAMETER_RESOLUTION_ID_IS_NULL,
                            Boolean.TRUE
                    ), PATH_PARAMETER_RESOLUTION_ID_IS_NULL);
        }
        if(Objects.isNull(posId)){
            throw new NullFieldsValidationException(ResponseMapper
                    .buildWithError(HttpStatus.SC_BAD_REQUEST,
                            PATH_PARAMETER_POS_ID_IS_NULL,
                            Boolean.TRUE
                    ), PATH_PARAMETER_POS_ID_IS_NULL);
        }
    }

}
