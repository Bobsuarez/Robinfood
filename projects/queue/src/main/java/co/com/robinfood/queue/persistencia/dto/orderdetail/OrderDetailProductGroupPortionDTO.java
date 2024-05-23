package co.com.robinfood.queue.persistencia.dto.orderdetail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class OrderDetailProductGroupPortionDTO {

    private  Boolean addition;
    private  BigDecimal discount;
    private  Double price;
    private  Integer quantity;
    @JsonProperty("free")
    private  Integer quantityFree;
    private  Long units;
    private  Double weight;
    private OrderDetailChangedPortionDTO changedPortion;
    private Long id;
    private String name;
    private Long parentId;
    private Long productId;
    private String sku;

    public boolean hasReplacement() {
        return changedPortion != null;
    }
}
