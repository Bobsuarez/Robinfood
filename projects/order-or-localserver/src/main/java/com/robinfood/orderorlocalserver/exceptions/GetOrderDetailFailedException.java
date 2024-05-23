package com.robinfood.orderorlocalserver.exceptions;

public class GetOrderDetailFailedException extends Exception {

    public GetOrderDetailFailedException(String errorMessage) {
        super(errorMessage);
    }

}