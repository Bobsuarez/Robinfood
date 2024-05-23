package com.robinfood.core.dtos.transactionrequestdto;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO implements Serializable {

    private static final long serialVersionUID = 8925238570926632453L;

    @NotBlank
    private String currency;

    @NotNull
    private Long id;
}
