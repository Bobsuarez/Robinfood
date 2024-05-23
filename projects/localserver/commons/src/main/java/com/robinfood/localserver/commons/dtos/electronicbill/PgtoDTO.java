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
public class PgtoDTO {

    private List<Map<String,String>> MP;
}
