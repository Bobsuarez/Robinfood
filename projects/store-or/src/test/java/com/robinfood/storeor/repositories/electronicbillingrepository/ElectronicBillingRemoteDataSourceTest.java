package com.robinfood.storeor.repositories.electronicbillingrepository;

import com.robinfood.storeor.configs.apis.BillingBcApi;
import com.robinfood.storeor.entities.APIResponseEntity;
import com.robinfood.storeor.entities.electronicbilling.ElectronicBillingEntity;
import com.robinfood.storeor.entities.electronicbilling.ElectronicBillingRequestEntity;
import com.robinfood.storeor.entities.electronicbilling.ElectronicBillingResponseEntity;
import com.robinfood.storeor.mocks.entity.electronicbilling.ElectronicBillingEntityMock;
import com.robinfood.storeor.mocks.entity.electronicbilling.ElectronicBillingRequestEntityMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ElectronicBillingRemoteDataSourceTest {

    final String token = "token";

    @InjectMocks
    ElectronicBillingRemoteDataSource electronicBillingRemoteDataSource;

    @Mock
    private BillingBcApi billingBcApi;

    final ElectronicBillingRequestEntity electronicBillingRequestEntity =
            new ElectronicBillingRequestEntityMock().electronicBillingRequestEntity;

    final ElectronicBillingResponseEntity electronicBillingResponseEntity = new
            ElectronicBillingResponseEntity("2022-04-06T03:18:52.678",
            "3d4fd4a0-dc7d-47c6-91c7-a5b6dc385370",
            "Register created electronic billing"
    );

    final APIResponseEntity<ElectronicBillingResponseEntity> apiResponseEntity = new APIResponseEntity<>(
            200,
            electronicBillingResponseEntity,
            "2022-04-06T03:18:53.817292Z",
            "Save electronic billing successfully",
            "OK"
    );

    @Test
    void test_When_Create_Register_Electronic_Billing_Order(){

        when(electronicBillingRemoteDataSource
                .save(token, electronicBillingRequestEntity)
        ).thenReturn(apiResponseEntity);

        when(billingBcApi
                .createElectronicBilling(token, electronicBillingRequestEntity)
        ).thenReturn(apiResponseEntity);

        APIResponseEntity<ElectronicBillingResponseEntity> apiResponseEntityTest =
                electronicBillingRemoteDataSource.save(token, electronicBillingRequestEntity);

        assertEquals(apiResponseEntityTest, apiResponseEntity);
    }

    @Test
    void test_When_Find_Electronic_Bill(){

        List<Long> ordersId=new ArrayList<>();
        ordersId.add(1l);
        ordersId.add(2l);

        ElectronicBillingEntityMock electronicBillingEntityMock= new  ElectronicBillingEntityMock();

        when(billingBcApi
                .getElectronicBillByOrdersId(token, ordersId)
        ).thenReturn(new APIResponseEntity<>(
                200,
                electronicBillingEntityMock.getDataListDefault(),
                "2022-04-06T03:18:53.817292Z",
                "successful search process",
                "OK"
        ));

        APIResponseEntity<List<ElectronicBillingEntity>> response =
                electronicBillingRemoteDataSource.findAllByOrderIdIn(token,ordersId);

        assertEquals(new APIResponseEntity<>(
                200,
                electronicBillingEntityMock.getDataListDefault(),
                "2022-04-06T03:18:53.817292Z",
                "successful search process",
                "OK"
        ),response);
    }
}
