package com.robinfood.storeor.dtos.apiresponsebuilder;

import com.robinfood.storeor.enums.ApiResponseEnum;

/**
 * Implementation of 'BuilderApiResponseDTO'
 */
public class BadRequestAbstractApiResponseBuilderDTO<T> extends AbstractApiResponseBuilderDTO<T> {

    @Override
    public void build(T data, ApiResponseEnum apiResponseEnum) {
        apiResponseDTO = new APIResponseDTO<>(data, apiResponseEnum);
    }

}
