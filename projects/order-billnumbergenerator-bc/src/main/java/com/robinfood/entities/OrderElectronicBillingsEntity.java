package com.robinfood.entities;

import com.robinfood.util.ObjectMapperSingleton;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public class OrderElectronicBillingsEntity implements Serializable {

    private LocalDateTime created_at;

    private int id;

    private Long order_id;

    private Object request_payload;

    private Object response_payload;

    private int status_code;

    private Long store_id;

    private String store_name;

    private LocalDateTime updated_at;

    public Object getRequest_payload() {
        return ObjectMapperSingleton.objectToJson(request_payload);
    }
}
