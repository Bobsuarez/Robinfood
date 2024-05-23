package com.robinfood.core.dtos.transactionrequestdto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CorporateDTO implements Serializable {

    private static final long serialVersionUID = -7819182697682343191L;

    @NotNull
    private Boolean isActive;
}
