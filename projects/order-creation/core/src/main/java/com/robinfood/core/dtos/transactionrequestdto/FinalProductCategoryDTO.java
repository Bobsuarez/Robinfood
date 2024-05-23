package com.robinfood.core.dtos.transactionrequestdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinalProductCategoryDTO implements Serializable {

    private static final long serialVersionUID = -5683365407204243939L;

    @NotNull
    @Positive
    private Long id;

    @NotBlank
    private String name;
}
