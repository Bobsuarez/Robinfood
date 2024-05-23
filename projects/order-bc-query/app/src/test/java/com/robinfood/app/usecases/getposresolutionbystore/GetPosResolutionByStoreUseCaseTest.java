package com.robinfood.app.usecases.getposresolutionbystore;

import com.robinfood.app.datamocks.dto.output.PosResolutionDTOMock;
import com.robinfood.app.datamocks.entity.PosResolutionEntityMock;
import com.robinfood.app.usecases.getposresolution.IGetPosResolutionUseCase;
import com.robinfood.core.dtos.posresolution.DataPosResolutionRequestDTO;
import com.robinfood.core.dtos.posresolution.GetPosResolutionsDTO;
import com.robinfood.core.exceptions.GenericOrderBcException;
import com.robinfood.repository.orderpos.IOrderPosRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GetPosResolutionByStoreUseCaseTest {

    @Mock
    private IOrderPosRepository orderPosRepository;

    @Mock
    private IGetPosResolutionUseCase posResolutionUseCase;

    @InjectMocks
    private GetPosResolutionByStoreUseCase getPosResolutionByStoreUseCase;

    @Test
    void test_GetDataResponseByStore_Should_Ok_When_DataPosResolution() {

        when(orderPosRepository
                .findByStoreIdAndCurrent(dataRequestDTO().getStoreId(), DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT))
                .thenReturn(Optional.of(List.of(PosResolutionEntityMock.getDataDefault())));

        when(posResolutionUseCase.invoke(any(DataPosResolutionRequestDTO.class)))
                .thenReturn(PosResolutionDTOMock.getDataDefault());

        List<GetPosResolutionsDTO> responseInvoke = getPosResolutionByStoreUseCase.invoke(dataRequestDTO());

        assertThat(responseInvoke.size(), is(1));
    }

    @Test
    void test_GetDataResponseByStore_Should_EmptyList_When_DataPostResolution_EffectiveAndCanceledIsZero() {

        GetPosResolutionsDTO dataResponsePosEntity = PosResolutionDTOMock.getDataDefault();
        dataResponsePosEntity.setCancelledInvoices(0L);
        dataResponsePosEntity.setEffectiveInvoices(0L);

        when(orderPosRepository
                .findByStoreIdAndCurrent(dataRequestDTO().getStoreId(), DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT))
                .thenReturn(Optional.of(List.of(PosResolutionEntityMock.getDataDefault())));

        when(posResolutionUseCase.invoke(any(DataPosResolutionRequestDTO.class)))
                .thenReturn(dataResponsePosEntity);

        List<GetPosResolutionsDTO> responseInvoke = getPosResolutionByStoreUseCase.invoke(dataRequestDTO());

        assertThat(responseInvoke.size(), is(0));
    }

    @Test
    void test_GetDataResponseByStore_Should_BadException_When_DataOrderRepositoryNull() {

        when(orderPosRepository
                .findByStoreIdAndCurrent(dataRequestDTO().getStoreId(), DEFAULT_BOOLEAN_TRUE_VALUE_AS_INT))
                .thenReturn(Optional.ofNullable(null));

        GenericOrderBcException exception = assertThrows(GenericOrderBcException.class, () -> {
            getPosResolutionByStoreUseCase.invoke(dataRequestDTO());
        });

        assertEquals("Pos resolution not found of the store :1", exception.getMessage());
    }

    private DataPosResolutionRequestDTO dataRequestDTO() {
        var localDateStart = LocalDate.parse("2023-01-15");
        var localDateEnd = LocalDate.parse("2023-01-16");
        return new DataPosResolutionRequestDTO(localDateStart, localDateEnd, 1L, 1L, "America/Bogota");
    }
}
