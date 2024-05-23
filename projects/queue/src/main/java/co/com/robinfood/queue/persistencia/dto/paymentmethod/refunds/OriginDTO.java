package co.com.robinfood.queue.persistencia.dto.paymentmethod.refunds;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Builder
public class OriginDTO implements Serializable {

    private static final long serialVersionUID = 7453985988457648294L;

    private Long channelId;

    private String ip;
}
