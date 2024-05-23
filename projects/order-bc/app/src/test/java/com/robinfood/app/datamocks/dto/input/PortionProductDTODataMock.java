package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.PortionProductDTO;
import lombok.Data;

@Data
public class PortionProductDTODataMock {
    
    public PortionProductDTO getDataDefault() {
        return new PortionProductDTO(
                "Carne",
                1L
        );
    }
}
