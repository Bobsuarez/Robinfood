package com.robinfood.core.dtos.changestatusordersrequestdto;

import java.io.Serializable;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ChangeStatusOrdersRequestDTO implements Serializable {

    @NotNull
    @Size(min = 1)
    @Valid
    private List<OrderStatusRequestDTO> orders;
}
