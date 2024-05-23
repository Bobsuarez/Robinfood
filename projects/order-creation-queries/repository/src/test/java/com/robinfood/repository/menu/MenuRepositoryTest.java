package com.robinfood.repository.menu;

import com.robinfood.core.dtos.MenuCurrentDTO;
import com.robinfood.core.dtos.menu.MenuHallProductResponseDTO;
import com.robinfood.core.dtos.menu.MenuSuggestedPortionResponseDTO;
import com.robinfood.core.entities.APIResponseEntity;
import com.robinfood.core.entities.MenuCurrentEntity;
import com.robinfood.core.entities.MenuHallsEntity;
import com.robinfood.core.entities.MenuProductEntity;
import com.robinfood.core.entities.menu.MenuHallProductResponseEntity;
import com.robinfood.core.entities.menu.MenuSuggestedPortionDataEntity;
import com.robinfood.core.entities.menu.MenuSuggestedPortionResponseEntity;
import com.robinfood.core.enums.Result;
import com.robinfood.core.extensions.ObjectExtensions;
import com.robinfood.network.api.MenuBcAPI;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuRepositoryTest {

    @Mock
    private MenuBcAPI mockMenuBcAPI;

    @Mock
    private Call<APIResponseEntity<MenuCurrentEntity>> mockApiResponseEntityCall;

    @Mock
    private Call<APIResponseEntity<List<MenuSuggestedPortionResponseEntity>>> mockApiResponseCallSuggestedPortions;

    @Mock
    private Call<APIResponseEntity<MenuHallProductResponseEntity>> mockApiResponseCallProductDetail;

    @InjectMocks
    private MenuRepository menuRepository;

    private final String token = "token";
    private final Long brandId = 1L;
    private final Long countryId = 1L;
    private final Long flowId = 1L;
    private final Long storeId = 1L;

    private final List<MenuProductEntity> menuProductEntities = new ArrayList<>(Arrays.asList(
            new MenuProductEntity(
                    1L,
                    "description",
                    new BigDecimal(1000),
                    1L,
                    1L,
                    "image",
                    "name",
                    1L,
                    1L,
                    new BigDecimal(1000),
                    "product flow",
                    1L,
                    "szu",
                    1L
            )
    ));

    private final List<MenuHallsEntity> menuHallsEntities = new ArrayList<>(Arrays.asList(
            new MenuHallsEntity(
                    1L,
                    "name",
                    1L,
                    menuProductEntities
            )
    ));

    private final MenuHallProductResponseEntity menuHallProductResponseEntity = new MenuHallProductResponseEntity(
         1L,
            1L,
            "a"
            ,BigDecimal.ONE,
            1L,
            List.of(),
            1L,
            "a",
            "a",
            1L,
            1,
            BigDecimal.ONE,
            "a",
            1L,
            "aa",
            List.of(),
            1L,
            "a"

    );

    private final MenuCurrentEntity menuCurrentEntity = new MenuCurrentEntity(
            menuHallsEntities,
            "name"
    );

    private final APIResponseEntity<MenuCurrentEntity> apiResponseEntity = new APIResponseEntity<>(
            200,
            menuCurrentEntity,
            "CO",
            "",
            "Menu returned"
    );

    private final APIResponseEntity<MenuCurrentEntity> apiResponseErrorEntity = new APIResponseEntity<>(
            500,
            menuCurrentEntity,
            "CO",
            "Error",
            "Menu could not be returned"
    );

    private final List<Long> portionIds = new ArrayList<>(Collections.singletonList(1L));

    private final List<MenuSuggestedPortionDataEntity> portionChangesEntities = new ArrayList<>(
            Collections.singletonList(
                    new MenuSuggestedPortionDataEntity(
                            1L,
                            2L,
                            "image",
                            "Changed Portion",
                            1L,
                            70.0,
                            "sku"
                            )
            )
    );

    private final List<MenuSuggestedPortionResponseEntity> suggestedPortionResponseList = new ArrayList<>(
            Collections.singletonList(
                    new MenuSuggestedPortionResponseEntity(
                            portionChangesEntities,
                            1L,
                            "image",
                            "Portion Name",
                            1L,
                            "sku"
                    )
            )
    );

    private final APIResponseEntity<List<MenuSuggestedPortionResponseEntity>> apiResponseEntitySuggestedPortions =
            new APIResponseEntity<>(
                    200,
                    suggestedPortionResponseList,
                    "CO",
                    "",
                    "Suggested portions retrieved successfully"
            );


    private final APIResponseEntity<MenuHallProductResponseEntity> apiResponseEntityProductDetail=
            new APIResponseEntity<>(
                    200,
                    menuHallProductResponseEntity,
                    "CO",
                    "",
                    "Suggested portions retrieved successfully"
            );

    @Test
    void test_Get_Menu_Returns_Successfully() throws Exception {

        when(mockMenuBcAPI.getMenuCurrent(
                brandId,
                countryId,
                flowId,
                storeId,
                token
        )).thenReturn(mockApiResponseEntityCall);

        when(mockApiResponseEntityCall.execute()).thenReturn(Response.success(apiResponseEntity));

        final Result<MenuCurrentDTO> result = menuRepository.getMenuCurrent(
                brandId,
                countryId,
                flowId,
                storeId,
                token
        );

        assertTrue(result instanceof Result.Success);
    }

    @Test
    void test_Get_Menu_Returns_With_Error() throws IOException {
        when(mockMenuBcAPI.getMenuCurrent(
                brandId,
                countryId,
                flowId,
                storeId,
                token
        )).thenReturn(mockApiResponseEntityCall);

        when(mockApiResponseEntityCall.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                ObjectExtensions.toJson(apiResponseErrorEntity)
        )));

        final Result<MenuCurrentDTO> result = menuRepository.getMenuCurrent(
                brandId,
                countryId,
                flowId,
                storeId,
                token
        );

        assertTrue(result instanceof Result.Error);
    }

    @Test
    void test_getSuggestedPortions_successfully() throws Exception {
        when(mockMenuBcAPI.getSuggestedPortions(
                portionIds,
                token
        )).thenReturn(mockApiResponseCallSuggestedPortions);

        when(mockApiResponseCallSuggestedPortions.execute())
                .thenReturn(Response.success(apiResponseEntitySuggestedPortions));

        final Result<List<MenuSuggestedPortionResponseDTO>> result = menuRepository.getSuggestedPortions(
                portionIds,
                token
        );

        assertTrue(result instanceof Result.Success);
    }

    @Test
    void test_Get_Product_Details_Returns_Successfully() throws Exception {

        when(mockMenuBcAPI.getProductDetail(
                1L,
                brandId,
                countryId,
                flowId,
                storeId,
                token
        )).thenReturn(mockApiResponseCallProductDetail);

        when(mockApiResponseCallProductDetail.execute()).thenReturn(Response.success(apiResponseEntityProductDetail));

        final Result<MenuHallProductResponseDTO> result = menuRepository.getProductDetail(
                brandId,
                countryId,
                flowId,
                1L,
                storeId,
                token
        );

        assertTrue(result instanceof Result.Success);
    }

    @Test
    void test_Get_Product_Detail_Returns_With_Error() throws IOException {
        when(mockMenuBcAPI.getProductDetail(
                1L,
                brandId,
                countryId,
                flowId,
                storeId,
                token
        )).thenReturn(mockApiResponseCallProductDetail);

        when(mockApiResponseCallProductDetail.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                ObjectExtensions.toJson(apiResponseErrorEntity)
        )));


        final Result<MenuHallProductResponseDTO> result = menuRepository.getProductDetail(
                brandId,
                countryId,
                flowId,
                1L,
                storeId,
                token
        );
        assertTrue(result instanceof Result.Error);
    }

    @Test
    void test_getSuggestedPortions_Returns_With_Error() throws IOException {
        when(mockMenuBcAPI.getSuggestedPortions(
                portionIds,
                token
        )).thenReturn(mockApiResponseCallSuggestedPortions);

        when(mockApiResponseCallSuggestedPortions.execute()).thenReturn(Response.error(500, ResponseBody.create(
                MediaType.parse("application/json"),
                ObjectExtensions.toJson(apiResponseErrorEntity)
        )));


        final Result<List<MenuSuggestedPortionResponseDTO>> result = menuRepository.getSuggestedPortions(
                portionIds,
                token
        );
        assertTrue(result instanceof Result.Error);
    }

}
