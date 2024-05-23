package com.robinfood.repository.ordersalesbycompanies;

import com.robinfood.core.dtos.orderSales.ResponseOrderActiveSalesDTO;
import com.robinfood.core.enums.Result;

import java.util.List;

/**
 * Repository for the order sales by companies
 */
public interface IOrderSalesByCompaniesRepository {

    /**
     * get the list of order sales by companies
     *
     * @param idsCompanies list ids companies
     * @param dateTimeCurrent current date time to consult the records
     * @param token token auth service
     * @return data of payment methods
     */
    Result<ResponseOrderActiveSalesDTO> getSalesToOrderByCompanies (
            List<Integer> idsCompanies,
            String dateTimeCurrent,
            List<String> timezones,
            String token
    );
}
