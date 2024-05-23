package com.robinfood.app.usecases.completereplacementportionsdata;

import com.robinfood.app.usecases.getsuggestedportions.IGetSuggestedPortionsUseCase;
import com.robinfood.core.dtos.OrderDetailChangedPortionDTO;
import com.robinfood.core.dtos.OrderDetailProductGroupPortionDTO;
import com.robinfood.core.dtos.menu.MenuSuggestedPortionDataDTO;
import com.robinfood.core.dtos.menu.MenuSuggestedPortionResponseDTO;
import com.robinfood.core.enums.Result;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompleteReplacementPortionsDataUseCaseTest {

    @Mock
    private IGetSuggestedPortionsUseCase mockGetSuggestedPortionsUseCase;

    @InjectMocks
    private CompleteReplacementPortionsDataUseCase completeReplacementPortionsDataUseCase;

    private final String token = "token";

    private final OrderDetailChangedPortionDTO changedPortionDTO = new OrderDetailChangedPortionDTO(
            2L,
            "Changed portion",
            1L,
            null,
            null,
            null
    );

    private final List<OrderDetailProductGroupPortionDTO> portions = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductGroupPortionDTO(
                            false,
                            changedPortionDTO,
                         BigDecimal.ZERO,
                         1L,
                         "Portion Name",
                         1L,
                            0.0,
                         1,
                         1,
                         "sku",
                         1L,
                         80.0
                    )
            )
    );
    private final List<OrderDetailProductGroupPortionDTO> portionsNull = new ArrayList<>(
            Collections.singletonList(
                    new OrderDetailProductGroupPortionDTO(
                            false,
                            changedPortionDTO,
                            BigDecimal.ZERO,
                            3L,
                            "Portion Name",
                            1L,
                            0.0,
                            1,
                            1,
                            "sku",
                            1L,
                            80.0
                    )
            )
    );

    private final List<MenuSuggestedPortionDataDTO> portionChanges = new ArrayList<>(
            Collections.singletonList(
                    new MenuSuggestedPortionDataDTO(
                            2L,
                            "image",
                            "Changed Portion",
                            1L,
                            "sku",
                            1L,
                            70.0
                    )
            )
    );

    private final List<MenuSuggestedPortionResponseDTO> suggestedPortionResponseList = new ArrayList<>(
            Collections.singletonList(
                    new MenuSuggestedPortionResponseDTO(
                            portionChanges,
                            1L,
                            "image",
                            "Portion Name",
                            1L,
                            "sku"
                    )
            )
    );

    private final List<MenuSuggestedPortionResponseDTO> suggestedPortionResponseListWithEmptyChanges = new ArrayList<>(
            Collections.singletonList(
                    new MenuSuggestedPortionResponseDTO(
                            Collections.emptyList(),
                            1L,
                            "image",
                            "Portion Name",
                            1L,
                            "sku"
                    )
            )
    );

    private final  Result<List<MenuSuggestedPortionResponseDTO>> suggestedPortionsResult = new Result.Success<>(
            suggestedPortionResponseList
    );

    private final  Result<List<MenuSuggestedPortionResponseDTO>> suggestedPortionsResultEmpty = new Result.Success<>(
            suggestedPortionResponseListWithEmptyChanges
    );

    private final Result.Error suggestedPortionsResultError = new Result.Error(
            new Exception(), HttpStatus.INTERNAL_SERVER_ERROR
    );

    private final List<Long> portionIds = portions
            .stream()
            .map(OrderDetailProductGroupPortionDTO::getId).collect(Collectors.toList());

    private final List<Long> portionIdsNull = portionsNull
            .stream()
            .map(OrderDetailProductGroupPortionDTO::getId).collect(Collectors.toList());

    @Test
    void test_CompleteReplacementPortionsDataUseCase_ok() {
        when(mockGetSuggestedPortionsUseCase.invoke(
                portionIds,
                token
        )).thenReturn(suggestedPortionsResult);

        completeReplacementPortionsDataUseCase.invoke(portions, token);

        verify(mockGetSuggestedPortionsUseCase).invoke(
                portionIds,
                token
        );
    }

    @Test
    void test_CompleteReplacementPortionsDataUseCase_ok_portions_null() {
        when(mockGetSuggestedPortionsUseCase.invoke(
                portionIdsNull,
                token
        )).thenReturn(suggestedPortionsResult);

        completeReplacementPortionsDataUseCase.invoke(portionsNull, token);

        verify(mockGetSuggestedPortionsUseCase).invoke(
                portionIdsNull,
                token
        );
    }

    @Test
    void test_CompleteReplacementPortionsDataUseCase_with_failure() {
        try {
            when(mockGetSuggestedPortionsUseCase.invoke(
                    portionIds,
                    token
            )).thenReturn(suggestedPortionsResultError);

            completeReplacementPortionsDataUseCase.invoke(portions, token);
        } catch (ResponseStatusException e) {
            assertEquals(
                    HttpStatus.NOT_FOUND,
                    e.getStatus()
            );
        }
    }

    @Test
    void test_CompleteReplacementPortionsDataUseCase_not_found_portions_in_menu() {
        try {
            when(mockGetSuggestedPortionsUseCase.invoke(
                    portionIds,
                    token
            )).thenReturn(suggestedPortionsResultEmpty);

            completeReplacementPortionsDataUseCase.invoke(portions, token);
        } catch (ResponseStatusException e) {
            assertEquals(
                    HttpStatus.NOT_FOUND,
                    e.getStatus()
            );
        }
    }
}
