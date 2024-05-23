package co.com.robinfood.queue.persistencia.dto.queue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreatedQueueDTO implements Serializable {

    public static final long serialVersionUID = -8133003200203333168L;

    public Long id;

    public ChannelQueueDTO channel;

    public String orderDate;

    public Boolean paid;

    public Long transactionId;

    public String transactionUuid;

    public String orderUid;

    public String orderUuid;

    public String orderNumber;

    public Long flowId;

    public Long countryId;

    public Long companyId;

    public List<CouponDTO> coupons;

    public String timeZone;

    public Long invoice;

    public BigDecimal subtotal;

    public BigDecimal tax;

    public BigDecimal discount;

    public BigDecimal total;

    public UserDTO user;

    public AddressQueueDTO address;

    public List<PickupTimeQueueDTO> pickupTimes;

    public List<ProductDTO> products;

    public List<PaymentsDTO> payments;

    public List<DiscountDTO> discounts;

    public StoreDTO store;

}
