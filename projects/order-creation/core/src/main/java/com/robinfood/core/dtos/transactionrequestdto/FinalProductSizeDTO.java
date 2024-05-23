package com.robinfood.core.dtos.transactionrequestdto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinalProductSizeDTO implements Serializable {

    private static final long serialVersionUID = -6673838158464692855L;

    @NotNull
    @Positive
    private Long id;

    @NotNull
    private String name;
}
