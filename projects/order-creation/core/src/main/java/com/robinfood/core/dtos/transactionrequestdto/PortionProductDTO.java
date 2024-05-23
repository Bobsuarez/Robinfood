package com.robinfood.core.dtos.transactionrequestdto;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortionProductDTO implements Serializable {

    private static final long serialVersionUID = -4019808470258773761L;

    @NotNull
    @Positive
    private Long id;

    @NotBlank
    private String name;
}
