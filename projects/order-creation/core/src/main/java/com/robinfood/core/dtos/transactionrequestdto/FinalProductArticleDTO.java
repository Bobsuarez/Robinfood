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
public class FinalProductArticleDTO implements Serializable {

    private static final long serialVersionUID = 8385382553208032925L;

    @NotNull
    @Positive
    private Long id;

    @NotNull
    @Positive
    private Long menuHallProductId;

    @NotNull
    @Positive
    private Long typeId;

    @NotBlank
    private String typeName;
}
