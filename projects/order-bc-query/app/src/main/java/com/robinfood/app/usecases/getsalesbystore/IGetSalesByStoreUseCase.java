package com.robinfood.app.usecases.getsalesbystore;

import com.robinfood.core.dtos.report.salebysegment.DataIdsToFindTheSegment;
import com.robinfood.core.dtos.report.salebystore.GetSaleByStoreResponseDTO;

/**
 * get sales by store and grouped by payment methods
 */
public interface IGetSalesByStoreUseCase {

    /**
     * get sales by store and grouped by payment methods
     *
     * @param toFindTheSegment dto that contain th ids and date to consult
     * @return GetSaleByStoreResponseDTO contains the sales by store
     */
    GetSaleByStoreResponseDTO invoke(DataIdsToFindTheSegment toFindTheSegment);
}
