package com.robinfood.app.usecases.getorderdetailsplushistory;

import com.robinfood.app.usecases.getlistpaymentmethods.IGetListPaymentMethodsUseCase;
import com.robinfood.app.usecases.getorderdetailprint.IGetOrderDetailPrintUseCase;
import com.robinfood.app.usecases.getstatusorderhistory.IGetStatusOrderHistoryUseCase;
import com.robinfood.core.dtos.OrderDetailDTO;
import com.robinfood.core.dtos.PaymentMethodsFilterDTO;
import com.robinfood.core.dtos.orderdetailplushistory.OrderDetailPlusHistoryDTO;
import com.robinfood.core.dtos.statusorderhistory.StatusOrderHistoryDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.mappers.OrderDetailPlusHistoryMappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.robinfood.core.constants.GlobalConstants.DEFAULT_INTEGER_VALUE;

@Service
@Slf4j
public class GetConsolidatedOrderDetailUseCase implements IGetConsolidatedOrderDetailUseCase{

    private final IGetStatusOrderHistoryUseCase getStatusOrderHistoryUseCase;

    private final IGetListPaymentMethodsUseCase getListPaymentMethodsUseCase;

    private final IGetOrderDetailPrintUseCase getOrderDetailPrintUseCase;

    public GetConsolidatedOrderDetailUseCase (
           IGetStatusOrderHistoryUseCase getStatusOrderHistoryUseCase,
           IGetListPaymentMethodsUseCase getListPaymentMethodsUseCase,
           IGetOrderDetailPrintUseCase getOrderDetailPrintUseCase
    ){
        this.getStatusOrderHistoryUseCase = getStatusOrderHistoryUseCase;
        this.getListPaymentMethodsUseCase = getListPaymentMethodsUseCase;
        this.getOrderDetailPrintUseCase = getOrderDetailPrintUseCase;
   }

    @Override
    public Result<OrderDetailPlusHistoryDTO> invoke(String orderUuid) {

        List<OrderDetailDTO> orderDetailPrintList = (
                (Result.Success<List<OrderDetailDTO>>) getOrderDetailPrintUseCase.invoke(
                        List.of(),
                        List.of(),
                        List.of(orderUuid)
                )).getData();

        if(!orderDetailPrintList.isEmpty()){
            OrderDetailPlusHistoryDTO orderDetailPlusHistory = buildOrderDetailsPlusHistory(
                    orderDetailPrintList.get(DEFAULT_INTEGER_VALUE)
            );

            return new Result.Success<>(orderDetailPlusHistory);
        }

        return new Result.Success<>(OrderDetailPlusHistoryDTO.builder().build());
    }

    private OrderDetailPlusHistoryDTO buildOrderDetailsPlusHistory(OrderDetailDTO orderDetailDTO){

        List<PaymentMethodsFilterDTO> paymentMethodsFilterList = ((Result.Success<List<PaymentMethodsFilterDTO>>)
                getListPaymentMethodsUseCase.invoke()).getData();

        List<StatusOrderHistoryDTO> statusOrderHistoryList = getStatusOrderHistoryUseCase.invoke(
                orderDetailDTO.getOrderUuid()
        );

        return OrderDetailPlusHistoryMappers
                .orderDetailDTOToOrderDetailPlusHistoryDTO(
                        orderDetailDTO,
                        statusOrderHistoryList,
                        paymentMethodsFilterList
                );
    }
}
