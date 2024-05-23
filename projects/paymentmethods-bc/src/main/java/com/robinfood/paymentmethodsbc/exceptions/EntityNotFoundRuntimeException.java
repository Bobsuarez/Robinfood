package com.robinfood.paymentmethodsbc.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.robinfood.paymentmethodsbc.constants.MessageConstants.ENTITY_NOT_FOUND_MESSAGE_FORMAT;

@Data
@EqualsAndHashCode(callSuper = false)
public class EntityNotFoundRuntimeException extends RuntimeException {
    private final String id;
    private final String entity;

    public EntityNotFoundRuntimeException(String entity, String id) {
        super(String.format(ENTITY_NOT_FOUND_MESSAGE_FORMAT, entity, id));
        this.id = id;
        this.entity = entity;
    }

    public EntityNotFoundRuntimeException(String entity, Integer id) {
        super(String.format(ENTITY_NOT_FOUND_MESSAGE_FORMAT, entity, id));
        this.id = String.valueOf(id);
        this.entity = entity;
    }

    public EntityNotFoundRuntimeException(String entity, Long id) {
        super(String.format(ENTITY_NOT_FOUND_MESSAGE_FORMAT, entity, id));
        this.id = String.valueOf(id);
        this.entity = entity;
    }
}
