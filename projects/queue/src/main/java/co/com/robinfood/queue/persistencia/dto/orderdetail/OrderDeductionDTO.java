package co.com.robinfood.queue.persistencia.dto.orderdetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDeductionDTO {

    private BigDecimal value;
}
