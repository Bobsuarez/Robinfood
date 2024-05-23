package co.com.robinfood.queue.persistencia.dto.queue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 6438489172020268749L;

    private Long id;

    private Long productId;

    private String img;

    private String name;

    private SizeQueueDTO size;

    private BrandQueueDTO brand;

    private BigDecimal price;

    private BigDecimal discount;

    private Integer quantity;
}
