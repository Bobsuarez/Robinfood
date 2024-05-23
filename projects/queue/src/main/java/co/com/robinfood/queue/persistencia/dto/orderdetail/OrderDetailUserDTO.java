package co.com.robinfood.queue.persistencia.dto.orderdetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailUserDTO {

    private  String email;

    private  String firstName;

    private  String fullName;

    private  Long id;

    private  String lastName;
    private  String mobile;
    private  Long orderId;
    private Long loyaltyStatus = 0L;
}
