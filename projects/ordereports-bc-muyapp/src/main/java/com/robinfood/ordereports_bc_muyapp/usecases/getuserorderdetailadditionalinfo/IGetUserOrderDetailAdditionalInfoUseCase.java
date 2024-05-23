package com.robinfood.ordereports_bc_muyapp.usecases.getuserorderdetailadditionalinfo;

import com.robinfood.app.library.exception.ApplicationException;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseOrderDetailDTO;

/**
 * Use case that returns the order detail by uid
 */
public interface IGetUserOrderDetailAdditionalInfoUseCase {

    /**
     * Gets the order detail by uid
     *
     * @param responseOrderDetailDTO basic response order detail
     *
     * @return the order detail by uid
     */
    ResponseOrderDetailDTO invoke(
            ResponseOrderDetailDTO responseOrderDetailDTO
    ) throws ApplicationException;

}
