package com.robinfood.repository.menu;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.menuvalidationentities.MenuValidationEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.enums.TransactionCreationErrors;
import com.robinfood.core.exceptions.TransactionCreationException;
import com.robinfood.core.logging.mappeddiagnosticcontext.CreateTransactionCustomLog;
import com.robinfood.core.models.retrofit.menu.brand.BrandResponse;
import com.robinfood.core.models.retrofit.menu.hallproductdetail.MenuHallProductDetailResponse;
import com.robinfood.core.models.retrofit.menu.validate.DiscountProductsResponse;
import com.robinfood.network.api.MenuBCAPI;
import com.robinfood.network.api.MenuBaseAdminBCAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.robinfood.core.extensions.NetworkExtensionsKt.safeAPICall;
import static com.robinfood.core.extensions.NetworkExtensionsMenuKt.safeAPICallMenu;


/**
 * Implementation of IMenuRemoteDataSource
 */
@Slf4j
public class MenuRemoteDataSource implements IMenuRemoteDataSource {

    private final MenuBaseAdminBCAPI menuBaseAdminBCAPI;
    private final MenuBCAPI menuBCAPI;

    public MenuRemoteDataSource(MenuBaseAdminBCAPI menuBaseAdminBCAPI, MenuBCAPI menuBCAPI) {
        this.menuBaseAdminBCAPI = menuBaseAdminBCAPI;
        this.menuBCAPI = menuBCAPI;
    }

    @Override
    public List<BrandResponse> getBrandsByCountryId(
            String token,
            Long countryId
    ) {
        Result<ApiResponseEntity<List<BrandResponse>>> brandsResult =
                safeAPICall(menuBaseAdminBCAPI.getBrandsByCountryId(token, countryId));

        if (brandsResult instanceof Result.Error) {
            final Result.Error error = (Result.Error) brandsResult;

            throw new TransactionCreationException(
                    error.component1().getMessage(),
                    "Error obtain brands",
                    TransactionCreationErrors.GET_BRANDS_ERROR,
                    HttpStatus.PRECONDITION_FAILED
            );
        }

        return ((Result.Success<ApiResponseEntity<List<BrandResponse>>>) brandsResult).getData()
                .getData();
    }

    @Async
    @Override
    public CompletableFuture<Boolean> validateMenu(
            String token,
            String timezone,
            MenuValidationEntity menu
    ) {

        CreateTransactionCustomLog.addUuid();

        final Result<ApiResponseEntity<DiscountProductsResponse>> menuValidationResult =
                safeAPICallMenu(menuBCAPI.validateMenu(token, timezone, menu));

        if (menuValidationResult instanceof Result.Error) {

            final Result.Error error = (Result.Error) menuValidationResult;

            if (error.component2() == HttpStatus.NOT_FOUND) {

                throw new TransactionCreationException(error.component1().getMessage(),
                        TransactionCreationErrors.INVALID_MENU.name(),
                        TransactionCreationErrors.INVALID_MENU,
                        HttpStatus.PRECONDITION_FAILED);
            }

            throw new TransactionCreationException(error.component1().getMessage(),
                    TransactionCreationErrors.INVALID_MENU_DISCOUNT.name(),
                    TransactionCreationErrors.INVALID_MENU_DISCOUNT,
                    HttpStatus.PRECONDITION_FAILED);

        }

        CreateTransactionCustomLog.removeHits();
        return CompletableFuture.completedFuture(
                menuValidationResult instanceof Result.Success
        );
    }

    @Override
    public MenuHallProductDetailResponse getMenuHallProductDetail(String token, Long menuHallProductId) {

        Result<ApiResponseEntity<MenuHallProductDetailResponse>> responseEntityResult =
                safeAPICall(menuBCAPI.menuProductDetail(token, menuHallProductId));

        if (responseEntityResult instanceof Result.Error) {

            final Result.Error error = (Result.Error) responseEntityResult;

            if (error.component2() == HttpStatus.NOT_FOUND) {

                throw new TransactionCreationException(error.component1().getMessage(),
                        "Error getting menu hall product detail",
                        TransactionCreationErrors.NOT_FOUND_MENU_HALL_PRODUCT_ID,
                        HttpStatus.PRECONDITION_FAILED);
            }

            throw new TransactionCreationException(error.component1().getMessage(),
                    "Error getting menu hall product detail", TransactionCreationErrors.MENU_HALL_PRODUCT_ID,
                    HttpStatus.PRECONDITION_FAILED);

        }

        return ((Result.Success<ApiResponseEntity<MenuHallProductDetailResponse>>) responseEntityResult).getData()
                .getData();
    }
}


