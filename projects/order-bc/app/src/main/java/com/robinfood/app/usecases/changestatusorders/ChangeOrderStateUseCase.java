package com.robinfood.app.usecases.changestatusorders;

import com.robinfood.app.facades.IGetOrderStateFacade;
import com.robinfood.app.usecases.changeorderfinalproductportions.IChangeOrderFinalProductPortionsUseCase;
import com.robinfood.app.usecases.changestatewithsubstate.IChangeStateWithSubStateUseCase;
import com.robinfood.app.usecases.getallstate.IGetAllStateUseCase;
import com.robinfood.app.usecases.getstate.IGetStateOrderWithCodeUseCase;
import com.robinfood.app.usecases.statemachine.IStateMachineUseCase;
import com.robinfood.app.usecases.writechangestatusqueue.IWriteChangeStatusQueueUseCase;
import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.core.dtos.OrderStateDTO;
import com.robinfood.core.dtos.queue.ChangeOrderStatusDTO;
import com.robinfood.core.dtos.queue.WriteChangeStatusDTO;
import com.robinfood.core.entities.StatusEntity;
import com.robinfood.core.utilities.ObjectMapperSingleton;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

import static com.robinfood.core.enums.AppOrderBcTraceEnum.GET_STATES_ALL;
import static com.robinfood.core.enums.AppOrderBcTraceEnum.IS_MACHINE_STATES;
import static com.robinfood.core.enums.AppOrderBcTraceEnum.IS_STATUS_PAID_AND_ORIGIN_ID;
import static com.robinfood.core.enums.AppOrderBcTraceEnum.ORDER_PAID_SELF_MANAGEMENT;
import static com.robinfood.core.enums.AppOrderBcTraceEnum.ORDER_STATUS_CHANGE_RESPONSE;
import static com.robinfood.core.utilities.ObjectMapperSingleton.objectToJson;

@Component
@Slf4j
public class ChangeOrderStateUseCase implements IChangeOrderStateUseCase {

    private final IChangeStateWithSubStateUseCase changeStateWithSubStateUseCase;
    private final IChangeOrderFinalProductPortionsUseCase changeOrderFinalProductPortionsUseCase;
    private final IGetStateOrderWithCodeUseCase getStateUseCase;
    private final IGetOrderStateFacade getOrderStateFacade;
    private final IGetAllStateUseCase getAllStateUseCase;
    private final IStateMachineUseCase stateMachineUseCase;
    private final IWriteChangeStatusQueueUseCase writeChangeStatusQueueUseCase;

    public ChangeOrderStateUseCase(
            IChangeStateWithSubStateUseCase changeStateWithSubStateUseCase,
            IChangeOrderFinalProductPortionsUseCase changeOrderFinalProductPortionsUseCase,
            IGetStateOrderWithCodeUseCase getStateUseCase,
            IGetOrderStateFacade getOrderStateFacade,
            IGetAllStateUseCase getAllStateUseCase,
            IStateMachineUseCase stateMachineUseCase,
            IWriteChangeStatusQueueUseCase writeChangeStatusQueueUseCase
    ) {

        this.changeStateWithSubStateUseCase = changeStateWithSubStateUseCase;
        this.changeOrderFinalProductPortionsUseCase = changeOrderFinalProductPortionsUseCase;
        this.getStateUseCase = getStateUseCase;
        this.getOrderStateFacade = getOrderStateFacade;
        this.getAllStateUseCase = getAllStateUseCase;
        this.stateMachineUseCase = stateMachineUseCase;
        this.writeChangeStatusQueueUseCase = writeChangeStatusQueueUseCase;
    }

    @Override
    public WriteChangeStatusDTO invoke(ChangeOrderStatusDTO changeOrderStatusDTO) {

        OrderStateDTO actualState = getOrderStateFacade.invoke(changeOrderStatusDTO);
        OrderStateDTO nextState = getStateUseCase.invoke(changeOrderStatusDTO.getStatusCode());
        List<StatusEntity> stateSystem = getAllStateUseCase.invoke();

        log.info(GET_STATES_ALL.getMessageWithCode(), ObjectMapperSingleton.objectToJson(stateSystem));

        changeOrderStatusDTO.setOrderId(actualState.getOrderId());
        changeOrderStatusDTO.setTransactionId(actualState.getTransactionId());
        changeOrderStatusDTO.setChannelId(actualState.getOriginId());

        final Boolean isSendFromSelfManagement = this.sendOrderStatusChangedWhenOriginIsSelfManagementAndOrderIsPaid(
                changeOrderStatusDTO,
                actualState
        );

        log.info(ORDER_PAID_SELF_MANAGEMENT.getMessageWithCode(), isSendFromSelfManagement);

        boolean isStateMachineUseCase = stateMachineUseCase.invoke(
                actualState, nextState, stateSystem, changeOrderStatusDTO.getOrderId()
        );

        log.info(IS_MACHINE_STATES.getMessageWithCode(), isStateMachineUseCase);

        if (Boolean.FALSE.equals(isStateMachineUseCase)) {

            String error = String.format(
                    "Order status not change due to invalid state response: [%s] to order id: [%s] from order-bc",
                    changeOrderStatusDTO, changeOrderStatusDTO.getOrderId()
            );

            log.error(error);

            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, error);
        }

        nextState.setNotes(changeOrderStatusDTO.getNotes());
        nextState.setIdUser(changeOrderStatusDTO.getUserId());

        boolean resultChangeStatus = changeStateWithSubStateUseCase.invoke(
                changeOrderStatusDTO.getOrderId(), nextState
        );

        log.info(
                ORDER_STATUS_CHANGE_RESPONSE.getMessageWithCode(),
                objectToJson(changeOrderStatusDTO), changeOrderStatusDTO.getOrderId()
        );

        if (Boolean.FALSE.equals(resultChangeStatus)) {

            log.error(GlobalConstants.ERROR_STATE);

            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, GlobalConstants.ERROR_STATE);
        }

        changeOrderStatusDTO.setOrderUuid(actualState.getOrderUuid());
        changeOrderStatusDTO.setTransactionUuid(actualState.getTransactionUuid());

        final WriteChangeStatusDTO messageSend = writeChangeStatusQueueUseCase.invoke(changeOrderStatusDTO);

        changeOrderFinalProductPortionsUseCase.invoke(
                changeOrderStatusDTO.getOrderId(),
                changeOrderStatusDTO.getStatusCode()
        );

        return messageSend;
    }

    private Boolean sendOrderStatusChangedWhenOriginIsSelfManagementAndOrderIsPaid(
            ChangeOrderStatusDTO changeOrderStatusDTO,
            OrderStateDTO orderStateDTO
    ) {

        log.info(
                IS_STATUS_PAID_AND_ORIGIN_ID.getMessageWithCode(),
                orderStateDTO.getOriginId(),
                orderStateDTO.getIsPaid()
        );

        if (
                Objects.isNull(orderStateDTO.getOriginId()) || Objects.isNull(orderStateDTO.getIsPaid())
        ) {
            return Boolean.FALSE;
        }

        final Long ORIGIN_ID_OF_SELF_MANAGEMENT = 4L;

        if (
                orderStateDTO.getOriginId().equals(ORIGIN_ID_OF_SELF_MANAGEMENT)
                        && Boolean.TRUE.equals(orderStateDTO.getIsPaid())
        ) {
            writeChangeStatusQueueUseCase.invoke(changeOrderStatusDTO);
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
