package com.robinfood.storeor.repositories.posconfigurationsbcrepository;

import com.robinfood.storeor.configs.apis.ConfigurationsBcApi;
import com.robinfood.storeor.configs.apis.OrdersPosResolutionsApi;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.PosEntity;
import com.robinfood.storeor.entities.StoreCreatePosEntity;
import com.robinfood.storeor.entities.configurationposbystore.ActivateOrDeactivatePosEntity;
import com.robinfood.storeor.mocks.dto.PosDTOMock;
import com.robinfood.storeor.mocks.dto.PosEntityMock;
import com.robinfood.storeor.mocks.entity.pos.ActivateOrDeactivatePosEntityMock;
import com.robinfood.storeor.mocks.entity.pos.StoreCreatePosEntityMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PosConfigurationsBcRepositoryTest {

    @Mock
    private ConfigurationsBcApi configurationsBcApi;

    @Mock
    private OrdersPosResolutionsApi ordersPosResolutionsApi;

    @InjectMocks
    private PosConfigurationsBcRepository posConfigurationsBcRepository;

    final ActivateOrDeactivatePosEntity activateOrDeactivateEntity = new ActivateOrDeactivatePosEntityMock()
            .defaultData();

    APIResponseEntity<StoreCreatePosEntity> apiCreatePosStatusTrueResponse = new APIResponseEntity<>(
            200, StoreCreatePosEntityMock.getDataDefault(true),
            "2022-12-13T21:31:57.837443Z",
            "Configuration Pos by Store and User successfully",
            "OK"
    );

    APIResponseEntity<StoreCreatePosEntity> apiCreatePosWithResolutionStatusTrueResponse = new APIResponseEntity<>(
            200, StoreCreatePosEntityMock.getStoreCreatePosWithResolution(true),
            "2022-12-13T21:31:57.837443Z",
            "Configuration Pos by Store and User successfully",
            "OK"
    );

    APIResponseEntity<StoreCreatePosEntity> apiCreatePosWithoutResolutionStatusTrueResponse = new APIResponseEntity<>(
            200, StoreCreatePosEntityMock.getStoreCreatePosWithoutResolution(true),
            "2022-12-13T21:31:57.837443Z",
            "Configuration Pos by Store and User successfully",
            "OK"
    );

    APIResponseEntity<StoreCreatePosEntity> apiCreatePosWithDataIsNullStatusTrueResponse = new APIResponseEntity<>(
            200, null,
            "2022-12-13T21:31:57.837443Z",
            "Configuration Pos by Store and User successfully",
            "OK"
    );

    APIResponseEntity<Object> apiResolutionUpdateResponse = new APIResponseEntity<>(
            200, null,
            "2022-12-13T21:31:57.837443Z",
            "Configuration Pos by Store and User successfully",
            "OK"
    );

    APIResponseEntity<PosEntity> apiResponseAccept = new APIResponseEntity<>(
            202,
            PosEntityMock.getDataDefault(),
            "2023-08-11T21:31:57.837443Z",
            "update successfully",
            "OK"
    );

    APIResponseEntity<Object> apiUpdatedActiveResponse = new APIResponseEntity<>(
            202,
            null,
            "2023-08-11T21:31:57.837443Z",
            "Resolutions update successfully",
            "OK"
    );

    private final String TOKEN = "token";

    @Test
    void test_CreatePos_Status_True_Returns_Successfully() throws Exception {

        final String token = "token";
        final StoreCreatePosEntity storeCreatePosEntity = StoreCreatePosEntityMock.getDataDefault(true);

        when(configurationsBcApi.createPos(storeCreatePosEntity, token)).thenReturn(apiCreatePosStatusTrueResponse);

        final APIResponseEntity<StoreCreatePosEntity> responseEntity = posConfigurationsBcRepository
                .createPosConfiguration(
                        token,
                        storeCreatePosEntity
                );

        assertEquals(responseEntity, apiCreatePosStatusTrueResponse);
    }

    @Test
    void test_CreatePos_Without_Resolution_Status_True_Returns_Successfully() throws Exception {

        final String token = "token";
        final StoreCreatePosEntity storeCreatePosEntity = StoreCreatePosEntityMock.getDataDefault(true);

        when(configurationsBcApi.createPos(storeCreatePosEntity, token)).thenReturn(apiCreatePosWithoutResolutionStatusTrueResponse);

        final APIResponseEntity<StoreCreatePosEntity> responseEntity = posConfigurationsBcRepository
                .createPosConfiguration(
                        token,
                        storeCreatePosEntity
                );

        assertEquals(responseEntity, apiCreatePosWithoutResolutionStatusTrueResponse);
    }

    @Test
    void test_CreatePos_With_Data_Is_Null_True_Returns_Successfully() throws Exception {

        final String token = "token";
        final StoreCreatePosEntity storeCreatePosEntity = StoreCreatePosEntityMock.getDataDefault(true);

        when(configurationsBcApi.createPos(storeCreatePosEntity, token)).thenReturn(apiCreatePosWithDataIsNullStatusTrueResponse);

        final APIResponseEntity<StoreCreatePosEntity> responseEntity = posConfigurationsBcRepository
                .createPosConfiguration(
                        token,
                        storeCreatePosEntity
                );

        assertEquals(responseEntity, apiCreatePosWithDataIsNullStatusTrueResponse);
    }



    @Test
    void test_CreatePos_With_Resolution_Status_True_Returns_Successfully() throws Exception {

        final String token = "token";
        final StoreCreatePosEntity storeCreatePosEntity = StoreCreatePosEntityMock.getStoreCreatePosWithResolution(true);
        final Long posId = 1L;
        final Long resolutionId = 1L;

        when(configurationsBcApi.createPos(eq(storeCreatePosEntity), eq(token))).thenReturn(apiCreatePosWithResolutionStatusTrueResponse);

        when(ordersPosResolutionsApi.updateResolutionWithPos(eq(resolutionId), eq(posId), eq(token))).thenReturn(apiResolutionUpdateResponse);

        final APIResponseEntity<StoreCreatePosEntity> responseEntity = posConfigurationsBcRepository.createPosConfiguration(token, storeCreatePosEntity);

        verify(configurationsBcApi).createPos(eq(storeCreatePosEntity), eq(token));

        verify(ordersPosResolutionsApi).updateResolutionWithPos(eq(resolutionId), eq(posId), eq(token));

        assertEquals(apiCreatePosWithResolutionStatusTrueResponse, responseEntity);
    }

    @Test
    void test_IPosConfigurationsBcRepository_Invoke_Returns_Correctly() {

        when(configurationsBcApi.updatePos(any(), anyLong(), anyString()))
                .thenReturn(apiResponseAccept);

        APIResponseEntity<PosEntity> responseApi =
                posConfigurationsBcRepository.updatePosConfigurationBc(PosDTOMock.getDataDefault(), 1L, TOKEN);

        Assertions.assertEquals(PosEntityMock.getDataDefault(), responseApi.getData());
    }

    @Test
    void test_Update_Pos_Whit_Resolution_Invoke_Returns_Correctly() {

        when(configurationsBcApi.updatePos(any(), anyLong(), anyString()))
                .thenReturn(apiResponseAccept);

        when(ordersPosResolutionsApi.updateResolutionWithPos(any(), any(), anyString())).thenReturn(apiResolutionUpdateResponse);

        APIResponseEntity<PosEntity> responseApi =
                posConfigurationsBcRepository.updatePosConfigurationBc(PosDTOMock.getPosDTOWithResolution(), 1L, TOKEN);

        verify(configurationsBcApi).updatePos(any(), anyLong(), anyString());

        verify(ordersPosResolutionsApi).updateResolutionWithPos(any(), any(), anyString());

        Assertions.assertEquals(PosEntityMock.getDataDefault(), responseApi.getData());
    }

    @Test
    void test_Update_Pos_Whit_Out_Resolution_Invoke_Returns_Correctly() {

        when(configurationsBcApi.updatePos(any(), anyLong(), anyString()))
                .thenReturn(apiResponseAccept);

        lenient().when(ordersPosResolutionsApi.updateResolutionWithPos(any(), any(), anyString())).thenReturn(apiResolutionUpdateResponse);

        APIResponseEntity<PosEntity> responseApi =
                posConfigurationsBcRepository.updatePosConfigurationBc(PosDTOMock.getPosDTOWithOutResolution(), 1L, TOKEN);

        verify(configurationsBcApi).updatePos(any(), anyLong(), anyString());

        Assertions.assertEquals(PosEntityMock.getDataDefault(), responseApi.getData());
    }

    @Test
    void test_PosRepository_activateOrDeactivatePos_Returns_Successfully() {

        when(configurationsBcApi.activateOrDeactivatePos(activateOrDeactivateEntity,
                1L, TOKEN))
                .thenReturn(apiUpdatedActiveResponse);

        final APIResponseEntity<Object>
                apiResponseEntity = posConfigurationsBcRepository.activateOrDeactivatePosConfigurations(
                activateOrDeactivateEntity,
                1L,
                TOKEN
        );

        assertEquals(202, apiResponseEntity.getCode());
    }
}
