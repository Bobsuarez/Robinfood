package com.robinfood.taxes.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemListDTO implements Serializable {

    private static final long serialVersionUID = -7995388494735248000L;

    @NotNull
    @JsonProperty
    @Schema(example = "1")
    private Long orderId;

    @NotNull
    @JsonProperty
    private Map<String, Object> criteria;

    @Valid
    @NotNull
    @JsonProperty
    private List<ItemDTO> items;
}
