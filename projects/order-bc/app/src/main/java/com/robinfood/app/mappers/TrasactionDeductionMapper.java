package com.robinfood.app.mappers;

import com.robinfood.core.dtos.DeductionDTO;
import com.robinfood.core.entities.TrasanctionDeductionEntity;

import java.util.List;
import java.util.stream.Collectors;

public class TrasactionDeductionMapper {

    public static List<TrasanctionDeductionEntity> listDeductionsDTOToListTransactionsEntity(
            List<DeductionDTO> listDeductions,Long idTransactions) {
        return listDeductions.stream().map(deduction ->
                TrasactionDeductionMapper.deductionsDTOTransactionsEntity(deduction,idTransactions))
                .collect(Collectors.toList());
    }

    public static TrasanctionDeductionEntity  deductionsDTOTransactionsEntity (
            DeductionDTO deductionDTO, Long idTransactions) {
        return TrasanctionDeductionEntity
                .builder()
                .deductionId(deductionDTO.getId())
                .trasactionId(idTransactions)
                .value(deductionDTO.getValue())
                .build();
    }
}
