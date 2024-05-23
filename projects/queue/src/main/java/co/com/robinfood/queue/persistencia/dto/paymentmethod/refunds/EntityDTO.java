package co.com.robinfood.queue.persistencia.dto.paymentmethod.refunds;

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
public class EntityDTO implements Serializable {

    private static final long serialVersionUID = 469078198195282753L;

    private Long id;

    private String reference;

    private Long sourceId;
}
