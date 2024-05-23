package com.robinfood.repository.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.robinfood.core.entities.ApiResponseEntity;
import com.robinfood.core.entities.GroupEntity;
import com.robinfood.core.entities.PortionEntity;
import com.robinfood.core.entities.menuvalidationentities.MenuValidationEntity;
import com.robinfood.core.entities.menuvalidationentities.MenuValidationPaymentEntity;
import com.robinfood.core.entities.menuvalidationentities.MenuValidationProductEntity;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.core.models.retrofit.menu.brand.BrandResponse;
import com.robinfood.core.models.retrofit.menu.hallproductdetail.MenuHallProductDetailResponse;
import com.robinfood.core.models.retrofit.menu.validate.DiscountProductsResponse;
import com.robinfood.core.models.retrofit.menu.validate.ProductResponse;
import com.robinfood.network.api.MenuBCAPI;
import com.robinfood.network.api.MenuBaseAdminBCAPI;
import com.robinfood.repository.mocks.menu.BrandResponseMock;
import com.robinfood.repository.mocks.menu.MenuHallProductDetailResponseMocks;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

@ExtendWith(MockitoExtension.class)
class MenuRemoteDataSourceTest {

    @Mock
    private MenuBaseAdminBCAPI mockMenuBaseAdminBCAPI;

    @Mock
    private MenuBCAPI mockMenuBCApi;

    @Mock
    private Call<ApiResponseEntity<DiscountProductsResponse>> mockValidateMenu;

    @Mock
    private Call<ApiResponseEntity<List<BrandResponse>>> mockBrands;

    @Mock
    private Call<ApiResponseEntity<MenuHallProductDetailResponse>> mockHallProductDetail;

    @InjectMocks
    private MenuRemoteDataSource menuRemoteDataSource;

    final Long brandId = 1L;

    final Long countryId = 1L;

    final Long flowId = 1L;

    final MenuValidationPaymentEntity payment = new MenuValidationPaymentEntity(
            BigDecimal.ZERO,
            BigDecimal.valueOf(10900.0),
            BigDecimal.ZERO,
            1,
            BigDecimal.valueOf(10900.0)
    );

    final Long platformId = 1L;

    final Long storeId = 1L;

    final List<MenuValidationProductEntity> products = Collections.singletonList(
            new MenuValidationProductEntity(
                    brandId,
                    BigDecimal.ZERO,
                    Collections.singletonList(
                            new GroupEntity(
                                    1L,
                                    Collections.singletonList(
                                            new PortionEntity(
                                                    null,
                                                    1,
                                                    1L,
                                                    true,
                                                    "Carne Molida",
                                                    BigDecimal.valueOf(0.0),
                                                    1L,
                                                    1,
                                                    "1234"
                                            )
                                    ),
                                    "1234"
                            )
                    ),
                    1L,
                1L,
                    BigDecimal.valueOf(10900.0),
                    1,
                    "1234",
                    BigDecimal.valueOf(10900.0),
                    BigDecimal.ZERO,
                    1,
                    "ARTICLE"
            )
    );

    final MenuValidationEntity menu = MenuValidationEntity.builder()
            .brandId(1L)
            .countryId(countryId)
            .flowId(flowId)
            .payment(payment)
            .platformId(platformId)
            .products(products)
            .storeId(storeId)
            .build();

    private final String token = "token";

    final ApiResponseEntity<DiscountProductsResponse> apiResponseEntityMenu = ApiResponseEntity.<DiscountProductsResponse>builder()
            .code(200)
            .data(            DiscountProductsResponse.builder()
                    .discountProducts(Collections.singletonList(ProductResponse.builder()
                            .build()))
                    .build()
            )
            .locale("CO")
            .message("Menu es valid")
            .build();

    @Mock
    private final Response<ApiResponseEntity<DiscountProductsResponse>> responseMock = Response.success(
            200,
            apiResponseEntityMenu
    );

    final ApiResponseEntity<Boolean> apiErrorResponseEntityMenu = ApiResponseEntity.<Boolean>builder()
            .code(400)
            .error("Error")
            .locale("CO")
            .message("INVALID_MENU_DISCOUNT")
            .build();

    final ApiResponseEntity<List<BrandResponse>> apiResponseBrands = ApiResponseEntity.<List<BrandResponse>>builder()
            .code(200)
            .data(Collections.singletonList(BrandResponseMock.build()))
            .locale("CO")
            .message("brands")
            .build();

    final ApiResponseEntity<Boolean> apiErrorResponseBrands = ApiResponseEntity.<Boolean>builder()
            .code(400)
            .error("Error")
            .locale("CO")
            .message("Error obtain brands")
            .build();

    final ApiResponseEntity<MenuHallProductDetailResponse> apiErrorResponseHallProductDetail = ApiResponseEntity.<MenuHallProductDetailResponse>builder()
            .code(400)
            .error("Error")
            .message("Error getting menu hall product detail")
            .build();

    final ApiResponseEntity<MenuHallProductDetailResponse> apiResponseHallProductDetail = ApiResponseEntity.<MenuHallProductDetailResponse>builder()
            .code(200)
            .data(MenuHallProductDetailResponseMocks.aMenuHallProductDetailResponse())
            .locale("CO")
            .message("Menu Hall Product Detail")
            .build();

    @Test
    void test_ValidateIfUserIsEmployee_Returns_Correctly() throws Exception {
        when(mockMenuBCApi.validateMenu(anyString(), anyString(), any()))
                .thenReturn(mockValidateMenu);
        when(mockValidateMenu.execute()).thenReturn(Response.success(apiResponseEntityMenu));

        final Boolean result = menuRemoteDataSource.validateMenu(token,"", menu).join();

        assertEquals(true, result);
    }

    @Test
    void test_ValidateIfUserIsEmployee_Returns_Correctly_Not_Success() throws Exception {
        when(mockMenuBCApi.validateMenu(anyString(), anyString(), any()))
                .thenReturn(mockValidateMenu);

        when(responseMock.body()).thenReturn(apiResponseEntityMenu);
        when(responseMock.code()).thenReturn(HttpStatus.SC_FORBIDDEN);
        when(mockValidateMenu.execute()).thenReturn(responseMock);
        when(responseMock.isSuccessful()).thenReturn(false);

        try {
            menuRemoteDataSource.validateMenu(token,"", menu).join();
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiErrorResponseEntityMenu.getMessage()));
        }
    }

    @Test
    void test_ValidateIfUserIsEmployee_Returns_Correctly_Body_Is_NuLL() throws Exception {

        when(mockMenuBCApi.validateMenu(anyString(), anyString(), any()))
                .thenReturn(mockValidateMenu);
        when(mockValidateMenu.execute()).thenReturn(Response.success(200, null));
        Request.Builder r = new Request.Builder().url("http://localhost:8083");

        when(mockValidateMenu.request()).thenReturn(r.build());
        try {
            menuRemoteDataSource.validateMenu(token, "", menu).join();
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiErrorResponseEntityMenu.getMessage()));
        }
    }

    @Test
    void test_ValidateIfUserIsEmployee_Returns_Correctly_isException() throws Exception {
        when(mockMenuBCApi.validateMenu(anyString(), anyString(), any()))
                .thenReturn(mockValidateMenu);

        when(mockValidateMenu.execute()).thenThrow(new IOException());

        try {
            menuRemoteDataSource.validateMenu(token, "", menu).join();
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiErrorResponseEntityMenu.getMessage()));
        }
    }

    @Test
    void test_ValidateIfUserIsEmployee_Returns_WithFailure() throws Exception {
        final String responseJSON = ObjectExtensions.toJson(apiErrorResponseEntityMenu);
        when(mockMenuBCApi.validateMenu(anyString(), anyString(), any()))
                .thenReturn(mockValidateMenu);
        when(mockValidateMenu.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                responseJSON
        )));

        Request.Builder r = new Request.Builder().url("http://localhost:8083");

        when(mockValidateMenu.request()).thenReturn(r.build());

        try {
            menuRemoteDataSource.validateMenu(token, "", menu).join();
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiErrorResponseEntityMenu.getMessage()));
        }
    }

    @Test
    void test_ValidateIfUserIsEmployee_Returns_WithFailure_Request() throws Exception {
        final String responseJSON = ObjectExtensions.toJson(apiErrorResponseEntityMenu);
        when(mockMenuBCApi.validateMenu(anyString(), anyString(), any()))
                .thenReturn(mockValidateMenu);
        when(mockValidateMenu.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                responseJSON
        )));

        try {
            menuRemoteDataSource.validateMenu(token, "", menu).join();
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiErrorResponseEntityMenu.getMessage()));
        }
    }

    @Test
    void get_brands_correctly() throws Exception {
        // Arrange
        String token = "token";
        Long countryId = 3L;
        BrandResponse brandResponse = BrandResponseMock.build();

        when(mockMenuBaseAdminBCAPI.getBrandsByCountryId(any(), any())).thenReturn(mockBrands);

        when(mockBrands.execute()).thenReturn(Response.success(apiResponseBrands));

        // Act
        List<BrandResponse> result = menuRemoteDataSource.getBrandsByCountryId(token, countryId);

        // Assert
        assertEquals(brandResponse.getCountryId(), result.get(0).getCountryId());
        assertEquals(brandResponse.getFranchiseId(), result.get(0).getFranchiseId());
        assertEquals(brandResponse.getId(), result.get(0).getId());
    }

    @Test
    void get_brands_with_error() throws Exception {
        String token = "token";
        Long countryId = 3L;
        String responseJSON = ObjectExtensions.toJson(apiErrorResponseBrands);

        when(mockMenuBaseAdminBCAPI.getBrandsByCountryId(any(), any()))
                .thenReturn(mockBrands);
        when(mockBrands.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                responseJSON
        )));

        try {
            menuRemoteDataSource.getBrandsByCountryId(token, countryId);
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiErrorResponseBrands.getMessage()));
        }
    }

    @Test
    void test_Validate_Menu_Remote_Data_Source_Unauthorized_request() throws Exception {
        final String responseJSON = ObjectExtensions.toJson(apiErrorResponseEntityMenu);

        when(mockMenuBCApi.validateMenu(anyString(), anyString(), any()))
                .thenReturn(mockValidateMenu);

        when(mockValidateMenu.execute()).thenReturn(Response.error(401, ResponseBody.create(
                MediaType.parse("application/json"),
                responseJSON
        )));

        Request.Builder r = new Request.Builder().url("http://localhost:8083");

        when(mockValidateMenu.request()).thenReturn(r.build());
        try {
            menuRemoteDataSource.validateMenu(token, "", menu).join();
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiErrorResponseEntityMenu.getMessage()));
        }
    }

    @Test
    void test_Validate_Menu_Remote_Data_Source_404_error() throws Exception {
        final String responseJSON = ObjectExtensions.toJson(apiErrorResponseEntityMenu);

        when(mockMenuBCApi.validateMenu(anyString(), anyString(), any()))
                .thenReturn(mockValidateMenu);

        when(mockValidateMenu.execute()).thenReturn(Response.error(404, ResponseBody.create(
                MediaType.parse("application/json"),
                responseJSON
        )));

        Request.Builder r = new Request.Builder().url("http://localhost:8083");

        when(mockValidateMenu.request()).thenReturn(r.build());
        try {
            menuRemoteDataSource.validateMenu(token, "", menu).join();
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiErrorResponseEntityMenu.getMessage()));
        }
    }

    @Test
    void test_Get_MenuHall_Product_Detail_Menu_Remote_Data_Source_404_error() throws Exception {
        final String responseJSON = ObjectExtensions.toJson(apiErrorResponseEntityMenu);

        when(mockMenuBCApi.menuProductDetail(token, 1L))
                .thenReturn(mockHallProductDetail);

        when(mockHallProductDetail.execute()).thenReturn(Response.error(404, ResponseBody.create(
                MediaType.parse("application/json"),
                responseJSON
        )));

        Request.Builder requestBuilder = new Request.Builder().url("http://localhost:8083");

        when(mockHallProductDetail.request()).thenReturn(requestBuilder.build());
        try {
            menuRemoteDataSource.getMenuHallProductDetail(token, 1L);
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiErrorResponseHallProductDetail.getMessage()));
        }
    }

    @Test
    void test_Get_MenuHall_Product_Detail_Menu_Remote_Data_Source_Unauthorized_request() throws Exception {

        final String responseJSON = ObjectExtensions.toJson(apiErrorResponseHallProductDetail);

        when(mockMenuBCApi.menuProductDetail(token, 1L))
                .thenReturn(mockHallProductDetail);

        when(mockHallProductDetail.execute()).thenReturn(Response.error(401, ResponseBody.create(
                MediaType.parse("application/json"),
                responseJSON
        )));

        Request.Builder requestBuilder = new Request.Builder().url("http://localhost:8083");

        when(mockHallProductDetail.request()).thenReturn(requestBuilder.build());
        try {
            menuRemoteDataSource.getMenuHallProductDetail(token, 1L);
        } catch (Exception exception) {
            assertTrue(exception.getLocalizedMessage().contains(apiErrorResponseHallProductDetail.getMessage()));
        }
    }

    @Test
    void test_Get_MenuHall_Product_Detail_Menu_Remote_Data_Source_correctly() throws Exception {

        // Arrange
        String token = "token";
        Long menuHallProductId = 1L;
        MenuHallProductDetailResponse responseMock = MenuHallProductDetailResponseMocks.aMenuHallProductDetailResponse();

        when(mockMenuBCApi.menuProductDetail(any(), any())).thenReturn(mockHallProductDetail);

        when(mockHallProductDetail.execute()).thenReturn(Response.success(apiResponseHallProductDetail));

        // Act
        MenuHallProductDetailResponse result = menuRemoteDataSource.getMenuHallProductDetail(token, menuHallProductId);

        // Assert
        assertEquals(menuHallProductId, result.getId());
    }
}
