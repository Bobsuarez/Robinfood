package com.robinfood.ordereports.controllers.orders;


import com.robinfood.app.library.dto.Answer;
import com.robinfood.app.library.restexceptionhandler.RestExceptionHandler;
import com.robinfood.ordereports.dtos.orders.OrderDetailDTO;
import com.robinfood.ordereports.usecases.getorderdetail.IGetOrderDetailUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static com.robinfood.ordereports.constants.APIConstants.TRANSACTION;
import static com.robinfood.ordereports.constants.APIConstants.TRANSACTION_UUID;
import static com.robinfood.ordereports.enums.OrderDetailLogEnum.RECEIVING_REQUEST;

@AllArgsConstructor
@RestController
@RequestMapping(TRANSACTION)
@Slf4j
public class OrderController  extends RestExceptionHandler implements IOrderController{

    private final IGetOrderDetailUseCase getOrderDetailUseCase;


    @Override
    @GetMapping(TRANSACTION_UUID)
    public Answer<OrderDetailDTO> getOrderDetails(
            @PathVariable(value = "transactionUuid") String transactionUuid
    ) {
        log.info(RECEIVING_REQUEST.getMessage(), transactionUuid);

        OrderDetailDTO orderDetailDTO = getOrderDetailUseCase.invoke(transactionUuid);

        return answer().setData(orderDetailDTO);
    }
}
