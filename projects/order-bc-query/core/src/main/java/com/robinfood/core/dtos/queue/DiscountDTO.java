package com.robinfood.core.dtos.queue;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class DiscountDTO  implements Serializable {

    private static final long serialVersionUID = 6585147313450766859L;

    private Long id;

    private String extra;

    private BigDecimal value;

    private Long typeId;

    private String reference;

}
