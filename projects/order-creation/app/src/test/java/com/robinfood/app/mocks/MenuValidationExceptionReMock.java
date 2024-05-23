package com.robinfood.app.mocks;

import com.robinfood.core.dtos.MenuDataSkuDTO;
import com.robinfood.core.dtos.MenuValidationMessageErrorDTO;

import java.util.List;

public class MenuValidationExceptionReMock {
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
