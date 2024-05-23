package com.robinfood.core.dtos.ordertocreatedto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO implements Serializable {

    private static final long serialVersionUID = -2184811136878707223L;

    protected String status;

    protected String message;

    @JsonProperty("description")
    protected String description;

    @JsonProperty("order_id")
    protected String orderId;

    @JsonProperty("store_id")
    protected String storeId;

    @JsonProperty("store_name")
    protected String storeName;

    @JsonProperty("order_total")
    protected BigDecimal orderTotal;

    @JsonProperty("reject_type")
    protected String rejectType;

    @JsonProperty("sku_rejected")
    protected List<String> skuRejected;

    @JsonProperty("created_at")
    private transient LocalDateTime createdAt;

    @JsonProperty("error_code")
    private Integer errorCode;

    @JsonProperty("error_message")
    private String errorMessage;

}
