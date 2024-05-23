package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.PortionProductDTO;
import com.robinfood.core.dtos.request.order.FinalProductPortionDTO;
import com.robinfood.core.dtos.request.order.OriginalReplacementPortionDTO;
import com.robinfood.core.dtos.request.order.PortionGroupDTO;
import com.robinfood.core.dtos.request.order.ReplacementPortionDTO;
import com.robinfood.core.dtos.request.order.RequestOrderPortionProductDTO;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class FinalProductPortionDTODataMock {

    private PortionGroupDTO portionGroupDTO;
    private RequestOrderPortionProductDTO requestOrderPortionProductDTO;
    private ReplacementPortionDTO replacementPortionDTO;

    private PortionProductDTO product = new PortionProductDTO(
        "Arroz integral",
        89L
    );

    private RequestOrderPortionProductDTO productRequest = new RequestOrderPortionProductDTO(
            89L,
            "Arroz integral"
    );

    private ReplacementPortionDTO replacementPortionWithValueDTO = new ReplacementPortionDTO(
        28L,
        "Arroz integral",
        product,
        "26257095296811301",
        6L,
        266.0,
        new OriginalReplacementPortionDTO(
                28L,
                "Arroz integral",
                new PortionProductDTO(
                        "Arroz integral",
                        89L
                ),
                "26257095296811301",
                6L,
                266.0
        )
    );

    public FinalProductPortionDTO getDataDefault(){
        return new FinalProductPortionDTO(
                false,
                1L,
                0.0,
                0,
                0,
                portionGroupDTO,
                1L,
                "Carne a la plancha",
                null,
                1L,
                1L,
                100.0,
                requestOrderPortionProductDTO,
                1,
                replacementPortionDTO,
                "sku",
                1L,
                1L,
                1.0
        );
    }

    public FinalProductPortionDTO getDataDefaultFree(){
        return new FinalProductPortionDTO(
                true,
                1L,
                0.0,
                0,
                1,
                portionGroupDTO,
                1L,
                "Carne a la plancha",
                1L,
                1L,
                1L,
                100.0,
                requestOrderPortionProductDTO,
                5,
                replacementPortionDTO,
                "sku",
                1L,
                1L,
                1.0
        );
    }

    public FinalProductPortionDTO getDataReplacementPortionDefault(){
        return new FinalProductPortionDTO(
                false,
                1L,
                0.0,
                0,
                0,
                portionGroupDTO,
                28L,
                "Arroz integral",
                null,
                1L,
                1L,
                100.0,
                productRequest,
                1,
                replacementPortionWithValueDTO,
                "26257095296811301",
                1L,
                6L,
                266.0
        );
    }

    public List<FinalProductPortionDTO> getDataDefaultList(){
        return Collections.singletonList(getDataDefault());
    }

    public List<FinalProductPortionDTO> getDataDefaultFreeList(){
        return Collections.singletonList(getDataDefaultFree());
    }

    public List<FinalProductPortionDTO> getDataDefaultReplacementPortionList(){
        return Collections.singletonList(getDataReplacementPortionDefault());
    }
}
