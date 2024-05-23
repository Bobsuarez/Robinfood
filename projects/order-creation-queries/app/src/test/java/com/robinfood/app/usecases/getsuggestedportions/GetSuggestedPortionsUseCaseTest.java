package com.robinfood.app.usecases.getsuggestedportions;

import com.robinfood.core.dtos.menu.MenuSuggestedPortionDataDTO;
import com.robinfood.core.dtos.menu.MenuSuggestedPortionResponseDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.repository.menu.IMenuRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetSuggestedPortionsUseCaseTest {

    @Mock
    private IMenuRepository mockMenuRepository;

    @InjectMocks
    private GetSuggestedPortionsUseCase getSuggestedPortionsUseCase;

    private final String token = "token";

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

    private final Result<List<MenuSuggestedPortionResponseDTO>> suggestedPortionsResult = new Result.Success<>(
            suggestedPortionResponseList
    );

    private final Result.Error suggestedPortionsResultError = new Result.Error(
            new Exception(), HttpStatus.INTERNAL_SERVER_ERROR
    );

    private final List<Long> portionIds = new ArrayList<>(Collections.singletonList(1L));

    @Test
    void test_GetSuggestedPortionsUseCase_ok() {
        when(mockMenuRepository.getSuggestedPortions(
                portionIds,
                token
        )).thenReturn(suggestedPortionsResult);

        Result<List<MenuSuggestedPortionResponseDTO>> result = getSuggestedPortionsUseCase.invoke(portionIds, token);

        verify(mockMenuRepository).getSuggestedPortions(
                portionIds,
                token
        );

        assertEquals(result, suggestedPortionsResult);
    }

    @Test
    void test_GetSuggestedPortionsUseCase_with_failure() {
        when(mockMenuRepository.getSuggestedPortions(
                portionIds,
                token
        )).thenReturn(suggestedPortionsResultError);

        Result<List<MenuSuggestedPortionResponseDTO>> result = getSuggestedPortionsUseCase.invoke(portionIds, token);

        verify(mockMenuRepository).getSuggestedPortions(
                portionIds,
                token
        );
        assertEquals(result, suggestedPortionsResultError);
    }
}
