package co.com.robinfood.queue.persistencia.dto.queue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@ToString
public class ChangeOrderStatusDTO implements Serializable {

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
