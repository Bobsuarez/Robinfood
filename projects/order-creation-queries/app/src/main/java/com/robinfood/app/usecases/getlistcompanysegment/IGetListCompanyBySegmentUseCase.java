package com.robinfood.app.usecases.getlistcompanysegment;

import com.robinfood.core.dtos.SalesBuildAnswerDTO;
import com.robinfood.core.dtos.report.salebysegment.response.CompanyDTOResponse;

import java.util.List;

/**
 * Use case that returns the list of company by app
 */
public interface IGetListCompanyBySegmentUseCase {

    /**
     * retrieve the stores according to the entered criteria
     *
     * @return object with the data
     */
    List<CompanyDTOResponse> invoke(SalesBuildAnswerDTO salesBuildAnswerDTO);
}
