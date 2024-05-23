package com.robinfood.core.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidateTaxRequestDTO implements Serializable {

    private  Map<String, Object> criteria;

    private  List<ValidateTaxItemRequestDTO> items;
}
