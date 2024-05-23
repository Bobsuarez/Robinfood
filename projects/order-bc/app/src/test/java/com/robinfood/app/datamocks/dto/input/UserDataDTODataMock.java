package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.request.transaction.RequestUserDTO;

public class UserDataDTODataMock {
    public RequestUserDTO getDataDefault() {
        return new RequestUserDTO(
                "muy@robbinfood.com",
                1L,
                "Usuario",
                "34343434",
                "Default",
                1L,
                "57",
                1L
        );
    }
}
