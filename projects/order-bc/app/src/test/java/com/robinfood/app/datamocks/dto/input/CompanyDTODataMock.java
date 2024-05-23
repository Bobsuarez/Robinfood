package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.request.transaction.RequestCompanyDTO;
import lombok.Data;

@Data
public class CompanyDTODataMock {
    public RequestCompanyDTO getDataDefault() {
        return new RequestCompanyDTO("COP", 1L);
    }
}
