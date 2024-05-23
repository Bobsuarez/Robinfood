package com.robinfood.core.dtos.response.deductions;

import com.robinfood.core.dtos.deductions.TypeDeductionsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseTypeDeductionsDTO {

    List<TypeDeductionsDTO> listTypeDeductions;

}
