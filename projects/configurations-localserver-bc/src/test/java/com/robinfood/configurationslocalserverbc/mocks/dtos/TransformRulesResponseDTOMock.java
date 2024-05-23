package com.robinfood.configurationslocalserverbc.mocks.dtos;

import com.robinfood.configurationslocalserverbc.dtos.templateResponse.TransformRulesResponseDTO;

import java.util.Arrays;
import java.util.List;

public class TransformRulesResponseDTOMock {

    public List<TransformRulesResponseDTO> transformRulesResponseDTODefault = Arrays.asList(
            TransformRulesResponseDTO
                    .builder()
                    .description("Rule One")
                    .name("Rule test")
                    .params("{}")
                    .build(),
            TransformRulesResponseDTO
                    .builder()
                    .description("Rule Tow")
                    .name("Rule two test")
                    .params("{}")
                    .build()
    );

    public List<TransformRulesResponseDTO> transformRulesResponseDTOResponseMock = Arrays.asList(
            TransformRulesResponseDTO
                    .builder()
                    .description("Rule One")
                    .name("Rule test")
                    .params("{}")
                    .build()
    );
}
