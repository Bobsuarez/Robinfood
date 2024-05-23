package com.robinfood.localserver.commons.entities.electronicbill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class InfCfeEntity {

    private Map<String,String> ide;

    private Map<String,String> emit;

    private List<DetEntity> det;

    private TotalEntity total;

    private PgtoEntity pgto;

    private InfAdicEntity infAdic;

}
