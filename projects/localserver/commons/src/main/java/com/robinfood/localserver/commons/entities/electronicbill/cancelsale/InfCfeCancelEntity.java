package com.robinfood.localserver.commons.entities.electronicbill.cancelsale;

import com.robinfood.localserver.commons.entities.electronicbill.DetEntity;
import com.robinfood.localserver.commons.entities.electronicbill.PgtoEntity;
import com.robinfood.localserver.commons.entities.electronicbill.TotalEntity;
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
public class InfCfeCancelEntity {

    private Map<String,String> ide;

    private Map<String,String> emit;

    private List<DetEntity> det;

    private TotalEntity total;

    private PgtoEntity pgto;

    private String chCanc;

}
