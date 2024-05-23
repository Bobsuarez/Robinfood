package co.com.robinfood.queue.persistencia.dto.orderdetail;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ServiceDTO {

    private Long id;

    private String name;

    private Long quantity;

    private Double unitPrice;
}
