package com.robinfood.app.usecases.getactivesalestoorderbycompanies;

import com.robinfood.core.dtos.orderSales.ResponseOrderActiveSalesDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Use case that returns the list of order sales by companies
 */
public interface IGetActiveSalesToOrderByCompaniesUseCase {

    /**
     * Get list order sales by companies
     *
     * @param idsCompanies   the list ids companies
     * @param dateTimeCurrent the date of creation to order
     * @return an object of ResponseOrderSalesDTO
     */
    Result<ResponseOrderActiveSalesDTO> invoke(
            List<Integer> idsCompanies,
            String dateTimeCurrent,
            List<String> timezones
    );
}
