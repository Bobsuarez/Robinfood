package com.robinfood.app.usecases.getmenucurrent;

import com.robinfood.core.dtos.MenuCurrentDTO;
import com.robinfood.core.dtos.MenuHallsDTO;
import com.robinfood.core.dtos.MenuProductDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.repository.menu.IMenuRepository;
import org.junit.jupiter.api.Assertions;
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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GetMenuCurrentUseCaseTest {

    @Mock
    private IMenuRepository mockMenuCurrentRepository;

    @InjectMocks
    private GetMenuCurrentUseCase getMenuCurrentUseCase;

    private final Long brandId = 1L;
    private final Long countryId = 1L;
    private final Long flowId = 1L;
    private final Long storeId = 1L;
    private final String token = "token";

    private final List<MenuProductDTO> menuProductDTOS = new ArrayList<>(Arrays.asList(
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

    private final List<MenuHallsDTO> menuHallsDTOS = new ArrayList<>(Arrays.asList(
            new MenuHallsDTO(
                    1L,
                    menuProductDTOS,
                    "name",
                    1L
            )
    ));

    private final MenuCurrentDTO menuCurrentDTO = new MenuCurrentDTO(
            menuHallsDTOS,
            "name"
    );

    @Test
    void test_Menu_Current_Returns_Correctly() {
        Mockito.when(mockMenuCurrentRepository.getMenuCurrent(
                brandId,
                countryId,
                flowId,
                storeId,
                token
        )).thenReturn(new Result.Success<>(menuCurrentDTO));

        final MenuCurrentDTO result = getMenuCurrentUseCase.invoke(
                brandId,
                countryId,
                flowId,
                storeId,
                token
        );

        Assertions.assertEquals(menuCurrentDTO, result);
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @Test
    void test_Menu_Current_Error() {
        try {
            Mockito.when(mockMenuCurrentRepository.getMenuCurrent(
                    brandId,
                    countryId,
                    flowId,
                    storeId,
                    token
            )).thenReturn(new Result.Error(new Exception("Some error"), HttpStatus.INTERNAL_SERVER_ERROR));

            final MenuCurrentDTO result = getMenuCurrentUseCase.invoke(
                    brandId,
                    countryId,
                    flowId,
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
