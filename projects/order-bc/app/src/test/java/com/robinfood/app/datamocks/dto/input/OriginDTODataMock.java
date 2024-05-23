package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.request.transaction.RequestOriginDTO;
import lombok.Data;

@Data
public class OriginDTODataMock {
    public RequestOriginDTO getDataDefault(){
        return new RequestOriginDTO(10L, "Cajas V2");
    }
}
