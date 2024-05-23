package com.robinfood.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.robinfood.dtos.v1.APIGatewayProxyRequestEvent;
import com.robinfood.dtos.v1.request.SearchResolutionsDTO;
import com.robinfood.dtos.v1.response.ApiGatewayResponseDTO;
import com.robinfood.dtos.v1.response.ResponseDTO;
import com.robinfood.dtos.v1.response.searchResolutions.DataResolutionResponseDTO;
import com.robinfood.dtos.v1.response.searchResolutions.PageableDTO;
import com.robinfood.dtos.v1.response.searchResolutions.ResolutionResponseDTO;
import com.robinfood.enums.AppLogsEnum;
import com.robinfood.exceptions.ApplicationException;
import com.robinfood.exceptions.NullFieldsValidationException;
import com.robinfood.mappers.ResponseMapper;
import com.robinfood.middleware.JwtMiddleware;
import com.robinfood.usecases.findallresolutions.FindAllResolutionsUseCase;
import com.robinfood.usecases.findallresolutions.IFindAllResolutionsUseCase;
import com.robinfood.util.ObjectMapperSingletonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static com.robinfood.constants.GeneralConstants.AUTHORIZATION;
import static com.robinfood.constants.GeneralConstants.PAGE;
import static com.robinfood.constants.GeneralConstants.SIZE;
import static com.robinfood.constants.GeneralConstants.STATUS;
import static com.robinfood.constants.GeneralConstants.VALUE_CUSTOM_FILTER;
import static com.robinfood.constants.GeneralConstants.ORDER_BY_END_DATE_RESOLUTION;
import static com.robinfood.constants.GeneralConstants.WITH_POS;
import static com.robinfood.constants.GeneralConstants.RESOLUTION_ID;
import static com.robinfood.enums.ErrorLogsEnum.ERROR_DATA_NOT_FOUND;



@Component
@Slf4j
public class FindAllResolutionHandler implements RequestHandler<APIGatewayProxyRequestEvent, ApiGatewayResponseDTO> {

    private final IFindAllResolutionsUseCase findAllResolutionsUseCase;

    public FindAllResolutionHandler() {
        this.findAllResolutionsUseCase = new FindAllResolutionsUseCase();
    }

    public FindAllResolutionHandler(IFindAllResolutionsUseCase findAllResolutionsUseCase) {
        this.findAllResolutionsUseCase = findAllResolutionsUseCase;
    }

    @Override
    public ApiGatewayResponseDTO handleRequest(APIGatewayProxyRequestEvent event, Context context) {

        ApiGatewayResponseDTO apiGatewayResponseDTO;

        try {

            log.info(AppLogsEnum.PROGRAM_START.getMessageWithCode(), FindAllResolutionHandler.class.getName());

            final String authorization = event.getHeaders().get(AUTHORIZATION);
            final String page = event.getQueryStringParameters().get(PAGE);
            final String size = event.getQueryStringParameters().get(SIZE);
            final String status = event.getQueryStringParameters().get(STATUS);
            final String valueCustomFilter = event.getQueryStringParameters().get(VALUE_CUSTOM_FILTER);
            final String orderByEndDate = event.getQueryStringParameters().get(ORDER_BY_END_DATE_RESOLUTION);
            final String withPos = event.getQueryStringParameters().get(WITH_POS);
            final String resolutionId = event.getQueryStringParameters().get(RESOLUTION_ID);

            log.info(AppLogsEnum.INPUT_PARAMETER_FIND_ALL_RESOLUTIONS.getMessageWithCode(),
                    page, size, status, valueCustomFilter, orderByEndDate, withPos, resolutionId);

            JwtMiddleware.validateToken(authorization);

            validateFields(page, size);

            final SearchResolutionsDTO searchResolutionsDTO =SearchResolutionsDTO.getSearchResolutionsDTO
                    (orderByEndDate, page, resolutionId, size, status, valueCustomFilter, withPos);

            log.info(AppLogsEnum.SEARCH_PARAMETERS_REQUEST.getMessageWithCode(), searchResolutionsDTO);

            DataResolutionResponseDTO dataResolutionResponse =
                    this.findAllResolutionsUseCase.invoke(searchResolutionsDTO);

            apiGatewayResponseDTO =  ApiGatewayResponseDTO.builder()
                    .setStatusCode(HttpStatus.SC_OK)
                    .setObjectBody(ResponseMapper
                            .buildDefault(HttpStatus.SC_OK,
                                    dataResolutionResponse,
                                    AppLogsEnum.SEARCH_SUCCESSFULLY.getMessage()))
                    .build();

        } catch (ApplicationException e){
            log.error(
                    ERROR_DATA_NOT_FOUND.getMessageWithCode(), e,
                    e.getStackTrace(),
                    ObjectMapperSingletonUtil.objectToJson(e.getApiGatewayResponseDTO())
            );

            apiGatewayResponseDTO = e.getApiGatewayResponseDTO();
        }
        return apiGatewayResponseDTO;
    }

    private static void validateFields(String page, String size) {
        if (Objects.isNull(page) || Objects.isNull(size)) {
            String errorMessage = null;
            if (Objects.isNull(page)) {
                errorMessage = "page is null";
            } else {
                errorMessage = "size is null";
            }
            throw new NullFieldsValidationException(
                    getResponseDTO(Boolean.TRUE, null, HttpStatus.SC_BAD_REQUEST, null, errorMessage),
                    errorMessage
            );
        }
    }

    private static ResponseDTO getResponseDTO(
            Boolean error,
            List<ResolutionResponseDTO> content,
            int httpStatus,
            PageableDTO pageable,
            String message
    ) {

        return ResponseDTO.builder()
                .data(new DataResolutionResponseDTO(content, pageable))
                .code(httpStatus)
                .error(error)
                .message(message)
                .build();

    }


}
