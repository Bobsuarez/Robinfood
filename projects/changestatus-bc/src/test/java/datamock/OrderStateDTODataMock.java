package datamock;

import com.robinfood.changestatusbc.dtos.OrderStateDTO;

public class OrderStateDTODataMock {

    public OrderStateDTO getDefaultData(){

        return OrderStateDTO.builder()
                .orderId(1L)
                .build();
    }

    public OrderStateDTO getDefaultDataPOS(){

        return OrderStateDTO.builder()
                .orderId(1L)
                .originId(4L)
                .isPaid(true)
                .build();
    }

    public OrderStateDTO getDefaultDataPOSNotPaid(){

        return OrderStateDTO.builder()
                .orderId(1L)
                .originId(4L)
                .isPaid(false)
                .build();
    }
}
