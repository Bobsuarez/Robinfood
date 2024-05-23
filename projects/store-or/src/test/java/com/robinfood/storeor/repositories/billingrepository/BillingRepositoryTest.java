package com.robinfood.storeor.repositories.billingrepository;

import com.robinfood.storeor.configs.apis.BillingBcApi;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.CommandConfiguration.CommandConfigurationEntity;
import com.robinfood.storeor.entities.TreasureDepartmentCategoryProductEntity;
import com.robinfood.storeor.entities.TreasureDepartmentPaymentEntity;
import com.robinfood.storeor.entities.TreasureDepartmentTaxesEntity;
import com.robinfood.storeor.entities.TreasureDepartmentsEntity;
import com.robinfood.storeor.entities.TreasureEntitiesEntity;
import com.robinfood.storeor.entities.TreasuryDepartmentEntity;
import com.robinfood.storeor.mocks.entity.CommandConfiguration.CommandConfigurationListEntityMock;
import com.robinfood.storeor.mocks.entity.TreasureDepartmentCategoryProductEntityMock;
import com.robinfood.storeor.mocks.entity.TreasureDepartmentPaymentEntityMock;
import com.robinfood.storeor.mocks.entity.TreasureDepartmentTaxesEntityMock;
import com.robinfood.storeor.mocks.entity.TreasureEntitiesEntityMock;
import feign.FeignException;
import feign.Request;
import feign.RequestTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BillingRepositoryTest {

    @Mock
    private BillingBcApi billingBcApi;

    @InjectMocks
    private BillingRepository billingRepository;

    private final String token = "token";

    private final Long storeId = 1L;

    private final TreasureEntitiesEntityMock entitiesMock = new TreasureEntitiesEntityMock();

    private final List<TreasureEntitiesEntity> entities = entitiesMock.getDataDefaultList();

    private final TreasureDepartmentCategoryProductEntityMock productsMock =
            new TreasureDepartmentCategoryProductEntityMock();

    private final List<TreasureDepartmentCategoryProductEntity> products = productsMock.getDataDefaultList();

    private final TreasureDepartmentTaxesEntityMock taxesMock = new TreasureDepartmentTaxesEntityMock();

    private CommandConfigurationListEntityMock commandConfigurationListEntity =
            new CommandConfigurationListEntityMock();

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

    final APIResponseEntity<TreasureDepartmentsEntity> ApiResponseEntity = new APIResponseEntity<>(
            200,
            treasureDepartmentsEntity,
            "locale",
            "success",
            "Ok"
    );

    @Test
    void test_When_Get_Billing_Configuration_Successfully() {

        when(billingBcApi.getBillingConfiguration(1L, token)).thenReturn(ApiResponseEntity);

        final APIResponseEntity<TreasureDepartmentsEntity> treasureDepartmentsEntityAPIResponseEntity =
                billingRepository.getBillingConfiguration(1L, token);

        assertEquals(treasureDepartmentsEntityAPIResponseEntity, ApiResponseEntity);
    }

    @Test
    void test_When_Get_Billing_Configuration_Failed_But_Exists_Electronic_Bill_Configuration() {

        Request request = Request.create(Request.HttpMethod.GET, "url",
                new HashMap<>(), null, new RequestTemplate());

        when(billingBcApi.getBillingConfiguration(anyLong(), anyString()))
                .thenThrow(new FeignException.InternalServerError("Not found Treasury Department Store",
                        request, null, null));

        final APIResponseEntity<TreasureDepartmentsEntity> treasureDepartmentsEntityAPIResponseEntity =
                billingRepository.getBillingConfiguration(anyLong(), anyString());

        assertEquals(200, treasureDepartmentsEntityAPIResponseEntity.getCode());
    }

    @Test
    void test_When_Get_Billing_Configuration_Failed_But_Doesnt_Exists_Electronic_Bill_Configuration() {

        Request request = Request.create(Request.HttpMethod.GET, "url",
                new HashMap<>(), null, new RequestTemplate());

        when(billingBcApi.getBillingConfiguration(anyLong(), anyString()))
                .thenThrow(new FeignException.InternalServerError("Any other message",
                        request, null, null));

        assertThrows(FeignException.InternalServerError.class, () -> billingRepository
                .getBillingConfiguration(anyLong(), anyString()));
    }

    @Test
    void test_getCommandConfiguration_Return_Succesfully() {
        final String token = "token";

        final APIResponseEntity<List<CommandConfigurationEntity>> apiResponse = new APIResponseEntity<>(
                200,
                commandConfigurationListEntity.defaultData(),
                "locale",
                "Store command Configuration obtained",
                "Success"
        );
        when(billingBcApi.getCommandConfigurationByStoreId(storeId, token)).thenReturn(apiResponse);
        APIResponseEntity<List<CommandConfigurationEntity>> response =
                billingRepository.getCommandConfiguration(storeId, token);

        assertEquals(apiResponse, response);
    }
}
