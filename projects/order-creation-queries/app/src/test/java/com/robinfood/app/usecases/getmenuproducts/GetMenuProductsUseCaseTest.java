package com.robinfood.app.usecases.getmenuproducts;

import com.robinfood.core.dtos.menu.MenuHallProductResponseDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.repository.menu.IMenuRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GetMenuProductsUseCaseTest {

    @Mock
    private IMenuRepository mockMenuRepository;

    @InjectMocks
    private GetMenuProductsUseCase getMenuProductsUseCase;

    private final Long brandId = 1L;
    private final Long countryId = 1L;
    private final Long flowId = 1L;
    private final List<Long> productsIds = new ArrayList<>(Collections.singletonList(1L));
    private final Long storeId = 1L;
    private final String token = "token";

    private final MenuHallProductResponseDTO menuHallProductResponse = new MenuHallProductResponseDTO(
            1L,
            1L,
            "Description",
            BigDecimal.ZERO,
            1L,
            null,
            1L,
            "image.png",
            "Product",
            1L,
            1,
            BigDecimal.valueOf(8900.0),
            "productFlow",
            1L,
            "sku",
            Collections.emptyList(),
            1L,
            "ARTICLE"
    );

    @Test
    void tet_getMenuProductsUseCase_ok() {
        Mockito.when(mockMenuRepository.getProductDetail(
                brandId,
                countryId,
                flowId,
                productsIds.get(0),
                storeId,
                token
        )).thenReturn(new Result.Success<>(menuHallProductResponse));

        final List<MenuHallProductResponseDTO> result = getMenuProductsUseCase.invoke(
                brandId,
                countryId,
                flowId,
                productsIds,
                storeId,
                token
        );

        assertEquals(Collections.singletonList(menuHallProductResponse), result);
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @Test
    void tet_getMenuProductsUseCase_Error() {
        try {
            Mockito.when(mockMenuRepository.getProductDetail(
                    brandId,
                    countryId,
                    flowId,
                    productsIds.get(0),
                    storeId,
                    token
            )).thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.INTERNAL_SERVER_ERROR));

            final List<MenuHallProductResponseDTO> result = getMenuProductsUseCase.invoke(
                    brandId,
                    countryId,
                    flowId,
                    productsIds,
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
