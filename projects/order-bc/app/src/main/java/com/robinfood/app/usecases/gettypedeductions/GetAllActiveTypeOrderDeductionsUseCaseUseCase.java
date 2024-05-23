package com.robinfood.app.usecases.gettypedeductions;

import com.robinfood.app.mappers.OrderTypeDeductionMapper;
import com.robinfood.core.constants.GlobalConstants;
import com.robinfood.repository.typededuction.IOrderTypeDeductionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class GetAllActiveTypeOrderDeductionsUseCaseUseCase implements IGetAllActiveTypeOrderDeductionsUseCase {

    private final IOrderTypeDeductionRepository orderTypeDeductionRepository;

    public GetAllActiveTypeOrderDeductionsUseCaseUseCase(IOrderTypeDeductionRepository orderTypeDeductionRepository) {
        this.orderTypeDeductionRepository = orderTypeDeductionRepository;
    }

    @Override
    public Map<Long, String> invoke() {

        log.info("Getting type deductions");

        Map<Long, String> result = OrderTypeDeductionMapper.listOrderTypeDeducctionsEntitiesToListTypeDeductionsDto(
                orderTypeDeductionRepository.findAllByStatus(GlobalConstants.ORDER_STATUS_CREATED)
        );

        log.info("Results of all active deductions: {}", result);

        return result;
    }
}
