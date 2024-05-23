package co.com.robinfood.queue.persistencia.dto.orderdetail;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailProductGroupDTO {

    private  Long id;

    private  String name;

    private  List<OrderDetailProductGroupPortionDTO> portions;

    private  List<OrderDetailRemovedPortionDTO> removedPortions;

    private String sku;

    public boolean hasReplacement() {
        return portions.stream().anyMatch(OrderDetailProductGroupPortionDTO::hasReplacement);
    }
}
