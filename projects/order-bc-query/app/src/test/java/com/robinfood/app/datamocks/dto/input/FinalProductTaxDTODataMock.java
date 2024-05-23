package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.request.order.FinalProductTaxDTO;
import java.util.Arrays;
import java.util.List;

public class FinalProductTaxDTODataMock {

    public FinalProductTaxDTO getDataDefault() {
        return new FinalProductTaxDTO(
            1L,
            1L,
            1L,
            1L,
            1L,
            1L,
            null,
            1L,
            0.0,
            1L,
            "IMPOCOSUMO",
            1L,
            0.0
        );
    }

    public List<FinalProductTaxDTO> getDataDefaultList() {
        return Arrays.asList(getDataDefault());
    }

    public List<List<FinalProductTaxDTO>> getDataDefaultListForCalculate() {
        FinalProductTaxDTO finalProductTaxDTOOne = getDataDefault();
        finalProductTaxDTOOne.setTaxValue(150.0);
        finalProductTaxDTOOne.setTaxPrice(100.0);

        FinalProductTaxDTO finalProductTaxDTOTwo = getDataDefault();
        finalProductTaxDTOTwo.setTaxValue(250.0);
        finalProductTaxDTOTwo.setTaxPrice(150.0);

        FinalProductTaxDTO finalProductTaxDTOThree = getDataDefault();
        finalProductTaxDTOThree.setTaxValue(350.0);
        finalProductTaxDTOThree.setTaxPrice(20.0);

        FinalProductTaxDTO finalProductTaxDTOFour = getDataDefault();
        finalProductTaxDTOFour.setTaxValue(450.0);
        finalProductTaxDTOFour.setTaxPrice(100.0);

        return Arrays.asList(
            Arrays.asList(
                finalProductTaxDTOOne,
                finalProductTaxDTOTwo
            ),
            Arrays.asList(
                finalProductTaxDTOThree,
                finalProductTaxDTOFour
            )
        );
    }
}
