package com.robinfood.app.datamocks.dto.input;

import com.robinfood.core.dtos.request.order.DeliveryDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeliveryDTODataMock {
    public DeliveryDTO getDataDefault(){
        return new DeliveryDTO(
                "address City",
                "addressDescription",
                LocalDate.of(2021, 01, 01),
                LocalTime.of(12, 20, 01),
                "code",
                "1",
                "Juan David",
                "notes",
                1,
                "Efectivo",
                10000.0,
                10000.0,
                10000.0,
                "MENU_ITEM_DISCOUNT"
        );
    }

    public List<DeliveryDTO> getDataDefaultList(){
        return new ArrayList<>(Arrays.asList(getDataDefault()));
    }
}
