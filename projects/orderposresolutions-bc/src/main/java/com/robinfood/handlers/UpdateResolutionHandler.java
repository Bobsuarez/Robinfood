package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.robinfood.dtos.v1.APIGatewayProxyRequestEvent;
import com.robinfood.dtos.v1.request.ResolutionUpdateDTO;
import com.robinfood.dtos.v1.response.ApiGatewayResponseDTO;
import com.robinfood.enums.AppLogsEnum;
import com.robinfood.exceptions.ApplicationException;
import com.robinfood.exceptions.NullFieldsValidationException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.usecases.updateresolutionsbyresolutionid.IUpdateResolutionsByResolutionIdUseCase;
import com.robinfood.usecases.updateresolutionsbyresolutionid.UpdateResolutionsByResolutionIdUseCase;
import com.robinfood.util.ObjectMapperSingletonUtil;
import com.robinfood.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.util.Objects;
import static com.robinfood.constants.GeneralConstants.AUTHORIZATION;
import static com.robinfood.constants.GeneralConstants.FIELDS_RESOLUTION_ID;
import static com.robinfood.constants.GeneralConstants.BODY_IS_NULL;
import static com.robinfood.constants.GeneralConstants.PATH_PARAMETER_RESOLUTION_ID_IS_NULL;

import static com.robinfood.enums.ErrorLogsEnum.ERROR_APP_EXCEPTION;

@Component
@Slf4j
public class UpdateResolutionHandler implements RequestHandler<APIGatewayProxyRequestEvent, ApiGatewayResponseDTO> {

    private final IUpdateResolutionsByResolutionIdUseCase updateResolutionsByResolutionIdUseCase;

    public UpdateResolutionHandler(IUpdateResolutionsByResolutionIdUseCase updateResolutionsByResolutionIdUseCase) {
        this.updateResolutionsByResolutionIdUseCase = updateResolutionsByResolutionIdUseCase;
    }
    public UpdateResolutionHandler(){
        updateResolutionsByResolutionIdUseCase = new UpdateResolutionsByResolutionIdUseCase();
    }

    @Override
    public ApiGatewayResponseDTO handleRequest(APIGatewayProxyRequestEvent event, Context context) {

        ApiGatewayResponseDTO apiGatewayResponseDTO;

        try {

            log.info(AppLogsEnum.PROGRAM_START.getMessageWithCode(), UpdateResolutionHandler.class.getName());

            String authorization = event.getHeaders().get(AUTHORIZATION);
            String resolutionId = event.getPathParameters().get(FIELDS_RESOLUTION_ID);
            String body = event.getBody();

            JwtMiddleware.validateToken(authorization);
            validatePathParameter(resolutionId);
            validateBody(body);

            log.info(AppLogsEnum.UPDATE_PARAMETERS_REQUEST.getMessageWithCode(), resolutionId, body);

            final ResolutionUpdateDTO resolutionDTO = parseRequestBody(event.getBody());

            ValidatorUtil.validate(resolutionDTO);

            updateResolutionsByResolutionIdUseCase.invoke(Long.valueOf(resolutionId), resolutionDTO);

            log.info(AppLogsEnum.UPDATE_RESOLUTION_SUCCESSFULLY.getMessage(), resolutionId);

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

    private static void validateBody(String body) {
        if(Objects.isNull(body)){
            throw new NullFieldsValidationException(ResponseMapper
                    .buildWithError(HttpStatus.SC_BAD_REQUEST,
                            BODY_IS_NULL,
                            Boolean.TRUE
                    ), BODY_IS_NULL);
        }
    }

    private static void validatePathParameter(String resolutionId) {
        if(Objects.isNull(resolutionId)){
            throw new NullFieldsValidationException(ResponseMapper
                    .buildWithError(HttpStatus.SC_BAD_REQUEST,
                            PATH_PARAMETER_RESOLUTION_ID_IS_NULL,
                            Boolean.TRUE
                    ), PATH_PARAMETER_RESOLUTION_ID_IS_NULL);
        }
    }

    private static ResolutionUpdateDTO parseRequestBody(String body) {
        return ObjectMapperSingletonUtil.jsonToClass(body, ResolutionUpdateDTO.class);
    }
}

