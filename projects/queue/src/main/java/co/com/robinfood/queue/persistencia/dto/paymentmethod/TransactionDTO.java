package co.com.robinfood.queue.persistencia.dto.paymentmethod;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Builder
public class TransactionDTO implements Serializable {

    private static final long serialVersionUID = 7645686037828574993L;

    private EntityDTO entity;

    private PaymentDTO payment;

    @JsonProperty("transaction_id")
    private Long transactionId;

    @JsonProperty("transaction_status")
    private TransactionStatusDTO transactionStatus;

    @JsonProperty("transaction_uuid")
    private String transactionUuid;
}
