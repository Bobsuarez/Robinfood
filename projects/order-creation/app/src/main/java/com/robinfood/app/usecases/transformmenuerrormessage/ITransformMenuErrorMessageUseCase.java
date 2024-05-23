package com.robinfood.app.usecases.transformmenuerrormessage;

import com.robinfood.core.dtos.ordertocreatedto.OrderToCreateDTO;
import com.robinfood.core.exceptions.TransactionCreationException;

public interface ITransformMenuErrorMessageUseCase {

    /**
     * Transform the exception  message into a java object
     *
     * @param order                        orders of the error
     * @param transactionCreationException exception of the order
     */
    void invoke(OrderToCreateDTO order, TransactionCreationException transactionCreationException);
}
