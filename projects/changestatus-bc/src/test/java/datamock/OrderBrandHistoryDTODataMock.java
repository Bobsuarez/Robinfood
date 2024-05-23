package datamock;

import com.robinfood.changestatusbc.dtos.OrderBrandHistoryDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderBrandHistoryDTODataMock {
    public OrderBrandHistoryDTO getDefaultData(){
        return OrderBrandHistoryDTO.builder()
                .brandId(1L)
                .orderId(1L)
                .orderStatusId(1L)
                .userId(1L)
                .build();
    }

    public List<OrderBrandHistoryDTO> getDefaultDataList(){
        return new ArrayList<>(Arrays.asList(
                getDefaultData()
        ));
    }
}
