package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.request.order.FinalProductRemovedPortionDTO;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class RemovedPortionDTODataMock {
    
    public FinalProductRemovedPortionDTO getDataDefault() {
        FinalProductRemovedPortionDTO inputFinalProductRemovedPortionDTO = new FinalProductRemovedPortionDTO(
                1L,
                1L,
                "Arroz"
        );

        inputFinalProductRemovedPortionDTO.setFinalProductId(1L);
        inputFinalProductRemovedPortionDTO.setOrderId(1L);
        return inputFinalProductRemovedPortionDTO;
    }

    public List<FinalProductRemovedPortionDTO> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }
}
