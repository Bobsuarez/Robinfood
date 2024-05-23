package com.robinfood.configurations.dto.v1;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MappedValidateDTO implements Serializable {

    private static final long serialVersionUID = 5968683224894161369L;

    private Long id;

    private String sku;

    private String errorMessage;

}
