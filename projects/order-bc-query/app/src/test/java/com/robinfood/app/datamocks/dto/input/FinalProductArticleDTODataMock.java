package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.request.order.FinalProductArticleDTO;
import lombok.Data;

@Data
public class FinalProductArticleDTODataMock {
    public FinalProductArticleDTO getDataDefault(){
        return new FinalProductArticleDTO(1L, 1L, 1L);
    }
}
