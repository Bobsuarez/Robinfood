package com.robinfood.localorderbc.dtos.apiresponsebuilder;

import com.robinfood.localorderbc.enums.ApiResponseEnum;

/**
 * Implementation of 'BuilderApiResponseDTO'
 */
public class BadRequestAbstractApiResponseBuilderDTO<T> extends AbstractApiResponseBuilderDTO<T> {

    @Override
    public void build(T data, ApiResponseEnum apiResponseEnum) {
        apiResponseDTO = new APIResponseDTO<>(data, apiResponseEnum);
    }

}
