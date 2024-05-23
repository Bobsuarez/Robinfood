package com.robinfood.app.usecases.updateordertemplate;

import com.robinfood.core.exceptions.ResourceNotFoundException;

public interface IUpdateOrderUuidTemplateUseCase {

    String invoke(String uuid) throws ResourceNotFoundException;
}
