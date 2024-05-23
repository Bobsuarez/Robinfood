package com.robinfood.configurationsposbc.dtos.apiresponsebuilder;

import com.robinfood.configurationsposbc.enums.ApiResponseEnum;
import org.springframework.stereotype.Component;

@Component
public class OkAbstractApiResponseBuilderDTO<T>
        extends AbstractApiResponseBuilderDTO<T> {

    @Override
    public void build(T data, ApiResponseEnum apiResponseEnum) {
        apiResponseDTO = new APIResponseDTO<>(data, apiResponseEnum);
    }
}
