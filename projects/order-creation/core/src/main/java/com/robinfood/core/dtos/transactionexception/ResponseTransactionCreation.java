package com.robinfood.core.dtos.transactionexception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTransactionCreation {

    private int code;

    private Object data;

    private List<String> error;

    private String message;

}
