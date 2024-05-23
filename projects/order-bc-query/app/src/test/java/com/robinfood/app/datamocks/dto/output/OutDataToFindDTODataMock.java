package com.robinfood.app.datamocks.dto.output;

import com.robinfood.core.dtos.report.salebysegment.DataIdsToFindTheSegment;
import lombok.Data;

@Data
public class OutDataToFindDTODataMock {
    public static DataIdsToFindTheSegment getDataDefault(){

        return DataIdsToFindTheSegment.builder()
                .build();
    }
}
