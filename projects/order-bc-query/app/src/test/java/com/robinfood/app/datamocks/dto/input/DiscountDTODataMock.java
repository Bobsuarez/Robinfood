package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.request.order.DiscountDTO;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class DiscountDTODataMock {

    public DiscountDTO getDataDefault() {
        return new DiscountDTO(
                1L,
                Boolean.TRUE,
                Boolean.FALSE,
                1L,
                null,
                1L,
                100.0
        );
    }
    public DiscountDTO getDataDefault_with_Product_Discount() {
        return new DiscountDTO(
                1L,
                Boolean.FALSE,
                Boolean.FALSE,
                1L,
                null,
                1L,
                100.0
        );
    }

    public List<DiscountDTO> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }

    public List<DiscountDTO> getDataDefaultList_With_Product() {
        return Arrays.asList(getDataDefault_with_Product_Discount());
    }

    public List<DiscountDTO> getDataDefaultForCalculateList() {
        return Arrays.asList(
                new DiscountDTO(
                        1L,
                        Boolean.FALSE,
                        Boolean.TRUE,
                        null,
                        null,
                        1L,
                        1000.0
                ),
                new DiscountDTO(
                        2L,
                        Boolean.FALSE,
                        Boolean.TRUE,
                        null,
                        null,
                        1L,
                        1500.0
                )
        );
    }

    public List<DiscountDTO> getDataDefaultForCalculateListConsumptionDiscount() {
        return Arrays.asList(
                new DiscountDTO(
                        1L,
                        Boolean.TRUE,
                        Boolean.FALSE,
                        null,
                        null,
                        1L,
                        1000.0
                )
        );
    }
}
