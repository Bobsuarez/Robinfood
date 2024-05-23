package co.com.robinfood.queue.persistencia.dto.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OrderToCreateDTO {
    @JsonProperty("uuid")
    public String uuid;
    @JsonProperty("status")
    public String status;
    @JsonProperty("message")
    public Object message;
    @JsonProperty("description")
    public Object description;
    @JsonProperty("order_id")
    public String orderId;
    @JsonProperty("store_id")
    public String storeId;
    @JsonProperty("store_name")
    public Object storeName;
    @JsonProperty("order_total")
    public Integer orderTotal;
    @JsonProperty("reject_type")
    public Object rejectType;
    @JsonProperty("sku_rejected")
    public Object skuRejected;
    @JsonProperty("created_at")
    public Object createdAt;
    @JsonProperty("error_code")
    public Object errorCode;
    @JsonProperty("error_message")
    public Object errorMessage;
    @JsonProperty("buyer")
    public Object buyer;
    @JsonProperty("company")
    public Company company;
    @JsonProperty("device")
    public Device device;
    @JsonProperty("flowId")
    public Integer flowId;
    @JsonProperty("id")
    public Object id;
    @JsonProperty("delivery")
    public Delivery delivery;
    @JsonProperty("orders")
    public List<OrdersItem> orders;
    @JsonProperty("origin")
    public Origin origin;
    @JsonProperty("paid")
    public Boolean paid;
    @JsonProperty("paymentMethods")
    public List<PaymentMethodsItem> paymentMethods;
    @JsonProperty("pickupTimeConsumption")
    public Boolean pickupTimeConsumption;
    @JsonProperty("total")
    public Integer total;
    @JsonProperty("totalPaidPayments")
    public Integer totalPaidPayments;
    @JsonProperty("user")
    public User user;
}