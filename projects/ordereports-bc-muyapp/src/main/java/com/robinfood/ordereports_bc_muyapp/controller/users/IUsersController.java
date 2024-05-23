package com.robinfood.ordereports_bc_muyapp.controller.users;

import com.robinfood.app.library.dto.Answer;
import com.robinfood.app.library.exception.ApplicationException;

public interface IUsersController {

    Answer getOrderDetail(String transactionUuid) throws ApplicationException;
}
