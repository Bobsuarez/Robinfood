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
public class AddressQueueDTO implements Serializable {

    private static final long serialVersionUID = 88551446972874377L;

    private String address;

    private String comment;

    private String latitude;

    private String longitude;

    private String pickupTime;

    private String zipCode;

}
