package com.robinfood.ordereports_bc_muyapp.usecases.getuserorderdetailbyuid;

import com.robinfood.app.library.exception.ApplicationException;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseOrderDetailDTO;

/**
 * Use case that returns the order detail by uid
 */
public interface IGetUserOrderDetailByUIdUseCase {

    /**
     * Gets the order detail by uid
     *
     * @param transactionUuid order uid
     *
     * @return the order detail by uid
     */
    ResponseOrderDetailDTO invoke(String transactionUuid) throws ApplicationException;
}
