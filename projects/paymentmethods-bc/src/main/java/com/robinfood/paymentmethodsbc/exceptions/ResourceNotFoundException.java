package com.robinfood.paymentmethodsbc.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.robinfood.paymentmethodsbc.constants.MessageConstants.ENTITY_NOT_FOUND_MESSAGE_FORMAT;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceNotFoundException extends RuntimeException {
    private final String id;
    private final String resource;

    public ResourceNotFoundException(String resource, String id) {
        super(String.format(ENTITY_NOT_FOUND_MESSAGE_FORMAT, resource, id));
        this.id = id;
        this.resource = resource;
    }
}
