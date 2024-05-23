package co.com.robinfood.queue.persistencia.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderBillingJsonResponseDTO {

    private Short retry;
    private Long orderId;
    private String statusCode;
    private JsonNode requestPayload;
    private JsonNode responsePayload;

}
