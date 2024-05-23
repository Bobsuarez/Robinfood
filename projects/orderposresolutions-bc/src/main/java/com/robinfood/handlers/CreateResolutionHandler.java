package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.robinfood.dtos.v1.APIGatewayProxyRequestEvent;
import com.robinfood.dtos.v1.request.StoreResolutionDTO;
import com.robinfood.dtos.v1.response.ApiGatewayResponseDTO;
import com.robinfood.enums.AppLogsEnum;
import com.robinfood.exceptions.ApplicationException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.usecases.saveresolutionsbystore.ISaveResolutionsByStoreUseCase;
import com.robinfood.usecases.saveresolutionsbystore.SaveResolutionsByStoreUseCase;
import com.robinfood.util.ObjectMapperSingletonUtil;
import com.robinfood.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.robinfood.constants.GeneralConstants.AUTHORIZATION;
import static com.robinfood.enums.AppLogsEnum.CREATED_RESOLUTION_SUCCESSFULLY;
import static com.robinfood.enums.AppLogsEnum.DATA_RESPONSE_REPOSITORY;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_APP_EXCEPTION;

@Component
@Slf4j
public class CreateResolutionHandler implements RequestHandler<APIGatewayProxyRequestEvent, ApiGatewayResponseDTO> {

    private final ISaveResolutionsByStoreUseCase saveResolutionsByStoreUseCase;

    public CreateResolutionHandler(ISaveResolutionsByStoreUseCase saveResolutionsByStoreUseCase) {
        this.saveResolutionsByStoreUseCase = saveResolutionsByStoreUseCase;
    }

    public CreateResolutionHandler() {
        this.saveResolutionsByStoreUseCase = new SaveResolutionsByStoreUseCase();
    }

    private static StoreResolutionDTO parseRequestBody(String body) {
        return ObjectMapperSingletonUtil.jsonToClass(body, StoreResolutionDTO.class);
    }

    @Override
    public ApiGatewayResponseDTO handleRequest(APIGatewayProxyRequestEvent requestEvent, Context context) {

        ApiGatewayResponseDTO apiGatewayResponseDTO;

        try {
            String authorization = requestEvent.getHeaders().get(AUTHORIZATION);
            JwtMiddleware.validateToken(authorization);

            log.info(AppLogsEnum.PROGRAM_START.getMessageWithCode(), CreateResolutionHandler.class.getName());

            final StoreResolutionDTO requestDTO = parseRequestBody(requestEvent.getBody());

            ValidatorUtil.validate(requestDTO);

            log.info(
                    AppLogsEnum.CREATE_RESOLUTION_DATA.getMessageWithCode(),
                    requestDTO.getStoreId(),
                    ObjectMapperSingletonUtil.objectToJson(requestDTO)
            );

            saveResolutionsByStoreUseCase.invoke(requestDTO);

            log.info(DATA_RESPONSE_REPOSITORY.getMessageWithCode(), CREATED_RESOLUTION_SUCCESSFULLY.getMessage());

            apiGatewayResponseDTO = ApiGatewayResponseDTO.builder()
                    .setStatusCode(HttpStatus.SC_CREATED)
                    .setObjectBody(ResponseMapper
                            .buildDefault(HttpStatus.SC_CREATED,
                                    null,
                                    AppLogsEnum.CREATED_RESOLUTION_SUCCESSFULLY.getMessage()))
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
}
