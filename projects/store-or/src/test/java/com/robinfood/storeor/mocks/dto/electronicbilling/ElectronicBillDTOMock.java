package com.robinfood.storeor.mocks.dto.electronicbilling;

import com.robinfood.storeor.dtos.electronicbilling.ElectronicBillDTO;
import java.util.List;

public class ElectronicBillDTOMock {

    public static ElectronicBillDTO getDefaultData(){
        return new ElectronicBillDTO(
                "2022-03-08T21:58:30.115Z",
                "1",
                "test",
                "test",
                "200",
                "27",
                "muy pruebas"
        );
    }

    public static List<ElectronicBillDTO> getDefaultListData(){

        return List.of(new ElectronicBillDTO(
                "2022-03-08T21:58:30.115Z",
                "1",
                "test",
                "test",
                "200",
                "27",
                "muy pruebas"
        ),new ElectronicBillDTO(
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
