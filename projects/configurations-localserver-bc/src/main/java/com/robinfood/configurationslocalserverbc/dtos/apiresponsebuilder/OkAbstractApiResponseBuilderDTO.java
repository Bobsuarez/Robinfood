package com.robinfood.configurationslocalserverbc.dtos.apiresponsebuilder;

import com.robinfood.configurationslocalserverbc.enums.ApiResponseEnum;
import org.springframework.stereotype.Component;

@Component
public class OkAbstractApiResponseBuilderDTO<T>
        extends AbstractApiResponseBuilderDTO<T> {

    @Override
    public void build(T data, ApiResponseEnum apiResponseEnum) {
        apiResponseDTO = new APIResponseDTO<>(data, apiResponseEnum);
    }
}
