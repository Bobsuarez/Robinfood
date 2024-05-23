package com.robinfood.storeor.mocks.entity.electronicbilling;


import com.robinfood.storeor.entities.electronicbilling.ElectronicBillingEntity;
import java.util.List;

public class ElectronicBillingEntityMock {

    public ElectronicBillingEntity getDataInDefault() {
        return new ElectronicBillingEntity(
                null,
                "1",
                "test",
                "test",
                "200",
                "27",
                "muy pruebas"
        );
    }

    public ElectronicBillingEntity getDataOutDefault() {
        return new ElectronicBillingEntity(
                "2022-03-08T21:58:30.115Z",
                "1",
                "test",
                "test",
                "200",
                "27",
                "muy pruebas"
        );
    }

    public List<ElectronicBillingEntity> getDataListDefault(){

        return List.of(new ElectronicBillingEntity(
                "2022-03-08T21:58:30.115Z",
                "1",
                "test",
                "test",
                "200",
                "27",
                "muy pruebas"
        ),new ElectronicBillingEntity(
                "2022-03-08T21:58:30.115Z",
                "2",
                "test",
                "test",
                "200",
                "27",
                "muy pruebas"
        ));
    }
}
