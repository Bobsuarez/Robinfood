package com.robinfood.localserver.commons.dtos.sathub;

import com.robinfood.localserver.commons.dtos.electronicbill.DetDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@AllArgsConstructor
@Data
public class DetSumDTO {

    private List<DetDTO> det;
}
