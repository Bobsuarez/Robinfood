package datamock;

import com.robinfood.changestatusbc.dtos.ChangeOrderStatusDTO;

public class ChangeOrderStatusDTODataMock {

    public ChangeOrderStatusDTO getDefaultData(){

        return ChangeOrderStatusDTO.builder()
                .notes("cambio de estado")
                .orderId(1L)
                .statusCode("CODE")
                .userId(1L)
                .build();
    }
}
