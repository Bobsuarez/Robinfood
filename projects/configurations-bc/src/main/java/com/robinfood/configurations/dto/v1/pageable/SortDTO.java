package com.robinfood.configurations.dto.v1.pageable;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SortDTO implements Serializable {

    private static final long serialVersionUID = 2873973003918506271L;

    @JsonProperty
    private boolean sorted;

    @JsonProperty
    private boolean unsorted;

    @JsonProperty
    private boolean empty;

}
