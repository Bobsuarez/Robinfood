package com.robinfood.core.dtos.pagination;

import com.robinfood.core.dtos.OrderStatusDTO;
import com.robinfood.core.dtos.PaymentMethodDTO;
import com.robinfood.core.dtos.request.order.BrandDTO;
import com.robinfood.core.dtos.request.order.FinalProductDTO;
import com.robinfood.core.dtos.request.order.StoreDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class OrdersHistoryPagination {

    private Long id;
    private String uid;
    private Boolean paid;
    private StoreDTO store;
    private OrderStatusDTO status;
    private PaymentMethodDTO payment;
    private LocalDate datetime;
    private String timezone;
    private List<BrandDTO> brand;
    private List<FinalProductDTO> productos;

    public OrdersHistoryPagination (Long id, String uid , Boolean paid, LocalDate local_date, LocalTime local_time) {
        this.id = id;
        this.uid = uid;
        this.paid = paid;
        this.datetime = local_date;
    }

}
