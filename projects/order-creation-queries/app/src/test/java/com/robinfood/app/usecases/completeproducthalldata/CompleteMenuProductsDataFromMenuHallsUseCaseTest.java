package com.robinfood.app.usecases.completeproducthalldata;

import com.robinfood.app.usecases.completereplacementportionsdata.ICompleteReplacementPortionsDataUseCase;
import com.robinfood.core.mocks.dto.OrderProductDeductionDTOMock;
import com.robinfood.app.usecases.getmenuproducts.IGetMenuProductsUseCase;
import com.robinfood.core.dtos.OrderDetailChangedPortionDTO;
import com.robinfood.core.dtos.OrderDetailProductDTO;
import com.robinfood.core.dtos.OrderDetailProductGroupDTO;
import com.robinfood.core.dtos.OrderDetailProductGroupPortionDTO;
import com.robinfood.core.dtos.menu.MenuGroupPortionDTO;
import com.robinfood.core.dtos.menu.MenuHallProductResponseDTO;
import com.robinfood.core.dtos.menu.MenuProductGroupDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompleteMenuProductsDataFromMenuHallsUseCaseTest {

    @Mock
    private IGetMenuProductsUseCase mockGetMenuProductsUseCase;

    @Mock
    private ICompleteReplacementPortionsDataUseCase completeReplacementPortionsDataUseCase;

    @InjectMocks
    private CompleteMenuProductsDataFromMenuHallsUseCase completeMenuProductsDataFromMenuHallsUseCase;

    private final Long countryId = 1L;
    private final Long flowId = 1L;
    private final Long storeId = 1L;
    private final String token = "token";

    private final List<MenuGroupPortionDTO> menuHallProductResponseGroupPortions = new ArrayList<>(
            Collections.singletonList(
                    new MenuGroupPortionDTO(
                            true,
                            BigDecimal.ZERO,
                            1L,
                            "portion.png",
                            "portion",
                            1L,
                            BigDecimal.ZERO,
                            BigDecimal.ZERO,
                            1,
                            "sku",
                            1L,
                            0.0

                    )
            )
    );

    private final List<MenuProductGroupDTO> menuHallProductResponseGroups = new ArrayList<>(
            Collections.singletonList(
                    new MenuProductGroupDTO(
                            0,
                            1L,
                            1L,
                            5,
                            0,
                            "group",
                            "group",
                            menuHallProductResponseGroupPortions,
                            "group",
                            "group",
                            "sku",
                            0
                    )
            )
    );

    private final MenuHallProductResponseDTO menuHallProductResponseDTO = new MenuHallProductResponseDTO(
            1L,
            1L,
            "Menu hall product description",
            null,
            1L,
            menuHallProductResponseGroups,
            1L,
            "image.png",
            "Menu hall product",
            1L,
            1,
            BigDecimal.valueOf(8900.0),
            "Product flow",
            1L,
            "sku",
            Collections.emptyList(),
            1L,
            "typeName"
    );

    private final List<OrderDetailProductGroupPortionDTO> orderDetailProductGroupPortions = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductGroupPortionDTO(
                            true,
                            null,
                            BigDecimal.ZERO,
                            1L,
                            "portion",
                            1L,
                            0.0,
                            1,
                            0,
                            "sku",
                            1L,
                            0.0
                    )
            )
    );

    private final OrderDetailChangedPortionDTO orderDetailChangedPortionDTO = new OrderDetailChangedPortionDTO(
            1L,"",1L,"",1L,1.0
    );

    private final List<OrderDetailProductGroupPortionDTO> orderDetailProductGroupPortionsEmpty = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductGroupPortionDTO(
                            true,
                            orderDetailChangedPortionDTO,
                            BigDecimal.ZERO,
                            1L,
                            "portion",
                            1L,
                            0.0,
                            1,
                            0,
                            "sku",
                            1L,
                            0.0
                    )
            )
    );

    private final List<OrderDetailProductGroupDTO> orderDetailProductGroups = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductGroupDTO(
                            1L,
                           "Group 1",
                            orderDetailProductGroupPortions,
                            Collections.emptyList(),
                           "sku"
                    )
            )
    );

    private final List<OrderDetailProductGroupDTO> orderDetailProductGroupsEmpty = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductGroupDTO(
                            1L,
                            "Group 1",
                            orderDetailProductGroupPortionsEmpty,
                            Collections.emptyList(),
                            "sku"
                    )
            )
    );

    private final List<OrderDetailProductDTO> orderDetailProductDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductDTO(
                            1L,
                            "Muy paisa",
                            1L,
                            8900.0,
                            1L,
                            "muy",
                            1L,
                            "Category",
                            null,
                            BigDecimal.valueOf(0),
                            1L,
                            BigDecimal.valueOf(0),
                            null,
                            1L,
                            orderDetailProductGroups,
                            1L,
                            "image.png",
                            1L,
                            1L,
                            "paisa",
                            1,
                            1L,
                            "muy",
                            "sku",
                            null,
                            new BigDecimal("8900.0"),
                            0.0
                    )
            )
    );

    private final List<OrderDetailProductDTO> orderDetailProductEmptyDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductDTO(
                            1L,
                            "Muy paisa",
                            1L,
                            8900.0,
                            1L,
                            "muy",
                            1L,
                            "Category",
                            null,
                            BigDecimal.valueOf(0),
                            1L,
                            BigDecimal.valueOf(0),
                            null,
                            1L,
                            orderDetailProductGroupsEmpty,
                            1L,
                            "image.png",
                            1L,
                            1L,
                            "paisa",
                            1,
                            1L,
                            "muy",
                            "sku",
                            null,
                            new BigDecimal("8900.0"),
                            0.0
                    )
            )
    );

    private final List<OrderDetailProductDTO> orderDetailProductDTOSWithNoGroups = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductDTO(
                            1L,
                            "Muy paisa",
                            1L,
                            8900.0,
                            1L,
                            "muy",
                            1L,
                            "Category",
                            null,
                            BigDecimal.valueOf(0),
                            1L,
                            BigDecimal.valueOf(0),
                            null,
                            1L,
                            orderDetailProductGroups,
                            1L,
                            "image.png",
                            1L,
                            1L,
                            "paisa",
                            1,
                            1L,
                            "muy",
                            "sku",
                            null,
                            new BigDecimal("8900.0"),
                            0.0
                    )
            )
    );

    @Test
    void test_CompleteMenuProductsDataFromMenuHallsUseCase_ok() {

        orderDetailProductDTOS.forEach(
                (OrderDetailProductDTO orderDetailProduct) -> when(mockGetMenuProductsUseCase.invoke(
                        orderDetailProduct.getBrandId(),
                        countryId,
                        flowId,
                        Collections.singletonList(orderDetailProduct.getArticleId()),
                        storeId,
                        token
                )).thenReturn(Collections.singletonList(menuHallProductResponseDTO))
        );

        completeMenuProductsDataFromMenuHallsUseCase.invoke(
                countryId,
                flowId,
                orderDetailProductDTOS,
                storeId,
                token
        );

        orderDetailProductDTOS.forEach(
                (OrderDetailProductDTO orderDetailProduct) -> verify(mockGetMenuProductsUseCase).invoke(
                        orderDetailProduct.getBrandId(),
                        countryId,
                        flowId,
                        Collections.singletonList(orderDetailProduct.getArticleId()),
                        storeId,
                        token
                )
        );
    }

    @Test
    void test_CompleteMenuProductsDataFromMenuHallsUseCase_ok_replacements() {

        orderDetailProductDTOS.forEach(
                (OrderDetailProductDTO orderDetailProduct) -> when(mockGetMenuProductsUseCase.invoke(
                        orderDetailProduct.getBrandId(),
                        countryId,
                        flowId,
                        Collections.singletonList(orderDetailProduct.getArticleId()),
                        storeId,
                        token
                )).thenReturn(Collections.singletonList(menuHallProductResponseDTO))
        );

        completeMenuProductsDataFromMenuHallsUseCase.invoke(
                countryId,
                flowId,
                orderDetailProductEmptyDTOS,
                storeId,
                token
        );

        orderDetailProductDTOS.forEach(
                (OrderDetailProductDTO orderDetailProduct) -> verify(mockGetMenuProductsUseCase).invoke(
                        orderDetailProduct.getBrandId(),
                        countryId,
                        flowId,
                        Collections.singletonList(orderDetailProduct.getArticleId()),
                        storeId,
                        token
                )
        );
    }

    @Test
    void test_CompleteMenuProductsDataFromMenuHallsUseCase_ok_when_groups_are_null() {


        orderDetailProductDTOSWithNoGroups.forEach(
                (OrderDetailProductDTO orderDetailProduct) -> when(mockGetMenuProductsUseCase.invoke(
                        orderDetailProduct.getBrandId(),
                        countryId,
                        flowId,
                        Collections.singletonList(orderDetailProduct.getArticleId()),
                        storeId,
                        token
                )).thenReturn(Collections.singletonList(menuHallProductResponseDTO))
        );

        completeMenuProductsDataFromMenuHallsUseCase.invoke(
                countryId,
                flowId,
                orderDetailProductDTOSWithNoGroups,
                storeId,
                token
        );

        orderDetailProductDTOS.forEach(
                (OrderDetailProductDTO orderDetailProduct) -> verify(mockGetMenuProductsUseCase).invoke(
                        orderDetailProduct.getBrandId(),
                        countryId,
                        flowId,
                        Collections.singletonList(orderDetailProduct.getArticleId()),
                        storeId,
                        token
                )
        );
    }
}
