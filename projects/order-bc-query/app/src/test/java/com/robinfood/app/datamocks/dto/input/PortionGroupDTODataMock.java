package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.PortionGroupDTO;
import lombok.Data;

@Data
public class PortionGroupDTODataMock {
    public PortionGroupDTO getDataDefault() {
        return new PortionGroupDTO(
            "Proteinas",
                1L
        );
    }
}
