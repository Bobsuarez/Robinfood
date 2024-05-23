package com.robinfood.repository.menu;

import com.robinfood.core.entities.menuvalidationentities.MenuValidationEntity;
import com.robinfood.core.models.retrofit.menu.brand.BrandResponse;
import com.robinfood.core.models.retrofit.menu.hallproductdetail.MenuHallProductDetailResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;

/**
 * Data source that handles remote related menu data transactions
 */
public interface IMenuRemoteDataSource {

    /**
     * Method that invokes the remote data source to obtain the brands according to the country
     * @param token     token from SSO
     * @param countryId to get the marks
     * @return brand list
     */
    List<BrandResponse> getBrandsByCountryId(
            String token,
            Long countryId
    );

    /**
     * Validates the products sent for the current transaction to see if an order
     * can be created with these finalProducts
     * @param token the authorization token
     * @param menu  the container of the products to be validated
     * @return a future containing the result of the operation
     */
    @Async
    CompletableFuture<Boolean> validateMenu(
            String token,
            String timezone,
            MenuValidationEntity menu
    );

    /**
     * Get menu hall product detail by menu hall product id
     * @param token             the authorization token
     * @param menuHallProductId Menu Hall Product Id
     * @return Menu hall product detail
     */

    MenuHallProductDetailResponse getMenuHallProductDetail(
            String token,
            Long menuHallProductId
    );
}
