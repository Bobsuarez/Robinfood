package com.robinfood.usecases.findallresolutions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.dtos.v1.response.searchResolutions.DataResolutionResponseDTO;
import com.robinfood.entities.db.mysql.PosResolutionEntity;
import com.robinfood.enums.OrderByEnum;
import com.robinfood.mocks.dtos.v1.request.SearchResolutionsDTOMock;
import com.robinfood.mocks.entities.PosResolutionEntityMock;
import com.robinfood.repository.posresolution.IPosResolutionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class FindAllResolutionsUseCaseTest {

    @Mock
    private IPosResolutionRepository posResolutionRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private FindAllResolutionsUseCase findAllResolutionsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private final String valueCustomFilter = "Resolution";

    @Test
    void test_GetInstance_When_DataOK_Should_Successfully() {

        FindAllResolutionsUseCase useCaseInstanceSecond = FindAllResolutionsUseCase.getInstance();
        FindAllResolutionsUseCase useCaseInstanceThird = FindAllResolutionsUseCase.getInstance();

        assertSame(useCaseInstanceSecond, useCaseInstanceThird);
    }

    @Test
    void test_Invoke_When_DataOK_Should_Successfully() {

        PosResolutionEntity posResolutionEntity1 = PosResolutionEntityMock.build();
        posResolutionEntity1.setInitial_date(new Date());
        posResolutionEntity1.setEnd_date(new Date());

        PosResolutionEntity posResolutionEntity2 = PosResolutionEntityMock.build();
        posResolutionEntity2.setPos_id(null);
        posResolutionEntity2.setInitial_date(new Date());
        posResolutionEntity2.setEnd_date(new Date());

        when(posResolutionRepository.countSearchResolutions(null, Boolean.TRUE, valueCustomFilter, null)).thenReturn(10);

        when(posResolutionRepository.searchResolutions(
                Boolean.TRUE,
                OrderByEnum.ASC,
                1,
                null,
                10,
                valueCustomFilter,
                null))
            .thenReturn(List.of(posResolutionEntity1, posResolutionEntity2));

        DataResolutionResponseDTO dataResolutionResponse = findAllResolutionsUseCase.invoke(
                SearchResolutionsDTOMock.build());

        assertNotNull(dataResolutionResponse);
    }
}
