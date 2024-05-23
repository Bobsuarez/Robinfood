package co.com.robinfood.queue.persistencia.dto;
//com.robinfood.core.dtos.queue.ChangeOrderStatusDTO
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ChangeOrderStatusDTO {

    private Long channelId;

    private String brandId;

    private String deliveryIntegrationId;

    private String notes;

    private Long orderId;

    private String orderUid;

    private String orderUuid;

    private String origin;

    private String statusCode;

    private Long statusId;

    private Long transactionId;

    private Long userId;

    private String transactionUuid;

}