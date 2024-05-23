package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.request.order.FinalProductDiscountDTO;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class FinalProductDiscountDTODataMock {
    public FinalProductDiscountDTO getDataDefault() {
        return new FinalProductDiscountDTO(true,8900.0);
    }
    public List<FinalProductDiscountDTO> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }
}
