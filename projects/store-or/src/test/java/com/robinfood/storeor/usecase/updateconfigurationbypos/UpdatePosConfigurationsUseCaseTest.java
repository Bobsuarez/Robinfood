package com.robinfood.storeor.usecase.updateconfigurationbypos;

import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.PosEntity;
import com.robinfood.storeor.exceptions.restexceptionhandlers.PosException;
import com.robinfood.storeor.mocks.dto.PosDTOMock;
import com.robinfood.storeor.mocks.dto.PosEntityMock;
import com.robinfood.storeor.repositories.posconfigurationsbcrepository.IPosConfigurationsBcRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.robinfood.storeor.configs.constants.APIConstants.ERROR_UPDATE_CONFIGURATIONS_BC;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdatePosConfigurationsUseCaseTest {

    @Mock
    private IPosConfigurationsBcRepository posConfigurationsBcRepository;

    @InjectMocks
    private UpdatePosConfigurationsUseCase updatePosConfigurationsUseCase;

    private final String TOKEN = "token";

    APIResponseEntity<PosEntity> apiResponseAccept = new APIResponseEntity<>(
            202,
            PosEntityMock.getDataDefault(),
            "2023-08-11T21:31:57.837443Z",
            "update successfully",
            "OK"
    );

    APIResponseEntity<PosEntity> apiResponseCreated = new APIResponseEntity<>(
            201,
            PosEntityMock.getDataDefault(),
            "2023-08-11T21:31:57.837443Z",
            "update successfully",
            "OK"
    );

    @Test
    void test_UpdatePosConfigurationsUseCase_When_DataOK_Should_Returns_Correctly() throws PosException {

        when(posConfigurationsBcRepository.updatePosConfigurationBc(any(), anyLong(), anyString()))
                .thenReturn(apiResponseAccept);

        updatePosConfigurationsUseCase.invoke(1L, PosDTOMock.getDataDefault(), TOKEN);

        verify(posConfigurationsBcRepository).updatePosConfigurationBc(any(), anyLong(), anyString());
    }

    @Test
    void test_UpdatePosConfigurations_When_ConfigurationsResponseCreated_Should_PosException() throws PosException {

        when(posConfigurationsBcRepository.updatePosConfigurationBc(any(), anyLong(), anyString()))
                .thenReturn(apiResponseCreated);

        PosException posException = assertThrows(PosException.class, () ->
                updatePosConfigurationsUseCase.invoke(1L, PosDTOMock.getDataDefault(), TOKEN)
        );

        assertEquals(posException.getMessage(), ERROR_UPDATE_CONFIGURATIONS_BC + "update successfully");
    }
}
