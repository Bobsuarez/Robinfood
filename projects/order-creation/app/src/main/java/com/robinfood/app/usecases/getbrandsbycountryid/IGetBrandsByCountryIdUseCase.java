package com.robinfood.app.usecases.getbrandsbycountryid;

import com.robinfood.core.models.domain.menu.Brand;
import java.util.List;

/**
 * Contract to obtain trademarks according to the country
 */
public interface IGetBrandsByCountryIdUseCase {

    /**
     * Method that invokes the use case to obtain the brands according to the country
     * @param token token from SSO
     * @param countryId to get the marks
     * @return brand list
     */
    List<Brand> invoke(
        String token,
        Long countryId
    );

}
