package com.robinfood.core.dtos.transactionrequestdto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmarineDTO implements Serializable {

    private static final long serialVersionUID = 2110348902074793361L;

    @NotNull
    private Boolean isActive;

}
