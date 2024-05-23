package com.robinfood.repository.menu;

import com.robinfood.core.dtos.MenuValidationDTO;
import com.robinfood.core.dtos.menuhallproductdetail.MenuHallProductDetailDTO;
import com.robinfood.core.models.domain.menu.Brand;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;

/**
 * Repository that handles menu related data
 */
public interface IMenuRepository {

    /**
     * Method that invokes the repository to obtain the brands according to the country
     * @param token     token from SSO
     * @param countryId to get the marks
     * @return brand list
     */
    List<Brand> getBrandsByCountryId(
            String token,
            Long countryId
    );

    /**
     * Validates the products sent for the current transaction to see if an order
     * can be created with these finalProducts
     * @param token      the authorization token
     * @param menuValidationDTO  the transaction country id
     * @return a future containing the result of the operation
     */
    @Async
    CompletableFuture<Boolean> validateMenu(
            String token,
            MenuValidationDTO menuValidationDTO
    );

    /**
     * Get menu hall product detail by menu hall product id
     * @param token             the authorization token
     * @param menuHallProductId Menu Hall Product Id
     * @return Menu hall product detail
     */

    MenuHallProductDetailDTO getMenuHallProductDetail(String token, Long menuHallProductId);
}
