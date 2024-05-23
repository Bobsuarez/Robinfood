package com.robinfood.localserver.commons.dtos.electronicbill.cancelsale;

import com.robinfood.localserver.commons.dtos.electronicbill.DetDTO;
import com.robinfood.localserver.commons.dtos.electronicbill.InfAdicDTO;
import com.robinfood.localserver.commons.dtos.electronicbill.PgtoDTO;
import com.robinfood.localserver.commons.dtos.electronicbill.TotalDTO;
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
public class InfCfeCancelDTO {

    private Map<String, String> ide;

    private Map<String, String> emit;

    private List<DetDTO> det;

    private TotalDTO total;

    private PgtoDTO pgto;

    private String chCanc;

}
