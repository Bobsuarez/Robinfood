package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.robinfood.dtos.v1.APIGatewayProxyRequestEvent;
import com.robinfood.dtos.v1.request.EnabledDisabledResolutionDTO;
import com.robinfood.dtos.v1.response.ApiGatewayResponseDTO;
import com.robinfood.enums.AppLogsEnum;
import com.robinfood.exceptions.ApplicationException;
import com.robinfood.exceptions.BusinessRuleException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.usecases.enabledisableresolution.EnabledDisabledResolutionUseCase;
import com.robinfood.usecases.enabledisableresolution.IEnabledDisabledResolutionUseCase;
import com.robinfood.util.ObjectMapperSingletonUtil;
import com.robinfood.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.robinfood.constants.GeneralConstants.AUTHORIZATION;
import static com.robinfood.constants.GeneralConstants.FIELDS_RESOLUTION_ID;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_APP_EXCEPTION;

@Component
@Slf4j
public class EnabledDisabledResolutionHandler implements RequestHandler<APIGatewayProxyRequestEvent,
        ApiGatewayResponseDTO> {

    private final IEnabledDisabledResolutionUseCase alternateResolutionUseCase;

    public EnabledDisabledResolutionHandler(IEnabledDisabledResolutionUseCase alternateResolutionUseCase) {
        this.alternateResolutionUseCase = alternateResolutionUseCase;
    }

    public EnabledDisabledResolutionHandler() {
        this.alternateResolutionUseCase = new EnabledDisabledResolutionUseCase();
    }

    @Override
    public ApiGatewayResponseDTO handleRequest(APIGatewayProxyRequestEvent requestEvent, Context context) {

        ApiGatewayResponseDTO apiGatewayResponseDTO;

        try {

            final String authorization = requestEvent.getHeaders().get(AUTHORIZATION);
            JwtMiddleware.validateToken(authorization);

            log.info(AppLogsEnum.PROGRAM_START.getMessageWithCode(), EnabledDisabledResolutionHandler.class.getName());

            final EnabledDisabledResolutionDTO requestDTO = parseRequestBody(requestEvent.getBody());
            final String resolutionIdPath = requestEvent.getPathParameters().get(FIELDS_RESOLUTION_ID);

            ValidatorUtil.validate(requestDTO);

            log.info(
                    AppLogsEnum.UPDATE_RESOLUTION_DATA.getMessageWithCode(),
                    ObjectMapperSingletonUtil.objectToJson(requestDTO),
                    resolutionIdPath
            );

            final String result = alternateResolutionUseCase.invoke(requestDTO, resolutionIdPath);

            apiGatewayResponseDTO = ApiGatewayResponseDTO.builder()
                    .setStatusCode(HttpStatus.SC_ACCEPTED)
                    .setObjectBody(ResponseMapper
                            .buildDefault(HttpStatus.SC_ACCEPTED,
                                    result,
                                    AppLogsEnum.UPDATE_RESOLUTION_SUCCESSFULLY.getMessage()))
                    .build();

            log.info(
                    AppLogsEnum.RESPONSE_DATA_OK.getMessageWithCode(),
                    ObjectMapperSingletonUtil.objectToJson(apiGatewayResponseDTO)
            );

        } catch (ApplicationException applicationException) {

            log.error(
                    ERROR_APP_EXCEPTION.getMessageWithCode(), applicationException,
                    applicationException.getStackTrace(),
                    ObjectMapperSingletonUtil.objectToJson(applicationException.getApiGatewayResponseDTO())
            );

            apiGatewayResponseDTO = applicationException.getApiGatewayResponseDTO();
        }
        return apiGatewayResponseDTO;
    }

    private EnabledDisabledResolutionDTO parseRequestBody(String body) {

        if (Objects.isNull(body)) {
            throw new BusinessRuleException(
                    HttpStatus.SC_NOT_FOUND,
                    "The service requires a value for resolution change");
        }

        return ObjectMapperSingletonUtil.jsonToClass(body, EnabledDisabledResolutionDTO.class);
    }
}
