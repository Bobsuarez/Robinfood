package com.robinfood.core.dtos.transactionrequestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO implements Serializable {

    private static final long serialVersionUID = -5959974350725948433L;

    private String code;

    @NotNull
    @Positive
    private Long id;

    @NotBlank
    private String name;

    private Long posId;

    private String postalCode;
}
