package co.com.robinfood.queue.persistencia.dto.ordertocreatedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ThirdParty implements Serializable {

    @Serial
    private static final long serialVersionUID = -8133003288203333188L;

    private String documentNumber;

    private Long documentType;

    private String email;

    private String fullName;

    private String phone;
}
