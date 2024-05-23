package com.robinfood.app.usecases.completeorderproductsdata;

import com.robinfood.core.mocks.dto.OrderProductDeductionDTOMock;
import com.robinfood.app.usecases.getmenucurrent.IGetMenuCurrentUseCase;
import com.robinfood.core.dtos.MenuCurrentDTO;
import com.robinfood.core.dtos.MenuHallsDTO;
import com.robinfood.core.dtos.MenuProductDTO;
import com.robinfood.core.dtos.OrderDetailProductDTO;
import com.robinfood.core.dtos.OrderDetailProductDiscountDTO;
import com.robinfood.core.dtos.OrderDetailProductGroupDTO;
import com.robinfood.core.dtos.OrderDetailProductGroupPortionDTO;
import com.robinfood.core.dtos.OrderDetailProductTaxDTO;
import com.robinfood.core.dtos.OrderDetailRemovedPortionDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompleteOrderProductsDataFromMenuCurrentUseCaseTest {

    @Mock
    private IGetMenuCurrentUseCase mockMenuCurrentUseCase;

    @InjectMocks
    private CompleteOrderProductsDataFromMenuCurrentUseCase completeOrderProductsDataFromMenuCurrentUseCase;

    private final Long brandId = 1L;
    private final Long countryId = 1L;
    private final Long flowId = 1L;
    private final Long storeId = 1L;
    private final String token = "token";

    private final List<MenuProductDTO> menuProductDTOS = new ArrayList<>(Collections.singletonList(
            new MenuProductDTO(
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
    private final List<MenuProductDTO> menuProductEmptyDTOS = new ArrayList<>(Collections.singletonList(
            new MenuProductDTO(
                    1L,
                    "description",
                    new BigDecimal(1000),
                    1L,
                    1L,
                    "image",
                    "name",
                    3L,
                    1L,
                    new BigDecimal(1000),
                    "product flow",
                    3L,
                    "szu",
                    1L
            )
    ));
    private final List<MenuHallsDTO> menuHallsDTOS = new ArrayList<>(Collections.singletonList(
            new MenuHallsDTO(
                    1L,
                    menuProductDTOS,
                    "name",
                    1L
            )
    ));
    private final List<MenuHallsDTO> menuHallsEmptyDTOS = new ArrayList<>(Collections.singletonList(
            new MenuHallsDTO(
                    3L,
                    menuProductEmptyDTOS,
                    "name",
                    1L
            )
    ));

    private final MenuCurrentDTO menuCurrentDTO = new MenuCurrentDTO(
            menuHallsDTOS,
            "name"
    );

    private final MenuCurrentDTO menuCurrentEmptyDTO = new MenuCurrentDTO(
            menuHallsEmptyDTOS,
            "name"
    );

    private final List<OrderDetailProductGroupPortionDTO> orderDetailProductGroupPortionDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductGroupPortionDTO(
                            true,
                            null,
                            BigDecimal.ZERO,
                            1L,
                            "arroz",
                            1L,
                            8900.0,
                            1,
                            1,
                            "sku",
                            1L,
                            1.0
                    )
            )
    );

    private final List<OrderDetailRemovedPortionDTO> orderDetailRemovedPortionDTOS = Collections.singletonList(
            new OrderDetailRemovedPortionDTO(
                    1L,
                    "Rice"
            )
    );

    private final List<OrderDetailProductGroupDTO> orderDetailProductGroupDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductGroupDTO(
                            1L,
                            "ingredientes",
                            orderDetailProductGroupPortionDTOS,
                            orderDetailRemovedPortionDTOS,
                            "sku"
                    )
            )
    );

    private final List<OrderDetailProductTaxDTO> orderDetailProductTaxDTOS = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductTaxDTO(
                            1L,
                            1L,
                            0.0,
                            1L,
                            "IMPOCONSUMO",
                            0.0
                    )
            )
    );

    private final List<OrderDetailProductDiscountDTO> orderDetailProductDiscountDTOS = Collections.singletonList(
            new OrderDetailProductDiscountDTO(
                    1L,
                    1L,
                    0.0
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
                            BigDecimal.valueOf(1L),
                            BigDecimal.valueOf(0),
                            1L,
                            BigDecimal.valueOf(0),
                            orderDetailProductDiscountDTOS,
                            1L,
                            orderDetailProductGroupDTOS,
                            1L,
                            "image.png",
                            1L,
                            1L,
                            "paisa",
                            1,
                            1L,
                            "muy",
                            "sku",
                            orderDetailProductTaxDTOS,
                            new BigDecimal("8900.0"),
                            0.0
                    )
            )
    );



    @Test
    void test_CompleteOrderProductsDataFromMenuCurrentUseCase() {

        when(mockMenuCurrentUseCase.invoke(
                brandId,
                countryId,
                flowId,
                storeId,
                token
        )).thenReturn(menuCurrentDTO);

        completeOrderProductsDataFromMenuCurrentUseCase.invoke(
                countryId,
                flowId,
                orderDetailProductDTOS,
                storeId,
                token
        );

        verify(mockMenuCurrentUseCase).invoke(
                brandId,
                countryId,
                flowId,
                storeId,
                token
        );
    }

    @Test
    void test_CompleteOrderProductsDataFromMenuCurrentUseCase_No_Equals() {

        when(mockMenuCurrentUseCase.invoke(
                brandId,
                countryId,
                flowId,
                storeId,
                token
        )).thenReturn(menuCurrentDTO);
        orderDetailProductDTOS.get(0).setBrandId(0L);
        completeOrderProductsDataFromMenuCurrentUseCase.invoke(
                countryId,
                flowId,
                orderDetailProductDTOS,
                storeId,
                token
        );

        verify(mockMenuCurrentUseCase).invoke(
                brandId,
                countryId,
                flowId,
                storeId,
                token
        );
    }

    @Test
    void test_CompleteOrderProductsDataFromMenuCurrentUseCase_EmpyList() {

        when(mockMenuCurrentUseCase.invoke(
                brandId,
                countryId,
                flowId,
                storeId,
                token
        )).thenReturn(menuCurrentEmptyDTO);


        assertThrows(ResponseStatusException.class, () -> {
            completeOrderProductsDataFromMenuCurrentUseCase.invoke(
                    countryId,
                    flowId,
                    orderDetailProductDTOS,
                    storeId,
                    token
            );
        });
    }

    @Test
    void test_Set_Id_Product_Menu_Error() {

        orderDetailProductDTOS.get(0).setArticleId(2L);

        try {
            when(mockMenuCurrentUseCase.invoke(
                    brandId,
                    countryId,
                    flowId,
                    storeId,
                    token
            )).thenReturn(menuCurrentDTO);

            completeOrderProductsDataFromMenuCurrentUseCase.invoke(
                    countryId,
                    flowId,
                    orderDetailProductDTOS,
                    storeId,
                    token
            );
        } catch (Exception e) {
            assertEquals(
                    "class org.springframework.web.server.ResponseStatusException",
                    e.getClass().toString()
            );
        }
    }
}
