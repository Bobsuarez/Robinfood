package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.PortionProductDTO;
import com.robinfood.core.dtos.ReplacementPortionDTO;
import lombok.Data;

@Data
public class ReplacementPortionDTODataMock {
    public ReplacementPortionDTO getDataDefault() {
        
        return new ReplacementPortionDTO(
                1L,
                "Jugo",
                new PortionProductDTO(
                        "Cocacola",
                        2L
                )
        );
    }
}
