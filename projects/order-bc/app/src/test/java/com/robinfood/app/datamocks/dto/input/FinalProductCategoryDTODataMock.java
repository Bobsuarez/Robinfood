package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.request.order.FinalProductCategoryDTO;
import lombok.Data;

@Data
public class FinalProductCategoryDTODataMock {
    public FinalProductCategoryDTO getDataDefault(){
        return new FinalProductCategoryDTO(1L,"Suggested");
    }
}
