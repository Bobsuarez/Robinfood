package co.com.robinfood.queue.persistencia.dto.queue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PickupTimeQueueDTO implements Serializable {

    private static final long serialVersionUID = -7376980265909798519L;

    private Long storeId;

    private Long pickupTime;

}
