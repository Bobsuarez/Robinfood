package com.robinfood.app.usecases.getsalesbysegment;

import com.robinfood.core.dtos.report.salebysegment.DataIdsToFindTheSegment;
import com.robinfood.core.dtos.report.salebysegment.GetSaleBySegmentResponseDTO;
import com.robinfood.core.exceptions.AsyncOrderBcException;

import java.util.List;

/**
 * Use case that returns the effectiveness of the orders and canceled along with their
 * sale by segments such as channels - payment methods - stores - brands
 */
public interface IGetSalesBySegmentUseCase {

    /**
     * retrieve the orders according to the entered criteria
     *
     * @param timezones list of the timezones per company
     * @param toFindTheSegment Dto that contains the data entered
     * @return Dto request GetSaleBySegmentResponseDTO
     */
    GetSaleBySegmentResponseDTO invoke(
            List<String> timezones,
            DataIdsToFindTheSegment toFindTheSegment
    ) throws AsyncOrderBcException;
}
