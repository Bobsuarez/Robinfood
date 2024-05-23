package co.com.robinfood.queue.persistencia.dto.queue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class DiscountDTO implements Serializable {

    private static final long serialVersionUID = 6585147313450766859L;

    private Long id;

    private String extra;

    private BigDecimal value;

    private Long typeId;

    private String reference;

}
