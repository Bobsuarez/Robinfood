package co.com.robinfood.queue.persistencia.dto.queue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowInfoDTO implements Serializable {

    private static final long serialVersionUID = -8133003200203333168L;

    private String address;

    private String comment;

    private String pickUpTime;
}
