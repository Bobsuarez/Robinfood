package com.robinfood.ordereports_bc_muyapp.controller.users;

import com.robinfood.app.library.dto.Answer;
import com.robinfood.app.library.exception.ApplicationException;
import com.robinfood.app.library.restexceptionhandler.RestExceptionHandler;
import com.robinfood.ordereports_bc_muyapp.dto.orderdetail.ResponseOrderDetailDTO;
import com.robinfood.ordereports_bc_muyapp.usecases.getuserorderdetailbyuid.IGetUserOrderDetailByUIdUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.robinfood.ordereports_bc_muyapp.constants.APIConstants.USERS_ORDER_DETAIL;
import static com.robinfood.ordereports_bc_muyapp.constants.APIConstants.USERS_V1;
import static com.robinfood.ordereports_bc_muyapp.enums.OrderDetailLogEnum.RECEIVING_REQUEST;

@AllArgsConstructor
@RequestMapping(USERS_V1)
@RestController
@Slf4j
public class UsersController extends RestExceptionHandler implements IUsersController {

    private IGetUserOrderDetailByUIdUseCase getUserOrderDetailByUIdUseCase;

    @Override
    @GetMapping(USERS_ORDER_DETAIL)
    public Answer getOrderDetail(
            @PathVariable("transactionUuid") String transactionUuid
    ) throws ApplicationException {

        log.info(RECEIVING_REQUEST.getMessage(), transactionUuid);

        final ResponseOrderDetailDTO orderDetail = getUserOrderDetailByUIdUseCase.invoke(transactionUuid);

        return answer(orderDetail);
    }
}

