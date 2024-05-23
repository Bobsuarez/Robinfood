package com.robinfood.storeor.dtos.apiresponsebuilder;

import com.robinfood.storeor.enums.ApiResponseEnum;
import org.springframework.stereotype.Component;

@Component
public class OkAbstractApiResponseBuilderDTO<T> extends AbstractApiResponseBuilderDTO<T> {

    @Override
    public void build(T data, ApiResponseEnum apiResponseEnum) {
        apiResponseDTO = new APIResponseDTO<>(data, apiResponseEnum);
    }

}
