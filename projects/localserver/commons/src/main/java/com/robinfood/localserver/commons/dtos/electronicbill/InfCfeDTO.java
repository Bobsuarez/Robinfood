package com.robinfood.localserver.commons.dtos.electronicbill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class InfCfeDTO {

    private Map<String, String> ide;

    private Map<String, String> emit;

    private List<DetDTO> det;

    private TotalDTO total;

    private PgtoDTO pgto;

    private InfAdicDTO infAdic;

}
