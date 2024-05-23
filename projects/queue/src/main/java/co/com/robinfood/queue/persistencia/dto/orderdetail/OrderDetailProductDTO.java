package co.com.robinfood.queue.persistencia.dto.orderdetail;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailProductDTO {

    private  Double basePrice;
    private  String brandName;
    private  Long categoryId;
    private  String categoryName;
    private  BigDecimal co2Total;
    private  BigDecimal deduction;
    private  BigDecimal discount;
    private  List<OrderDetailProductDiscountDTO> discounts;
    private  Long ProductId;
    private  List<OrderDetailProductGroupDTO> groups;
    private  Long id;
    private  String name;
    private  Integer quantity;
    private  Long sizeId;
    private  String sizeName;
    private  List<OrderDetailProductTaxDTO> taxes;
    private  BigDecimal unitPrice;
    private  Double total;
    private Long articleId;
    private String articleName;
    private Long articleTypeId;
    private Long brandId;
    private String image;
    private Long brandMenuId;
    private Long menuHallProductId;
    private String sku;

    public boolean hasReplacement() {
        return groups.stream().anyMatch(OrderDetailProductGroupDTO::hasReplacement);
    }
}
