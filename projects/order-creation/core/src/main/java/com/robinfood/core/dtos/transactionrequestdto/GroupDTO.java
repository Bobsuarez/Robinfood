package com.robinfood.core.dtos.transactionrequestdto;

import java.io.Serializable;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO implements Serializable {

    private static final long serialVersionUID = -8682484337954085549L;

    @NotNull
    @Positive
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Size
    @Valid
    private List<PortionDTO> portions;

    private String sku;
}
