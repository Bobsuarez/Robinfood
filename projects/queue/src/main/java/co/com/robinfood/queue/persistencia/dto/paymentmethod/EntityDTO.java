package co.com.robinfood.queue.persistencia.dto.paymentmethod;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class EntityDTO implements Serializable {

    private static final long serialVersionUID = 469078198195282753L;

    private Long id;

    @JsonProperty("source_id")
    private Long sourceId;

    private String reference;
}
