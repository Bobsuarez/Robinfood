package com.robinfood.storeor.usecase.getbillingconfigurationbystore;

import com.robinfood.storeor.dtos.response.TreasureDepartmentCategoryProductDTO;
import com.robinfood.storeor.dtos.response.TreasureDepartmentPaymentDTO;
import com.robinfood.storeor.dtos.response.TreasureDepartmentTaxesDTO;
import com.robinfood.storeor.dtos.response.TreasureDepartmentsDTO;
import com.robinfood.storeor.dtos.response.TreasureEntitiesDTO;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.TreasureDepartmentCategoryProductEntity;
import com.robinfood.storeor.entities.TreasureDepartmentPaymentEntity;
import com.robinfood.storeor.entities.TreasureDepartmentTaxesEntity;
import com.robinfood.storeor.entities.TreasureDepartmentsEntity;
import com.robinfood.storeor.entities.TreasureEntitiesEntity;
import com.robinfood.storeor.entities.TreasuryDepartmentEntity;
import com.robinfood.storeor.mappers.ITreasuryDepartmentMapper;
import com.robinfood.storeor.mocks.dto.TreasureDepartmentCategoryProductDTOMock;
import com.robinfood.storeor.mocks.dto.TreasureDepartmentPaymentDTOMock;
import com.robinfood.storeor.mocks.dto.TreasureDepartmentTaxesDTOMock;
import com.robinfood.storeor.mocks.dto.TreasureEntitiesDTOMock;
import com.robinfood.storeor.mocks.entity.TreasureDepartmentCategoryProductEntityMock;
import com.robinfood.storeor.mocks.entity.TreasureDepartmentPaymentEntityMock;
import com.robinfood.storeor.mocks.entity.TreasureDepartmentTaxesEntityMock;
import com.robinfood.storeor.mocks.entity.TreasureEntitiesEntityMock;
import com.robinfood.storeor.repositories.billingrepository.IBillingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetBillingConfigurationByStoreUseCaseTest {

    @Mock
    private IBillingRepository mockBillingRepository;

    @Mock
    private ITreasuryDepartmentMapper mockTreasuryDepartmentMapper;

    @InjectMocks
    private GetBillingConfigurationByStoreUseCase mockGetBillConfigurationByStoreUseCase;

    private final TreasureEntitiesEntityMock entitiesMock = new TreasureEntitiesEntityMock();

    private final List<TreasureEntitiesEntity> entities = entitiesMock.getDataDefaultList();

    private final TreasureDepartmentCategoryProductEntityMock productsMock =
            new TreasureDepartmentCategoryProductEntityMock();

    private final List<TreasureDepartmentCategoryProductEntity> products = productsMock.getDataDefaultList();

    private final TreasureDepartmentTaxesEntityMock taxesMock = new TreasureDepartmentTaxesEntityMock();

    private final List<TreasureDepartmentTaxesEntity> taxes = taxesMock.getDataDefaultList();

    private final TreasureDepartmentPaymentEntityMock paymentMock = new TreasureDepartmentPaymentEntityMock();

    private final List<TreasureDepartmentPaymentEntity> payments = paymentMock.getDataDefaultList();

    private final TreasuryDepartmentEntity treasuryDepartment = TreasuryDepartmentEntity.builder()
            .name("SEFAZ")
            .entities(entities)
            .payments(payments)
            .products(products)
            .taxes(taxes)
            .build();

    private final TreasureDepartmentsEntity treasureDepartmentsEntity = TreasureDepartmentsEntity.builder()
            .treasuryDepartment(treasuryDepartment)
            .build();

    private final APIResponseEntity<TreasureDepartmentsEntity> apiResponseEntity = new APIResponseEntity<>(
            200,
            treasureDepartmentsEntity,
            "locale",
            "success",
            "OK"
    );

    private final TreasureEntitiesDTOMock entitiesMockDTO = new TreasureEntitiesDTOMock();

    private final List<TreasureEntitiesDTO> entitiesDTO = entitiesMockDTO.getDataDefaultList();

    private final TreasureDepartmentCategoryProductDTOMock productsMockDTO = new TreasureDepartmentCategoryProductDTOMock();

    private final List<TreasureDepartmentCategoryProductDTO> productsDTO = productsMockDTO.getDataDefaultList();

    private final TreasureDepartmentTaxesDTOMock taxesMockDTO = new TreasureDepartmentTaxesDTOMock();

    private final List<TreasureDepartmentTaxesDTO> taxesDTO = taxesMockDTO.getDataDefaultList();

    private final TreasureDepartmentPaymentDTOMock paymentMockDTO = new TreasureDepartmentPaymentDTOMock();

    private final List<TreasureDepartmentPaymentDTO> paymentDTO = paymentMockDTO.getDataDefaultList();

    private final TreasureDepartmentsDTO treasureDepartmentsDTO = TreasureDepartmentsDTO.builder()
            .name("SEFAZ")
            .entities(entitiesDTO)
            .payments(paymentDTO)
            .taxes(taxesDTO)
            .build();

    @Test
    void test_When_GetBillingConfiguration_Is_OK() {

        when(mockBillingRepository.getBillingConfiguration(anyLong(), anyString())).thenReturn(apiResponseEntity);

        when(mockTreasuryDepartmentMapper.treasureDepartmentsEntityToTreasureDepartmentsDto(
                any(TreasuryDepartmentEntity.class)))
                .thenReturn(treasureDepartmentsDTO);

        TreasureDepartmentsDTO treasureDepartmentsDTOResponse = mockGetBillConfigurationByStoreUseCase.invoke(1L,
                "token");

        assertEquals(treasureDepartmentsDTOResponse, treasureDepartmentsDTO);

    }
}
