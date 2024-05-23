package com.robinfood.localserver.commons.dtos.electronicbill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class DetDTO {

    private Map<String,String> prod;

    private Map <String,Object> imposto;

    @JsonProperty("nItem")
    private String numItem;

}
