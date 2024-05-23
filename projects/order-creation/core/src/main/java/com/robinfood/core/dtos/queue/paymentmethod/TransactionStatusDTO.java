package com.robinfood.core.dtos.queue.paymentmethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class TransactionStatusDTO implements Serializable {

    private static final long serialVersionUID = 2724081987447452929L;

    private Long id;

    private String date;

    private String name;
}
