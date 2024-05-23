package datamock;

import com.robinfood.changestatusbc.dtos.OrderHistoryDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderHistoryDTODataMock {

    public OrderHistoryDTO getDefaultData() {
        return OrderHistoryDTO.builder()
                .observation("Anything")
                .orderId(1L)
                .orderStatusId(1L)
                .userId(1L)
                .build();
    }

    public OrderHistoryDTO getDefaultDataThree() {
        return OrderHistoryDTO.builder()
                .observation("Anything")
                .orderId(1L)
                .orderStatusId(2L)
                .userId(1L)
                .build();
    }

    public OrderHistoryDTO getDefaultDataTwo() {
        return OrderHistoryDTO.builder()
                .observation("Anything")
                .orderId(1L)
                .orderStatusId(3L)
                .userId(1L)
                .build();
    }

    public List<OrderHistoryDTO> getDefaultDataList() {
        return new ArrayList<>(Arrays.asList(
                getDefaultData()
        ));
    }

    public List<OrderHistoryDTO> getDefaultDataListTwoValues() {
        return new ArrayList<>(Arrays.asList(
                getDefaultDataTwo(),
                getDefaultDataThree()
        ));
    }
}
