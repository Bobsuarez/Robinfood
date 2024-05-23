package com.robinfood.repository.mocks;

import com.robinfood.core.dtos.MenuDataSkuDTO;
import com.robinfood.core.dtos.MenuValidationMessageErrorDTO;

import java.util.List;

public class MenuValidationErrorMock {
    public static MenuValidationMessageErrorDTO build() {
        return MenuValidationMessageErrorDTO.builder()
                .code("2")
                .message("sdsd")
                .error(List.of("12"))
                .data(List.of(new MenuDataSkuDTO()))
                .request("a")
                .build();
    }
}
