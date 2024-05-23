package com.robinfood.core.dtos.transactionrequestdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelDTO implements Serializable {

    private static final long serialVersionUID = -7472024941954344740L;

    @NotNull
    @Positive
    private Long id;

    @NotBlank
    private String name;

}
