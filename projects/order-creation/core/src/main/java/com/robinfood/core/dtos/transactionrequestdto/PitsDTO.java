package com.robinfood.core.dtos.transactionrequestdto;

import com.robinfood.core.validations.IsAttributeFalse;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@IsAttributeFalse(field = "isActive", required = {"carPlate"})
public class PitsDTO implements Serializable {

    private static final long serialVersionUID = -5464156556266490735L;

    private String carPlate;

    @NotNull
    private Boolean isActive;
}
