package com.robinfood.app.usecases.getallactivetypedeductions;

import com.robinfood.core.dtos.DeductionDTO;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import com.robinfood.repository.deductions.IDeductionsRemoteDataSource;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetTotalDeductionsUseCase implements IGetTotalDeductionsUseCase {

    private IDeductionsRemoteDataSource deductionsRemoteDataSource;

    public GetTotalDeductionsUseCase(IDeductionsRemoteDataSource deductionsRemoteDataSource) {
        this.deductionsRemoteDataSource = deductionsRemoteDataSource;
    }

    @Override
    public BigDecimal invoke(TransactionRequestDTO transactionRequestDTO, String token) {

        Map<Long, String> result = deductionsRemoteDataSource.getAllActiveTypeDeductions(token);

        transactionRequestDTO.setDeductions(transactionRequestDTO.getDeductions().stream()
                .filter(deductionDTO -> Objects.nonNull(result.get(deductionDTO.getId())))
                .collect(Collectors.toList()));
        BigDecimal total = transactionRequestDTO.getDeductions().stream().map(DeductionDTO::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (total.compareTo(transactionRequestDTO.getTotal()) > 0) {
            return transactionRequestDTO.getTotal();
        }

        return total;
    }
}
