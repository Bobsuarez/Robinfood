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
@IsAttributeFalse(field = "isActive", required = {"statusId"})
public class TogoDTO implements Serializable {

    private static final long serialVersionUID = -6546347069917019894L;

    @NotNull
    private Boolean isActive;

    private Long statusId;
}
